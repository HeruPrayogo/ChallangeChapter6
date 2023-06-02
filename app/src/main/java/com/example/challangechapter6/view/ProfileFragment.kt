package com.example.challangechapter6.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.challangechapter6.R
import com.example.challangechapter6.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        sharedPreferences = requireContext().getSharedPreferences("InsertAcc", Context.MODE_PRIVATE)
        binding.uss.setText(sharedPreferences.getString("uss", " "))
        updateUsername()
        binding.Logout.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(context, "Logout Berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

    }
    private fun updateUsername() {
        sharedPreferences = requireContext().getSharedPreferences("InsertAcc", Context.MODE_PRIVATE)
        binding.Update.setOnClickListener {
            val getUsername = binding.uss.text.toString()
            binding.Nama.text.toString()
            binding.TglLahir.text.toString()
            binding.Alamat.text.toString()
            val updateUser = sharedPreferences.edit()
            updateUser.putString("uss", getUsername)
            updateUser.apply()
            Toast.makeText(context, "UpdateBerhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
    }


}