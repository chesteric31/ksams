package be.chesteric31.ksams.service

import be.chesteric31.ksams.domain.Armor

interface ArmorService {

    fun save(armor: Armor): Armor
}