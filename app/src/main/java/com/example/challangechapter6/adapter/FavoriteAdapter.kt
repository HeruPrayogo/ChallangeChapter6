@file:Suppress("unused")

package com.example.challangechapter6.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangechapter6.databinding.ItemFavBinding
import com.example.challangechapter6.room.FilmFavorit

@Suppress("unused", "unused", "unused", "unused", "unused")
class FavoriteAdapter(
    private var listFav: List<FilmFavorit>,
    private var onItemClick: ((FilmFavorit) -> Unit)? = null) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemFavBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFav.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.Judul.text = listFav[position].title
        holder.binding.Rating.text = listFav[position].voteAverage.toString()
        holder.binding.Tanggal.text = listFav[position].releasedate
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500${listFav[position].posterPath}").into(holder.binding.Poster)
        holder.binding.card.setOnClickListener{
            onItemClick?.invoke(listFav[position])
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setListFilmFavorite(list : List<FilmFavorit>){
        listFav = list
        notifyDataSetChanged()
    }


}