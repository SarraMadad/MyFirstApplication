package com.example.myfirstapplication.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
//la classe adapter lie les données avec la viewholder, qui définit chaque item de la liste
//on change dataSet en List au lieu d'Array car l'utilisation d'une interface est plus intéressante
class DungeonAdapter(private var dataSet: List<Dungeon>) : RecyclerView.Adapter<DungeonAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    //ceci est une "cellule", un élément de Dungeon
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.dungeon_name)
        }
    }

    //mise à jour de la liste
    fun updateList(list : List<Dungeon>) {
        dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dungeon_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val dungeon = dataSet[position]
        viewHolder.textView.text = dungeon.name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}