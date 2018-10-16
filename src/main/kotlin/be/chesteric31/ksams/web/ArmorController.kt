package be.chesteric31.ksams.web

import be.chesteric31.ksams.domain.Armor
import be.chesteric31.ksams.service.ArmorRepository
import be.chesteric31.ksams.service.ArmorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v2/armors")
class ArmorController(
        @Autowired val repository : ArmorRepository,
        @Autowired val service: ArmorService) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun getAll() = ResponseEntity.ok(repository.findAll())

    @PostMapping(value = ["/"])
    @ResponseBody
    fun save(@RequestBody armor: Armor): ResponseEntity<Armor> {
        val savedArmor = service.save(armor)
        if (armor.id != null) {
            return ResponseEntity.ok(savedArmor)
        }
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArmor.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }

}