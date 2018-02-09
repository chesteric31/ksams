package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorVersion
import be.chesteric31.ksams.service.ArmorRepository
import be.chesteric31.ksams.service.ArmorVersionRepository
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@RestController
@RequestMapping("/api/armorVersions")
class ArmorVersionController(@Autowired
                                        val repository: ArmorVersionRepository,
                                        @Autowired val armorRepository: ArmorRepository,
                                        @Autowired val cloudinary: Cloudinary) {

    @GetMapping(value = ["/upload"])
    @ResponseBody
    fun provideUploadInfo() = ResponseEntity.ok("You can upload a file by posting to this same URL.")

    @PostMapping("/upload")
    @ResponseBody
    fun save(@RequestParam("armorName") armorName: String,
             @RequestParam("armorVersionName") armorVersionName: String,
             @RequestParam("image") image: MultipartFile) : ResponseEntity<String> {
        when {
            !image.isEmpty -> {
                val uploadResult = cloudinary.uploader().upload(image.bytes, ObjectUtils.emptyMap())
                val secureUrl = uploadResult["secure_url"]
                val uri = URI(secureUrl as String?)
                val armor = armorRepository.findByName(armorName)
                val armorVersion = ArmorVersion(0, armorVersionName, uri.toString())
                armorVersion.armor = armor!!
                repository.save(armorVersion)
                return ResponseEntity.created(uri).build()
            }
            else -> return ResponseEntity.badRequest().build()
        }
    }
}