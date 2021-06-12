package com.chidi.therickandmorty.data.repository

import com.chidi.therickandmorty.data.local.CharacterDao
import com.chidi.therickandmorty.data.remote.RemoteDataSource
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: CharacterDao
)