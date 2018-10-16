package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.Armor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("armorService")
class ArmorServiceImpl(@Autowired val repository: ArmorRepository,
                       @Autowired val categoryRepository: ArmorCategoryRepository) : ArmorService {

    override fun save(armor: Armor): Armor {
        val categoryId = armor.category.id
        val category = categoryRepository.findById(categoryId)
        if (category.isPresent) {
            armor.category = category.get()
        } else {
            throw IllegalStateException("Category ${armor.category.id} is unknown")
        }
        val savedArmor = repository.save(armor)
        return savedArmor
    }
}