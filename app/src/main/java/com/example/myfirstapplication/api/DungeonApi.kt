package com.example.myfirstapplication.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DungeonApi {
    @GET("classes")
    fun getDungeonList(): Call<DungeonResponse>

    @GET("classes/{index}")
    fun getDungeonDetail(@Path("index") index: String): Call<DungeonDetailResponse>

}

//attention au type de retour : on voit bien que le retour est un objet (accolade) et non pas une
//liste (crochet)