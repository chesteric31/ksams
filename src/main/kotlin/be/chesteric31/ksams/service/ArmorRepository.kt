package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.Armor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ArmorRepository : JpaRepository<Armor, Long> {

    fun findByNameAllIgnoringCase(@Param("name") name: String, pageable: Pageable): Page<Armor>
    fun findByName(@Param("name") name: String): Armor?
    @Query("SELECT a FROM Armor a, ArmorCategory c where a.category.id = c.id and c.name = :categoryName")
    fun findByCategory(@Param("categoryName") categoryName: String, pageable: Pageable): Page<Armor>
}