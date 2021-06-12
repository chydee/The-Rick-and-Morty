package com.chidi.therickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chidi.therickandmorty.domain.Character

@Dao
interface CharacterDao {

    /**
     *  Get all Characters from the characters table
     */
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<Character>>

    /**
     *  Get a single character with the specified id from the character table
     */
    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Character>

    /**
     *  Insert all characters into the character table
     *  if there's a conflict then replace  OnConflictStrategy.REPLACE
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<Character>)

    /**
     *  Insert a character into the character table,
     *  if the character is already in existence then replace
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)


}