package com.chidi.therickandmorty.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiService) : BaseDataSource() {
    suspend fun getAllCharacters() = getResult { service.getAllCharacters() }
    suspend fun getCharacter(id: Int) = getResult { service.getCharacter(id) }
}