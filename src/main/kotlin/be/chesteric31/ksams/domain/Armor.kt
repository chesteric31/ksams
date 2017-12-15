package be.chesteric31.ksams.domain

import javax.persistence.*

@Entity
data class Armor(

    @Id
    @SequenceGenerator(name = "armor_generator", sequenceName = "armor_sequence", allocationSize = 1)
    @GeneratedValue(generator = "armor_generator")
    val id: Long = 0,
    val name: String = "",
    @OneToOne
    @JoinColumn(name = "fk_category")
    val category: ArmorCategory
)