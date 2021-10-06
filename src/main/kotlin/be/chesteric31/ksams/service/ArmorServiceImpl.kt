package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.Armor
import com.cloudinary.Cloudinary
import com.cloudinary.Transformation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("armorService")
class ArmorServiceImpl(@Autowired val repository: ArmorRepository,
                       @Autowired val categoryRepository: ArmorCategoryRepository,
                       @Autowired val cloudinary: Cloudinary) : ArmorService {

    override fun findAllWithImageThumb(scaleHeight: String, scaleWidth: String): List<Armor> {
        val all = this.findAll()
        all.forEach {
            it.versions.forEach { v ->
                v.thumb = scaleImage(v.armor.category.name, v.image, scaleHeight, scaleWidth)
            }
        }
        return all
    }

    override fun findAll() = repository.findAll()

    override fun findById(id: Long) = repository.findById(id)

    override fun save(armor: Armor): Armor {
        val categoryId = armor.category.id
        val category = categoryRepository.findById(categoryId)
        if (category.isPresent) {
            armor.category = category.get()
        } else {
            throw IllegalStateException("Category ${armor.category.id} is unknown")
        }
        return repository.save(armor)
    }

    override fun delete(armor: Armor) = repository.delete(armor)

    private fun scaleImage(categoryName: String, image: String, scaleHeight: String, scaleWidth: String): String {
        val fileName = image.substring(image.lastIndexOf("/") + 1, image.length)
        //imageTag("audugxb5exmdlcksf7bm.png");
        return cloudinary
                .url()
                .transformation(Transformation<Transformation<out Transformation<*>>?>().height(scaleHeight)!!.width(scaleWidth).crop("thumb"))
                .secure(true)
                .generate("/" + categoryName.toLowerCase() + "/" + fileName)
    }

}
