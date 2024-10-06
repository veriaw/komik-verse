package com.dicoding.komikverse

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.komikverse.databinding.ItemRowComicBinding


class ListComicAdapter(private val listComics:ArrayList<Comic>): RecyclerView.Adapter<ListComicAdapter.ListViewHolder>() {
    private lateinit var binding: ItemRowComicBinding
    class ListViewHolder(var binding: ItemRowComicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemRowComicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listComics.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val(name,genre,description,photo,sinopsis)=listComics[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(binding.imgItemPhoto)
        holder.binding.tvItemName.text=name
        holder.binding.tvItemGenre.text=genre
        holder.binding.tvItemDescription.text=description
        Log.d("Adapter", "Sending comic: ${name}, ${genre}, ${photo}")
        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(holder.itemView.context, ComicDetail::class.java)
            moveWithObjectIntent.putExtra(ComicDetail.EXTRA_COMIC, listComics[holder.adapterPosition])
            Log.d("Adapter", "Mengirim Data")
            holder.itemView.context.startActivity(moveWithObjectIntent)
        }
    }


}