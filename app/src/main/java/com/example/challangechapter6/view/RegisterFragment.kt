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
import com.example.challangechapter6.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        insertAcc()
    }
    private fun insertAcc(){
        sharedPreferences = requireContext().getSharedPreferences("InsertAcc", Context.MODE_PRIVATE)
        binding.RegistBtn.setOnClickListener {
            val getUser = binding.uss.text.toString()
            val getEmail = binding.getEmail.text.toString()
            val getPassword = binding.pass.text.toString()
            val confirmPassword = binding.cPass.text.toString()

            if (getPassword == confirmPassword){

                val putInput = sharedPreferences.edit()
                putInput.putString("uss", getUser)
                putInput.putString("email", getEmail)
                putInput.putString("pass", getPassword)
                putInput.apply()
                createUserWithEmailAndPassword(email = getEmail, password = getPassword)
            }
        }
    }
    private fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()){ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(context,"Register Berhasil", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context,"Register Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }


}


