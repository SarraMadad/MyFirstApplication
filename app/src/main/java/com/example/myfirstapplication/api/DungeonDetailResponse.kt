package com.example.myfirstapplication.api

data class DungeonDetailResponse (
        val index: String,
        val name: String,
        val url: String,
        val starting_equipment: List<DungeonStartingEquipment>
    )

data class DungeonStartingEquipment (
    val quantity: Int,
    val equipment: DungeonEquipment
    )

data class DungeonEquipment (
    val index: String,
    val name: String,
    val url: String
    )