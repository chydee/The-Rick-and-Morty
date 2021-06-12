package com.chidi.therickandmorty.presentation.view.characters

import androidx.lifecycle.ViewModel
import com.chidi.therickandmorty.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    // Get All Characters
    val characters = repository.characters()
}