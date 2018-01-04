package be.chesteric31.ksams.domain

import javax.persistence.*

@Entity
data class ArmorVersion(

    @Id
    @SequenceGenerator(name = "armor_version_generator", sequenceName = "armor_version_sequence", allocationSize = 1)
    @GeneratedValue(generator = "armor_version_generator")
    val id: Long = 0,
    val name: String = "",
    val image: String= "",
    @OneToOne
    @JoinColumn(name = "fk_armor")
    val armor: Armor
)