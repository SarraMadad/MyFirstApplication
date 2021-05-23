package com.example.myfirstapplication.presentation.list

//ce model pilote la vue via la livedata
//permet de maitriser l'héritage
sealed class DungeonModel

data class DungeonSuccess(val dungList: List<Dungeon>) : DungeonModel()
//écran de load pendant que la liste se charge (sans cache)
object  DungeonLoader : DungeonModel()

//écran d'érreur si la liste ne charge pas
object  DungeonError : DungeonModel()