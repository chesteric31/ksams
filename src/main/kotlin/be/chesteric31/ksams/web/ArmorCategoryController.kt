package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorCategory
import be.chesteric31.ksams.service.ArmorCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v2/categories")
class ArmorCategoryController(@Autowired val service : ArmorCategoryService) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun getAll(): ResponseEntity<List<ArmorCategory>>? {
        return ok(service.findAll())
    }

    @GetMapping(value = ["/{id}"])
    @ResponseBody
    fun get(@PathVariable id: Long): ResponseEntity<ArmorCategory>? {
        val category = service.findById(id)
        if (!category.isPresent) {
            return ResponseEntity.notFound().build()
        }
        return ok(category.get())
    }

    @PostMapping(value = ["/"])
    @ResponseBody
    fun save(@RequestBody category: ArmorCategory): ResponseEntity<ArmorCategory> {
        val id = category.id
        val savedCategory = service.save(category)
        if (id != 0L) {
            return ok(savedCategory)
        }
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.id)
                .toUri()
        return created(location).build()
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun delete(@PathVariable id: Long) : ResponseEntity<Any> {
        val category = service.findById(id)
        if (!category.isPresent) {
            return ResponseEntity.notFound().build()
        }
        service.delete(category.get())
        return ResponseEntity.noContent().build()
    }
}
