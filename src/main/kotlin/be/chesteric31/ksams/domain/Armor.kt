package be.chesteric31.ksams.domain

import javax.persistence.*

@Entity
data class Armor(

    @Id
    @SequenceGenerator(name = "armor_generator", sequenceName = "armor_sequence", allocationSize = 1)
    @GeneratedValue(generator = "armor_generator")
    val id: Long = 0,
    val name: String = "") {

    @OneToOne
    @JoinColumn(name = "fk_category")
    lateinit var category: ArmorCategory

    @ManyToMany
    @JoinTable(name = "ARMOR_STRENGTH",
            joinColumns = arrayOf(JoinColumn(name = "fk_armor")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "fk_strength")))
    lateinit var strengths: List<Strength>

    constructor(category: ArmorCategory) : this(0, "") {
        this.category = category
    }
}