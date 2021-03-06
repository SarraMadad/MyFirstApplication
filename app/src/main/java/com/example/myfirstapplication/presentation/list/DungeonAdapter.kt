package com.example.myfirstapplication.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstapplication.R
import kotlin.reflect.KFunction1

//la classe adapter lie les données avec la viewholder, qui définit chaque item de la liste
//on change dataSet en List au lieu d'Array car l'utilisation d'une interface est plus intéressante
class DungeonAdapter(private var dataSet: List<Dungeon>, var listener: ((Dungeon)->Unit)? = null) : RecyclerView.Adapter<DungeonAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    //ceci est une "cellule", un élément de Dungeon
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView : ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.dungeon_name)
            imageView = view.findViewById(R.id.dungeon_img)
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
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(dungeon)
        }

        Glide
            .with(viewHolder.itemView.context)
            .load("https://e7.pngegg.com/pngimages/491/184/png-clipart-dungeons-dragons-pathfinder-roleplaying-game-elf-concept-art-character-elf-fictional-character-cartoon-thumbnail.png")
            .centerCrop()
            .into(viewHolder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}