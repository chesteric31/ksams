package be.chesteric31.ksams.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class ArmorVersion(

        @Id
        @SequenceGenerator(name = "armor_version_generator", sequenceName = "armor_version_sequence", allocationSize = 1)
        @GeneratedValue(generator = "armor_version_generator")
        val id: Long = 0,
        val name: String = "",
        var image: String = "") {

    @ManyToOne
    @JoinColumn(name = "fk_armor")
    lateinit var armor: Armor

    @OneToMany(mappedBy = "version")
    @JsonIgnore
    var attacks: List<ArmorVersionAttack> = ArrayList()

    @Transient
    var thumb: String = ""

    constructor(armor: Armor) : this(0, "", "") {
        this.armor = armor
    }
}