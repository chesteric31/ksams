package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorVersion
import com.cloudinary.Cloudinary
import com.cloudinary.Transformation
import com.cloudinary.utils.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@Service("armorVersionService")
class ArmorVersionServiceImpl(@Autowired val repository: ArmorVersionRepository,
                              @Autowired val armorRepository: ArmorRepository,
                              @Autowired val cloudinary: Cloudinary) : ArmorVersionService {

    override fun uploadArmorVersionImage(image: MultipartFile, armorName: String, armorVersionName: String): URI {
        val uploadResult = cloudinary.uploader().upload(image.bytes, ObjectUtils.emptyMap())
        val secureUrl = uploadResult["secure_url"]
        val armor = armorRepository.findByName(armorName)
        val uri = URI(secureUrl as String?)
        val armorVersion = ArmorVersion(0, armorVersionName, uri.toString())
        armorVersion.armor = armor!!
        repository.save(armorVersion)
        return uri
    }

    override fun buildAllWithImageThumb(scaleHeight: String, scaleWidth: String): MutableList<ArmorVersion> {
        val all = repository.findAll()
        all.forEach {
            it.thumb = scaleImage(it.image, scaleHeight, scaleWidth)
        }
        return all
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

    private fun scaleImage(image: String, scaleHeight: String, scaleWidth: String): String {
        val fileName = image.substring(image.lastIndexOf("/") + 1, image.length)
        //imageTag("audugxb5exmdlcksf7bm.png");
        return cloudinary
                .url()
                .transformation(Transformation<Transformation<out Transformation<*>>?>().height(scaleHeight)!!.width(scaleWidth).crop("thumb"))
                .secure(true)
                .generate(fileName)
    }

}