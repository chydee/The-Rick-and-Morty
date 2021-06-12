package com.chidi.therickandmorty.data.remote

import com.chidi.therickandmorty.domain.Character
import com.chidi.therickandmorty.domain.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /**
     *  GET request
     *
     *  Sends a request to the character endpoint which returns
     *  a list of all characters
     */
    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterList>

    /**
     *  GET request
     *
     *  Sends a request with the Character's id
     *  which returns information about the Character in question
     */
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}