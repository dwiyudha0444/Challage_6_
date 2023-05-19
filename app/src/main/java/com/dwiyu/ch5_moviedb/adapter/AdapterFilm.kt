package com.dwiyu.ch5_moviedb.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwiyu.ch5_moviedb.R
import com.dwiyu.ch5_moviedb.databinding.ItemFilmBinding
import com.dwiyu.ch5_moviedb.model.NowPlayingMovieResponse

class AdapterFilm (var listFilm: List<NowPlayingMovieResponse>):
    RecyclerView.Adapter<AdapterFilm.ViewHolder>() {

    class ViewHolder(var binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindFilms(itemFilms: NowPlayingMovieResponse){
            binding.film = itemFilms
            binding.cardView.setOnClickListener{
                val bundle = Bundle()
                bundle.putSerializable("BUNDEL", itemFilms)
                Navigation.findNavController(itemView).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFilms(listFilm[position])
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listFilm[position].posterPath}")
            .into(holder.binding.imgFilm)

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("BUNDEL", listFilm[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

}

