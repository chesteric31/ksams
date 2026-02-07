package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.Armor
import be.chesteric31.ksams.service.ArmorService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest

@RestController
@RequestMapping("/api/v2/armors")
class ArmorController(val service: ArmorService) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun findAll(
        @RequestParam(required = false, defaultValue = "185") scaleHeight: String,
        @RequestParam(required = false, defaultValue = "185") scaleWidth: String
    ): ResponseEntity<List<Armor>> {
        return ok(service.findAllWithImageThumb(scaleHeight, scaleWidth))
    }

    @PostMapping(value = ["/"])
    @ResponseBody
    fun save(@RequestBody armor: Armor): ResponseEntity<Armor> {
        val id = armor.id
        val savedArmor = service.save(armor)
        if (id != 0L) {
            return ok(savedArmor)
        }
        val location = fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedArmor.id)
            .toUri()
        return created(location).build()
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun get(@PathVariable id: Long): ResponseEntity<Any> {
        val optional = service.findById(id)
        if (!optional.isPresent) {
            return notFound().build()
        }
        return ok().body(optional.get())
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        val optional = service.findById(id)
        if (!optional.isPresent) {
            return notFound().build()
        }
        service.delete(optional.get())
        return noContent().build()
    }

}
