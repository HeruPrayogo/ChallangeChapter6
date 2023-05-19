package com.example.challangechapter6.view

import android.annotation.SuppressLint
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
import com.example.challangechapter6.R
import com.example.challangechapter6.adapter.MovieAdapter
import com.example.challangechapter6.databinding.FragmentHomeBinding
import com.example.challangechapter6.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        sharedPreferences = requireContext().getSharedPreferences("InsertAcc", AppCompatActivity.MODE_PRIVATE)
        val getUss = sharedPreferences.getString("uss", "")
        binding.welcome.text ="Welcome, $getUss"
        binding.imageView.setOnClickListener {
            val giveUser = sharedPreferences.edit()
            giveUser.putString("uss", getUss)
            giveUser.apply()
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.fav.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getData(){
        movieAdapter = MovieAdapter(emptyList()){movie ->
            val bundle = Bundle()
            bundle.putInt("id", movie.id)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
        val getVM = ViewModelProvider(this)[MovieViewModel::class.java]
        getVM.getMovie()
        binding.rcvcon.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcvcon.adapter = movieAdapter
        getVM.liveDataMovie.observe(viewLifecycleOwner) { movie ->
            movieAdapter.listMovie = movie
            movieAdapter.notifyDataSetChanged()
        }

    }


}