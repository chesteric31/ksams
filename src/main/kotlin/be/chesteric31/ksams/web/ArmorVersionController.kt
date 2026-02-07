package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorVersion
import be.chesteric31.ksams.service.ArmorVersionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest

@RestController
@RequestMapping("/api/v2/versions")
class ArmorVersionController(@Autowired val service: ArmorVersionService) {

    @GetMapping(value = ["/upload"])
    @ResponseBody
    fun provideUploadInfo() = ok("You can upload a file by posting to this same URL.")

    @PostMapping("/upload")
    @ResponseBody
    fun save(
        @RequestParam armorName: String,
        @RequestParam armorVersionName: String,
        @RequestParam image: MultipartFile
    ): ResponseEntity<String> {
        when {
            !image.isEmpty -> {
                return created(service.uploadArmorVersionImage(image, armorName, armorVersionName)).build()
            }

            else -> return ResponseEntity.badRequest().build()
        }
    }

    @PostMapping
    @ResponseBody
    fun save(@RequestBody armorVersion: ArmorVersion): ResponseEntity<ArmorVersion> {
        val id = armorVersion.id
        val savedVersion = service.save(armorVersion)
        if (id != 0L) {
            return ok(savedVersion)
        }
        val location = fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedVersion.id)
            .toUri()
        return created(location).build()
    }

}
