package be.chesteric31.ksams.domain

import javax.persistence.*

@Entity
data class ArmorVersionAttack(

    @Id
    @SequenceGenerator(name = "armor_version_attack_generator", sequenceName = "armor_version_attack_sequence", allocationSize = 1)
    @GeneratedValue(generator = "armor_version_attack_generator")
    val id: Long = 0,
    val name: String = "") {

    @ManyToOne
    @JoinColumn(name = "fk_armor_version")
    lateinit var version: ArmorVersion

    constructor(version: ArmorVersion) : this(0, "") {
        this.version = version
    }
}