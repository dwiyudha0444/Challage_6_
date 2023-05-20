package com.dwiyu.ch5_moviedb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dwiyu.ch5_moviedb.databinding.FragmentDetailBinding
import com.dwiyu.ch5_moviedb.model.NowPlayingMovieResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val getfilm = arguments?.getSerializable("BUNDEL") as NowPlayingMovieResponse
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/w500${getfilm.posterPath}")
            .into(binding.ivFilmimagedetail)
        binding.tvJdl.text = getfilm.title
        binding.tvRelease.text = "Release : ${getfilm.releaseDate}"
        binding.tvKP.text = "Popularity : ${getfilm.popularity}"
        binding.tvSin.text = """Overview:
            ${getfilm.overview}
        """.trimIndent()


    }


}