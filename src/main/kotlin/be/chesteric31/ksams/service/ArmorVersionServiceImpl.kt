package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorVersion
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@Service("armorVersionService")
class ArmorVersionServiceImpl(
    val repository: ArmorVersionRepository,
    val armorRepository: ArmorRepository,
    val cloudinary: Cloudinary
) : ArmorVersionService {

    override fun uploadArmorVersionImage(image: MultipartFile, armorName: String, armorVersionName: String): URI {
        val uploadResult = cloudinary.uploader().upload(image.bytes, ObjectUtils.emptyMap())
        val secureUrl = uploadResult["secure_url"]
        val armor = armorRepository.findByName(armorName)
        val uri = URI(secureUrl as String)
        val armorVersion = ArmorVersion(0, armorVersionName, uri.toString())
        armorVersion.armor = armor!!
        repository.save(armorVersion)
        return uri
    }

    override fun save(armorVersion: ArmorVersion): ArmorVersion {
        val armor = armorRepository.findById(armorVersion.armor.id)
        if (armor.isPresent) {
            armorVersion.armor = armor.get()
        } else {
            throw IllegalStateException("Armor ${armorVersion.armor.id} is not known")
        }
        return repository.save(armorVersion)
    }

}