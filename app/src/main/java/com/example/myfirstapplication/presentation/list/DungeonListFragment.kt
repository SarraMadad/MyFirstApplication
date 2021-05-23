package com.example.myfirstapplication.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    //on déclare le loader
    private lateinit var loader: ProgressBar

    //on déclare le mode error
    private lateinit var textViewError: TextView

    //on déclare un adapter qui va lier les data à un élément de la liste
    private val adapter = DungeonAdapter(listOf(), ::onClickedDungeon)

    //on déclare un layout qui arrange les éléments de la liste
    //private val layoutManager = LinearLayoutManager(context)

    //on crée une variable de la classe dungeonlistviewmodel
    private val viewModel : DungeonListViewModel by viewModels()

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
        loader = view.findViewById(R.id.dungeon_loader)
        textViewError = view.findViewById(R.id.dungeon_error)

        //on gère la liste, on lui donne l'adapter et on initialise le layout
        recyclerView.apply {
            adapter = this@DungeonListFragment.adapter
            layoutManager = LinearLayoutManager(context)
            //layoutManager = this@DungeonListFragment.layoutManager
        }

        viewModel.dungList.observe(viewLifecycleOwner, Observer { dungeonModel ->
            loader.isVisible = dungeonModel is DungeonLoader
            textViewError.isVisible = dungeonModel is DungeonError

            if (dungeonModel is DungeonSuccess) {
                adapter.updateList(dungeonModel.dungList)
            }

        })

    }

    private fun onClickedDungeon(dungeon: Dungeon) {
        findNavController().navigate(R.id.navigateToDungeonDetailFragment, bundleOf(
                "dungeonIndex" to dungeon.index
        ))
    }
}