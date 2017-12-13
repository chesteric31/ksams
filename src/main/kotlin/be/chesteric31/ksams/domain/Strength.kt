package be.chesteric31.ksams.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
data class Strength(

    @Id
    @SequenceGenerator(name = "strength_generator", sequenceName = "strength_sequence", allocationSize = 1)
    @GeneratedValue(generator = "strength_generator")
    val id: Long = 0,
    val name: String = ""
)