package be.chesteric31.ksams.domain

import javax.persistence.*

@Entity
data class ArmorVersion(

    @Id
    @SequenceGenerator(name = "armor_version_generator", sequenceName = "armor_version_sequence", allocationSize = 1)
    @GeneratedValue(generator = "armor_version_generator")
    val id: Long = 0,
    val name: String = "",
    val image: String= "") {

    @ManyToOne
    @JoinColumn(name = "fk_armor")
    lateinit var armor: Armor

    @OneToMany(mappedBy = "version")
    var attacks: List<ArmorVersionAttack> = ArrayList()

    constructor(armor: Armor) : this(0, "", "") {
        this.armor = armor
    }
}