package be.chesteric31.ksams.web

import be.chesteric31.ksams.service.ArmorRepository
import be.chesteric31.ksams.service.ArmorVersionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URI

@RestController
@RequestMapping("/api/armorVersions")
class ArmorVersionImageUploadController(@Autowired
                                        val repository: ArmorVersionRepository,
                                        @Autowired val armorRepository: ArmorRepository) {

    @GetMapping(value = "/upload")
    @ResponseBody
    fun provideUploadInfo() = ResponseEntity.ok("You can upload a file by posting to this same URL.")

    @PostMapping("/upload")
    @ResponseBody
    fun upload(@RequestParam("armorName") armorName: String,
               @RequestParam("armorVersionName") armorVersionName: String,
               @RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        if (!file.isEmpty) {
            try {
                val bytes = file.bytes
                val stream = BufferedOutputStream(FileOutputStream(File(armorName)))
                stream.write(bytes)
                stream.close()
                val uri = URI("https://res.cloudinary.com/ksams/image/upload/v1513774255/r2cuurx0namp81efnamh.png")
                return ResponseEntity.created(uri).build()
                //return "You successfully uploaded $name!"
            } catch (e: Exception) {
                return ResponseEntity.badRequest().build()
                //return "You failed to upload $name => ${e.message}"
            }
        } else {
            return ResponseEntity.badRequest().build()
            //return "You failed to upload $name because the file was empty."
        }
        return ResponseEntity.badRequest().build()
        //return ""
    }
}