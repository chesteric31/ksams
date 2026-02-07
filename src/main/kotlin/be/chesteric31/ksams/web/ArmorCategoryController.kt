package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.ArmorCategory
import be.chesteric31.ksams.service.ArmorCategoryService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest

@RestController
@RequestMapping("/api/v2/categories")
class ArmorCategoryController(val service: ArmorCategoryService) {

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
            return notFound().build()
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
        val location = fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedCategory.id)
            .toUri()
        return created(location).build()
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        val category = service.findById(id)
        if (!category.isPresent) {
            return notFound().build()
        }
        service.delete(category.get())
        return noContent().build()
    }
}
