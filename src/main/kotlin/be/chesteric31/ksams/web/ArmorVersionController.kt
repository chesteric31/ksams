package be.chesteric31.ksams.web

import com.cloudinary.Cloudinary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ArmorVersionController(@Autowired
                             val cloudinary: Cloudinary) {

    @GetMapping("/test")
    @ResponseBody
    fun test() : String {
        //val toUpload = File("/Users/eric/Downloads/pegasus_v1.png")
        return cloudinary.url().secure(true).imageTag("r2cuurx0namp81efnamh.png")
        //val uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap())
        //println(uploadResult)

    }
}