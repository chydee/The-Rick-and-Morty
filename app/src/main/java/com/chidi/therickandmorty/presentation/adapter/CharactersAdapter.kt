package com.chidi.therickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chidi.therickandmorty.databinding.ItemCharacterBinding
import com.chidi.therickandmorty.domain.Character

class CharactersAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private lateinit var listener: OnCharacterClickListener

    interface OnCharacterClickListener {
        fun onItemClick(characterId: Int)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.bind(character)
        holder.itemView.setOnClickListener {
            listener.onItemClick(character.id)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    internal fun submitList(characters: List<Character>) {
        differ.submitList(characters)
    }

    internal fun setOnClickListener(listener: OnCharacterClickListener) {
        this.listener = listener
    }
}