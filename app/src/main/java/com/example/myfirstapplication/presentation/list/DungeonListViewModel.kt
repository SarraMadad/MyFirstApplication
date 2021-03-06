package com.example.myfirstapplication.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstapplication.Singleton
import com.example.myfirstapplication.api.DungeonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DungeonListViewModel : ViewModel() {

    val dungList : MutableLiveData<DungeonModel> = MutableLiveData()

    init {

        callApi()

    }

    private fun callApi() {
        dungList.value = DungeonLoader
        Singleton.dungeonApi.getDungeonList().enqueue(object: Callback<DungeonResponse> {
            override fun onResponse(call: Call<DungeonResponse>, response: Response<DungeonResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val dungeonResponse = response.body()!!
                    dungList.value = DungeonSuccess(dungeonResponse.results)
                } else {
                    dungList.value = DungeonError
                }
            }

            override fun onFailure(call: Call<DungeonResponse>, t: Throwable) {
                dungList.value = DungeonError
            }
        })
    }
}