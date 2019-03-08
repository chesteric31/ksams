package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.Armor
import be.chesteric31.ksams.service.ArmorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v2/armors")
class ArmorController(@Autowired val service: ArmorService) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun findAll(@RequestParam("scaleHeight", required = false, defaultValue = "185") scaleHeight: String,
                @RequestParam("scaleWidth", required = false, defaultValue = "185") scaleWidth: String): ResponseEntity<List<Armor>> {
        return ResponseEntity.ok(service.findAllWithImageThumb(scaleHeight, scaleWidth))
    }

    @PostMapping(value = ["/"])
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(@RequestBody armor: Armor): ResponseEntity<Armor> {
        val savedArmor = service.save(armor)
        if (armor.id != null) {
            return ok(savedArmor)
        }
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArmor.id)
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
