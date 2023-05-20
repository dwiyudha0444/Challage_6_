@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused"
)

package com.dwiyu.ch5_moviedb.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwiyu.ch5_moviedb.R
import com.dwiyu.ch5_moviedb.databinding.ItemFavBinding
import com.dwiyu.ch5_moviedb.room.DataFav

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused"
)
class AdapterFav(private val listMovie: List<DataFav>) :
    RecyclerView.Adapter<AdapterFav.ViewHolder>() {


    class ViewHolder(var binding: ItemFavBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindFilms(movie: DataFav) {
            binding.apply {
                binding.filmfav = movie
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w400${movie.posterPath}")
                    .into(binding.imgFilm)
                cardView.setOnClickListener{
                    val bundle = Bundle().apply {
                        putInt("ID", movie.id.toString().toInt())
                    }
                    it.findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFilms(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

}