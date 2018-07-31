package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorVersionAttack
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ArmorVersionAttackRepository : JpaRepository<ArmorVersionAttack, Long> {

    fun findByNameAllIgnoringCase(@Param("name") name: String, pageable: Pageable): Page<ArmorVersionAttack>

}