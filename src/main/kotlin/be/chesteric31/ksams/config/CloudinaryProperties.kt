package be.chesteric31.ksams.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CloudinaryProperties {

    @Value("\${cloudinary.apiKey}") lateinit var apiKey: String
    @Value("\${cloudinary.apiSecret}") lateinit var apiSecret: String
    @Value("\${cloudinary.cloudname}") lateinit var cloudName: String

}