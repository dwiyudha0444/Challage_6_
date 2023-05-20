@file:Suppress("PrivatePropertyName")

package com.dwiyu.ch5_moviedb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiyu.ch5_moviedb.adapter.AdapterFav
import com.dwiyu.ch5_moviedb.databinding.FragmentFavoriteBinding
import com.dwiyu.ch5_moviedb.room.FavDB
import com.dwiyu.ch5_moviedb.viewmodel.ViewModelFav
import dagger.hilt.android.AndroidEntryPoint

@Suppress("PrivatePropertyName")
@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var viewModel: ViewModelFav
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private var FavDB: FavDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModelFav::class.java]
        getAllFav()
    }

    private fun getAllFav(){
        viewModel.getAllFav()
        viewModel.listMovie.observe(viewLifecycleOwner){
            if(it != null){
                binding.rvListfilmfav.layoutManager = LinearLayoutManager(requireContext())
                binding.rvListfilmfav.setHasFixedSize(false)
                binding.rvListfilmfav.adapter = AdapterFav(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}