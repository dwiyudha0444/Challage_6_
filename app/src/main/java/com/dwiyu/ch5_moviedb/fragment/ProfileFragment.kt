package com.dwiyu.ch5_moviedb.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dwiyu.ch5_moviedb.R
import com.dwiyu.ch5_moviedb.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val getUser = sharedPreferences.getString("user", "")
        binding.etUsernameprofile.setText(getUser)

        val getNama = sharedPreferences.getString("nama", "")
        binding.etNameprofile.setText(getNama)

        val getTgl = sharedPreferences.getString("tgl", "")
        binding.etTgllahirprofile.setText(getTgl)

        val getAlamat = sharedPreferences.getString("alamat", "")
        binding.etAddressprofile.setText(getAlamat)

        binding.btnupdateprofile.setOnClickListener {
            val getUsername = binding.etUsernameprofile.text.toString()
            val getNamaLengkap = binding.etNameprofile.text.toString()
            val getTglLahir = binding.etTgllahirprofile.text.toString()
            val getAlamat = binding.etAddressprofile.text.toString()
            val addUser = sharedPreferences.edit()
            addUser.putString("user", getUsername)
            addUser.putString("nama", getNamaLengkap)
            addUser.putString("tgl", getTglLahir)
            addUser.putString("alamat", getAlamat)
            addUser.apply()

            Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            val addUser = sharedPreferences.edit()
            addUser.remove("nama")
            addUser.remove("tgl")
            addUser.remove("alamat")
            addUser.commit()
            Toast.makeText(context, "Keluar Berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment2)
        }



    }

}