package be.chesteric31.ksams.web

import be.chesteric31.ksams.service.ArmorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/armors")
class ArmorController(@Autowired val repository : ArmorRepository) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun getAll() = ResponseEntity.ok(repository.findAll())

}