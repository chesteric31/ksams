package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("armorCategoryService")
class ArmorCategoryServiceImpl(@Autowired val repository: ArmorCategoryRepository) : ArmorCategoryService {

    override fun findAll(): List<ArmorCategory> = repository.findAll()
    override fun findById(id: Long): Optional<ArmorCategory> = repository.findById(id)
    override fun save(armorCategory: ArmorCategory): ArmorCategory = repository.save(armorCategory)
    override fun delete(armorCategory: ArmorCategory) = repository.delete(armorCategory)
}
