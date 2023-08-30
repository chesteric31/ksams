package be.chesteric31.ksams.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator

@Entity
data class Strength(

    @Id
    @SequenceGenerator(name = "strength_generator", sequenceName = "strength_sequence", allocationSize = 1)
    @GeneratedValue(generator = "strength_generator")
    val id: Long = 0,
    val name: String = ""
)