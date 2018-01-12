package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.ArmorVersion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "armorVersions", path = "armorVersions")
interface ArmorVersionRepository : PagingAndSortingRepository<ArmorVersion, Long> {

    fun findByNameAllIgnoringCase(@Param("name") name: String, pageable: Pageable): Page<ArmorVersion>

    fun findByName(@Param("name") name: String): ArmorVersion

}