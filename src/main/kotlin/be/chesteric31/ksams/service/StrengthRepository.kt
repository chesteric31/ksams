package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorCategory
import be.chesteric31.ksams.domain.Strength
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "strengths", path = "strengths")
interface StrengthRepository : PagingAndSortingRepository<Strength, Long> {

    fun findByNameAllIgnoringCase(@Param("name") name: String, pageable: Pageable): Page<Strength>
}