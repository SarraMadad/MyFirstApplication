package com.example.myfirstapplication.api

import com.example.myfirstapplication.presentation.list.Dungeon

data class DungeonResponse (
    val count: Int,
    val results: List<Dungeon>

        )