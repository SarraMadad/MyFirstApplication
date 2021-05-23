package com.example.myfirstapplication

import com.example.myfirstapplication.DungeonApplication.Companion.context
import com.example.myfirstapplication.api.DungeonApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singleton {
    companion object {
        //on set up le cache, la variable stock des données dans un dossier du système
        var cache = Cache( File(context?.cacheDir, "responses"), 10 * 1024 * 1024)

        //on déclare le client http, celui qui fait les requêtes
        val okHttpClient : OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()

        val dungeonApi = Retrofit.Builder()
            .baseUrl("https://www.dnd5eapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
            .build()
            .create(DungeonApi::class.java)
    }

}
