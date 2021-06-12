package com.chidi.therickandmorty.presentation.view.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.chidi.therickandmorty.data.repository.AppRepository
import com.chidi.therickandmorty.domain.Character
import com.chidi.therickandmorty.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private val _characterId = MutableLiveData<Int>()

    private val _character = _characterId.switchMap { id ->
        repository.character(id)
    }
    val character: LiveData<Resource<Character>> = _character


    /**
     * Retrieve the character details
     */
    fun read(id: Int) {
        _characterId.value = id
    }
}