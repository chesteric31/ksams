package be.chesteric31.ksams.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

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
    @JsonIgnore
    lateinit var armor: Armor

    @ManyToOne
    @JoinColumn(name = "fk_strength")
    lateinit var strength: Strength

}
