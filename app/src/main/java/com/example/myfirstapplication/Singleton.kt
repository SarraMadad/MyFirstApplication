package com.example.myfirstapplication

import com.example.myfirstapplication.api.DungeonApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Singleton {
    companion object {
        val dungeonApi = Retrofit.Builder()
            .baseUrl("https://www.dnd5eapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DungeonApi::class.java)
    }
}
