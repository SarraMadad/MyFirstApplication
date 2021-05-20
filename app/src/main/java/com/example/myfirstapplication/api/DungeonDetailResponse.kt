package com.example.myfirstapplication.api

data class DungeonDetailResponse (
        val name: String,
        val starting_equipment: List<DungeonQuantity>
    )

data class DungeonQuantity (
    val quantity: Int,
    val equipment: DungeonEquipment
    )

data class DungeonEquipment (
    val index: String,
    val url: String
    )