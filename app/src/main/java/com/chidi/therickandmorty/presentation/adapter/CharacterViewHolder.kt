package com.chidi.therickandmorty.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.chidi.therickandmorty.databinding.ItemCharacterBinding
import com.chidi.therickandmorty.domain.Character

class CharacterViewHolder(private var binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.character = character
        binding.executePendingBindings()
    }
}