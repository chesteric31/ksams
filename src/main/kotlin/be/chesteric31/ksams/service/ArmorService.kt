package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.Armor

interface ArmorService {

    fun save(armor: Armor): Armor
    fun findAll(): List<Armor>
    fun findAllWithImageThumb(scaleHeight: String, scaleWidth: String): List<Armor>

}