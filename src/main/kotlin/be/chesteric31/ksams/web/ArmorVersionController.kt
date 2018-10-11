package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorVersion
import be.chesteric31.ksams.service.ArmorRepository
import be.chesteric31.ksams.service.ArmorVersionRepository
import com.cloudinary.Cloudinary
import com.cloudinary.Transformation
import com.cloudinary.utils.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/v2/versions")
class ArmorVersionController(@Autowired val repository: ArmorVersionRepository,
                             @Autowired val armorRepository: ArmorRepository,
                             @Autowired val cloudinary: Cloudinary) {

    @GetMapping(value = ["/upload"])
    @ResponseBody
    fun provideUploadInfo() = ResponseEntity.ok("You can upload a file by posting to this same URL.")

    @PostMapping("/upload")
    @ResponseBody
    fun save(@RequestParam("armorName") armorName: String,
             @RequestParam("armorVersionName") armorVersionName: String,
             @RequestParam("image") image: MultipartFile): ResponseEntity<String> {
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

    @GetMapping
    @ResponseBody
    fun getAll(@RequestParam("scaleHeight") scaleHeight: String?, @RequestParam("scaleWidth") scaleWidth: String?): ResponseEntity<MutableList<ArmorVersion>> {
        val all = repository.findAll()
        if (scaleHeight != null && scaleWidth != null) {
            all.forEach {
                it.thumb = scaleImage(it.image, scaleHeight, scaleWidth)
            }
        }
        return ResponseEntity.ok(all)
    }

    private fun scaleImage(image: String, scaleHeight: String, scaleWidth: String): String {
        val fileName = image.substring(image.lastIndexOf("/") + 1, image.length)
        //imageTag("audugxb5exmdlcksf7bm.png");
        return cloudinary
                .url()
                .transformation(Transformation<Transformation<out Transformation<*>>?>().height(scaleHeight)!!.width(scaleWidth).crop("thumb"))
                .secure(true)
                .generate(fileName)
    }

    @PostMapping
    @ResponseBody
    fun save(@RequestBody armorVersion: ArmorVersion): ResponseEntity<ArmorVersion> {
        val armor = armorRepository.findById(armorVersion.armor.id)
        if (armor.isPresent) {
            armorVersion.armor = armor.get()
        } else {
            throw IllegalStateException("Armor ${armorVersion.armor.id} is not known")
        }
        val savedVersion = repository.save(armorVersion)
        if (armorVersion.id != null) {
            return ResponseEntity.ok(savedVersion)
        }
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedVersion.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }

}