package be.chesteric31.ksams.web

import be.chesteric31.ksams.service.ArmorCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/categories")
class ArmorCategoryController(@Autowired val repository : ArmorCategoryRepository) {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun getAll() = ResponseEntity.ok(repository.findAll())

}