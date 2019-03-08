package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorCategory
import be.chesteric31.ksams.service.ArmorCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v2/categories")
class ArmorCategoryController(@Autowired val service : ArmorCategoryService) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun getAll() = ResponseEntity.ok(service.findAll())

    @PostMapping(value = ["/"])
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(@RequestBody category: ArmorCategory): ResponseEntity<ArmorCategory> {
        val savedCategory = service.save(category)
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

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun delete(@PathVariable id: Long) : ResponseEntity<Any> {
        val category = service.findById(id)
        if (!category.isPresent) {
            return ResponseEntity.notFound().build()
        }
        service.delete(category.get())
        return ResponseEntity.noContent().build()
    }
}
