package be.chesteric31.ksams.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator

@Entity
data class ArmorCategory(

    @Id
    @SequenceGenerator(name = "armor_category_generator", sequenceName = "armor_category_sequence", allocationSize = 1)
    @GeneratedValue(generator = "armor_category_generator")
    val id: Long = 0,
    val name: String = ""
)
