package be.chesteric31.ksams.config

import com.cloudinary.Cloudinary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CloudinaryConfig {

    @Autowired
    lateinit var properties : CloudinaryProperties

    @Bean
    fun cloudinary() : Cloudinary {
        val cloudinary = Cloudinary()
        cloudinary.config.apiKey = properties.apiKey
        cloudinary.config.apiSecret = properties.apiSecret
        cloudinary.config.cloudName = properties.cloudName
        return cloudinary
    }
}