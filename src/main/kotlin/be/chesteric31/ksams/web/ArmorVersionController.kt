package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorVersion
import be.chesteric31.ksams.service.ArmorVersionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v2/versions")
class ArmorVersionController(@Autowired val service: ArmorVersionService) {

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
                return ResponseEntity.created(service.uploadArmorVersionImage(image, armorName, armorVersionName)).build()
            }
            else -> return ResponseEntity.badRequest().build()
        }
    }

    @PostMapping
    @ResponseBody
    fun save(@RequestBody armorVersion: ArmorVersion): ResponseEntity<ArmorVersion> {
        val savedVersion = service.save(armorVersion)
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
