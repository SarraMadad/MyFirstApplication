package com.example.myfirstapplication.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.Singleton
import com.example.myfirstapplication.api.DungeonApi
import com.example.myfirstapplication.api.DungeonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DungeonListFragment : Fragment() {

    //on déclare une recyclerview
    private lateinit var recyclerView: RecyclerView

    //on déclare un adapter qui va lier les data à un élément de la liste
    private val adapter = DungeonAdapter(listOf(), ::onClickedDungeon)

    //on déclare un layout qui arrange les éléments de la liste
    //private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dungeon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //on initialise la recycleview pour récupérer mon élément et le mettre dans la liste
        //lien entre classe kotlin et fichier xml listfragment
        recyclerView = view.findViewById(R.id.dungeon_recyclerview)

        //on gère la liste, on lui donne l'adapter et on initialise le layout
        recyclerView.apply {
            adapter = this@DungeonListFragment.adapter
            layoutManager = LinearLayoutManager(context)
            //layoutManager = this@DungeonListFragment.layoutManager
        }

        //on va désormais manipuler notre API
        //val retrofit = Retrofit.Builder()
        //    .baseUrl("https://www.dnd5eapi.co/api/")
        //    .addConverterFactory(GsonConverterFactory.create())
        //    .build()

        //val dungeonApi: DungeonApi = retrofit.create(DungeonApi::class.java)

        Singleton.dungeonApi.getDungeonList().enqueue(object: Callback<DungeonResponse> {
            override fun onResponse(call: Call<DungeonResponse>, response: Response<DungeonResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val dungeonResponse = response.body()!!
                    adapter.updateList(dungeonResponse.results)
                }
            }

            override fun onFailure(call: Call<DungeonResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun onClickedDungeon(dungeon: Dungeon) {
        findNavController().navigate(R.id.navigateToDungeonDetailFragment, bundleOf(
                "dungeonIndex" to dungeon.index
        ))
    }
}