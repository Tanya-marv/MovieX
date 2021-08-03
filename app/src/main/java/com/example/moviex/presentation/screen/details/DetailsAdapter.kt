package com.example.moviex.presentation.screen.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviex.R

class DetailsAdapter : RecyclerView.Adapter<DetailsViewHolder>() {

    private val genres = mutableListOf<Genre>()

    override fun getItemCount(): Int = genres.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_genre, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    fun addGenre(newGenre: List<Genre>) {
        genres.addAll(newGenre)
        notifyDataSetChanged()
    }
}

class DetailsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(genres: Genre) {
        view.findViewById<TextView>(R.id.cv_genre).text = genres.name
    }
}

data class Genre(
        val id: Int,
        val name: String
)