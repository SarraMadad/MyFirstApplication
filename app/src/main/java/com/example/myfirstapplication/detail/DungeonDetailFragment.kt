package com.example.myfirstapplication.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.Singleton
import com.example.myfirstapplication.api.DungeonDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DungeonDetailFragment : Fragment() {

    private lateinit var textViewName: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dungeon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view.findViewById<Button>(R.id.button_second).setOnClickListener {
        //    findNavController().navigate(R.id.navigateToDungeonListFragment)
        //}

        textViewName = view.findViewById(R.id.dungeon_detail_name)
        callApi()
    }

    private fun callApi() {

        val index = arguments?.getString("dungeonIndex")

        Singleton.dungeonApi.getDungeonDetail(index.toString()).enqueue(object: Callback<DungeonDetailResponse> {
            override fun onResponse(
                    call: Call<DungeonDetailResponse>,
                    response: Response<DungeonDetailResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    textViewName.text = response.body()!!.name
                    //textViewName.text = response.body()!!.url
                    //textViewName.text = response.body()!!.starting_equipment.toString()
                } else {
                    textViewName.text = "test"

                }
            }

            override fun onFailure(call: Call<DungeonDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
