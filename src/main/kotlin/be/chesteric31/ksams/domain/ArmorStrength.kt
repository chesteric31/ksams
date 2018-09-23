package be.chesteric31.ksams.domain

import javax.persistence.*

@Entity
data class ArmorStrength(

        @Id
        @SequenceGenerator(name = "armor_strength_generator", sequenceName = "armor_strength_sequence", allocationSize = 1)
        @GeneratedValue(generator = "armor_strength_generator")
        val id: Long = 0,
        val amount: Long = 0
) {

    @ManyToOne
    @JoinColumn(name = "fk_armor")
    lateinit var armor: Armor

    @ManyToOne
    @JoinColumn(name = "fk_strength")
    lateinit var strength: Strength

}
