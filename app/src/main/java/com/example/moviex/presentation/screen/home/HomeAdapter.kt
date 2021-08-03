package com.example.moviex.presentation.screen.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviex.R

class HomeAdapter (
        private val clickListener: (MoviesItem) -> Unit
): RecyclerView.Adapter<HomeViewHolder>() {

        private val movies = mutableListOf<MoviesItem>()

        override fun getItemCount(): Int = movies.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_movies, parent, false)
                return HomeViewHolder(view)
        }

        override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
                holder.bind(movies[position], clickListener)
        }

        fun addMovies(newMovies: List<MoviesItem>) {
                movies.addAll(newMovies)
                notifyDataSetChanged()
        }
}

class HomeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movies: MoviesItem, clickListener: (MoviesItem) -> Unit) {
                view.setOnClickListener { clickListener(movies) }
                view.findViewById<ImageView>(R.id.iv_poster)
                        .load("https://image.tmdb.org/t/p/w342${movies.path}")
        }
}

data class MoviesItem(
        val id: Long,
        val path: String
)
