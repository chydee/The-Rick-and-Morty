package com.chidi.therickandmorty.data.repository

import com.chidi.therickandmorty.data.local.CharacterDao
import com.chidi.therickandmorty.data.remote.RemoteDataSource
import com.chidi.therickandmorty.presentation.utils.performGetOperation
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: CharacterDao
) {
    fun character(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun characters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getAllCharacters() },
        saveCallResult = { localDataSource.insert(it.results) }
    )
}