package com.example.myfirstapplication.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MachineLearningListFragment : Fragment() {

    //on déclare une recyclerview
    private lateinit var recyclerView: RecyclerView

    //on déclare un adapter qui va lier les data à un élément de la liste
    private val adapter = MachineLearningAdapter(listOf())

    //on déclare un layout qui arrange les éléments de la liste
    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_machinelearning_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //on initialise la recycleview pour récupérer mon élément et le mettre dans la liste
        //lien entre classe kotlin et fichier xml listfragment
        recyclerView = view.findViewById(R.id.machinelearning_recyclerview)

        //on lui donne l'adapter et on initialise le layout
        recyclerView.apply {
            adapter = this@MachineLearningListFragment.adapter
            layoutManager = this@MachineLearningListFragment.layoutManager
        }

        //on crée notre liste de Machine Learning
        val machinelearningList = arrayListOf<MachineLearning>().apply {
            add(MachineLearning ("AI1"))
            add(MachineLearning ("AI2"))
            add(MachineLearning ("AI3"))
            add(MachineLearning ("AI4"))
            add(MachineLearning ("AI5"))
        }

        adapter.updateList(machinelearningList)
    }
}