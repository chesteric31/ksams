package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorVersion
import org.springframework.web.multipart.MultipartFile
import java.net.URI

interface ArmorVersionService {

    fun uploadArmorVersionImage(image: MultipartFile, armorName: String, armorVersionName: String): URI
    fun buildAllWithImageThumb(scaleHeight: String, scaleWidth: String): MutableList<ArmorVersion>
    fun save(armorVersion: ArmorVersion): ArmorVersion
}