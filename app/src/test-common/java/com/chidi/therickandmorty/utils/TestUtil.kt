package com.chidi.therickandmorty.utils

import com.chidi.therickandmorty.domain.Character

object TestUtil {
    fun createCharacter() = Character(
        id = 2,
        created = "2017-11-04T18:50:21.651Z",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        url = "https://rickandmortyapi.com/api/character/2",
        name = "Morty Smith",
        species = "Human",
        status = "Alive",
        type = ""
    )
}