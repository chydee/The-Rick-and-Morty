package com.chidi.therickandmorty.core.di

import android.content.Context
import com.chidi.therickandmorty.data.local.CharacterDao
import com.chidi.therickandmorty.data.local.RickAndMortyDB
import com.chidi.therickandmorty.data.remote.RemoteDataSource
import com.chidi.therickandmorty.data.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = RickAndMortyDB.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: RickAndMortyDB) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: CharacterDao
    ) =
        AppRepository(remoteDataSource, localDataSource)
}