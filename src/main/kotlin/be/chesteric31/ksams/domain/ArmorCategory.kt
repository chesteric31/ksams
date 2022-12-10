package be.chesteric31.ksams.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
data class ArmorCategory(

    @Id
    @SequenceGenerator(name = "armor_category_generator", sequenceName = "armor_category_sequence", allocationSize = 1)
    @GeneratedValue(generator = "armor_category_generator")
    val id: Long = 0,
    val name: String = ""
)
