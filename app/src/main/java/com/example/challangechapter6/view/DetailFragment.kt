@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused"
)

package com.example.challangechapter6.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challangechapter6.R
import com.example.challangechapter6.databinding.FragmentDetailBinding
import com.example.challangechapter6.model.Result
import com.example.challangechapter6.room.FilmFavorit
import com.example.challangechapter6.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates


@Suppress("DEPRECATION", "unused", "unused", "unused", "unused")
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var selectedMovie: FilmFavorit
    private var isFavorite by Delegates.notNull<Boolean>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getMovie = requireArguments().getInt("id", 0)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.liveDetail.observe(viewLifecycleOwner) { movie ->
            bindMovieData(movie)

        }
        viewModel.getMovieDetail(getMovie)
        setFavoriteListener()
        checkFavoriteMovie(id)
    }


    private fun bindMovieData(movie: Result) {
        viewModel.movie.observe(viewLifecycleOwner) {
            selectedMovie = FilmFavorit(
                it.id,
                it.title,
                it.releaseDate,
                it.posterPath,
                it.voteAverage
            )
            binding.apply {
                Glide.with(this@DetailFragment)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(imageView2)

                judul.text = movie.title
                Tanggal.text = movie.releaseDate
                deskripsi.text = movie.overview
            }
        }
    }


    private fun addToFavorite(movie: FilmFavorit) {
        viewModel.addFavMovie(movie)
        viewModel.favMovie.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "Sukses tambah favorit", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Failed menambah favorit", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setFavoriteListener() {
        binding.floating.apply {
            setOnClickListener {
                isFavorite = if (!isFavorite) {
                    addToFavorite(selectedMovie)
                    binding.floating.setImageResource(R.drawable.baseline_favorite_24)
                    true
                } else {
                    deleteFromFavorite(selectedMovie)
                    binding.floating.setImageResource(R.drawable.baseline_favorite_border_24)
                    false
                }
            }
        }
    }

    private fun deleteFromFavorite(movie: FilmFavorit) {
        viewModel.deleteFavMovie(movie)
        viewModel.deleteFavMovie.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "Sukses menghapus favorit", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Failed menghapus favorit", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun checkFavoriteMovie(movieId: Int) {
        viewModel.isFavoriteMovie(movieId)
        viewModel.isFav.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it) {
                    isFavorite = true
                    binding.floating.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    isFavorite = false
                    binding.floating.setImageResource(R.drawable.baseline_favorite_border_24)
                }
            }
        }
    }
}


