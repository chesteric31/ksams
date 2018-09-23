package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorCategory
import be.chesteric31.ksams.service.ArmorCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v2/categories")
class ArmorCategoryController(@Autowired val repository : ArmorCategoryRepository) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun getAll() = ResponseEntity.ok(repository.findAll())

    @PostMapping(value = ["/"])
    @ResponseBody
    fun save(@RequestBody category: ArmorCategory): ResponseEntity<ArmorCategory> {
        val savedCategory = repository.save(category)
        if (category.id != null) {
            return ResponseEntity.ok(savedCategory)
        }
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }

}