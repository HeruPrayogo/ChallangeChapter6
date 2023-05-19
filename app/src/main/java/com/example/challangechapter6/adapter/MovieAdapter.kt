@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused")

package com.example.challangechapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangechapter6.databinding.ItemMovieBinding
import com.example.challangechapter6.model.Result


@Suppress("unused", "unused")
class MovieAdapter (var listMovie : List<Result>,
                    private var onItemClick: ((Result) -> Unit)? = null ) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder (var binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.Judul.text = listMovie[position].originalTitle
        holder.binding.Rating.text = listMovie[position].voteAverage.toString()
        holder.binding.Tanggal.text = listMovie[position].releaseDate
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500${listMovie[position].posterPath}").into(holder.binding.Poster)
        holder.binding.card.setOnClickListener{
            onItemClick?.invoke(listMovie[position])
        }

    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}