package com.example.challangechapter6.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangechapter6.adapter.FavoriteAdapter
import com.example.challangechapter6.databinding.FragmentFavoriteBinding
import com.example.challangechapter6.viewmodel.FavoritViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoritViewModel: FavoritViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteAdapter = FavoriteAdapter(ArrayList())
        favoritViewModel = ViewModelProvider(this)[FavoritViewModel::class.java]
        getAllFavMovies()

    }
    private fun getAllFavMovies(){
        favoritViewModel.getAllFavoriteMovie()
        favoritViewModel.listMovie.observe(viewLifecycleOwner){
            if(it != null){
                binding.rcvcon.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvcon.adapter = FavoriteAdapter(it)
            }
        }
    }



}