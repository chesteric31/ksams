package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorCategory
import java.util.*

interface ArmorCategoryService {

    fun save(armorCategory: ArmorCategory): ArmorCategory
    fun findAll(): List<ArmorCategory>
    fun findById(id: Long): Optional<ArmorCategory>
    fun delete(armorCategory: ArmorCategory)
}
