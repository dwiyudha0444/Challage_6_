package com.dwiyu.ch5_moviedb.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiyu.ch5_moviedb.R
import com.dwiyu.ch5_moviedb.adapter.AdapterFilm
import com.dwiyu.ch5_moviedb.databinding.FragmentHomeBinding
import com.dwiyu.ch5_moviedb.viewmodel.ViewModelFilm
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: ViewModelFilm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModelFilm::class.java]
        observeMovie()
        (activity as AppCompatActivity).setSupportActionBar(binding.tbHome)

        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser == null){
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        val getUser = sharedPreferences.getString("user", "")
        binding.textView.text = "Welcome, $getUser"

        binding.ivIcprofile.setOnClickListener {
            val addUser = sharedPreferences.edit()
            addUser.putString("user", getUser)
            addUser.apply()
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.ivIcfavhome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }

  }

        private fun observeMovie(){
            binding.rvFilm.layoutManager = LinearLayoutManager(requireContext())
            binding.rvFilm.setHasFixedSize(false)
            viewModel.callApi()
            viewModel.movie.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.rvFilm.adapter = AdapterFilm(it)
                }
            }
        }


}