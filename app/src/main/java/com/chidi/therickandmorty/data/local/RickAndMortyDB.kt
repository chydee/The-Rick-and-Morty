package com.chidi.therickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chidi.therickandmorty.domain.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class RickAndMortyDB : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var instance: RickAndMortyDB? = null

        fun getDatabase(context: Context): RickAndMortyDB =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, RickAndMortyDB::class.java, "Rick_and_Morty")
                .fallbackToDestructiveMigration()
                .build()
    }
}