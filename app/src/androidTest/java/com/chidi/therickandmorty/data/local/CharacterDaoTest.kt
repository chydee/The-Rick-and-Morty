package com.chidi.therickandmorty.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.chidi.therickandmorty.utils.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class CharacterDaoTest : LocalDatabase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var characterDao: CharacterDao

    @Before
    fun setUp() {
        characterDao = database.characterDao()
    }

    @Test
    @Throws(Exception::class)
    fun test_insertAndLoad() = runBlockingTest {
        val character = TestUtil.createCharacter()
        val characters = listOf(character)
        characterDao.insert(characters)

        val loaded = characterDao.getAllCharacters()

        MatcherAssert.assertThat(loaded, CoreMatchers.notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun test_insertAndLoadById() = runBlockingTest {
        val character = TestUtil.createCharacter()
        val characters = listOf(character)
        characterDao.insert(characters)

        val loaded = characterDao.getCharacter(character.id)

        MatcherAssert.assertThat(loaded.value?.id, `is`(character.id))
        MatcherAssert.assertThat(loaded.value?.url, `is`(character.url))
        MatcherAssert.assertThat(loaded.value?.name, `is`(character.name))
        MatcherAssert.assertThat(loaded.value?.gender, `is`(character.gender))
        MatcherAssert.assertThat(loaded.value?.status, `is`(character.status))
        MatcherAssert.assertThat(loaded.value?.species, `is`(character.species))
        MatcherAssert.assertThat(loaded.value?.image, `is`(character.image))
        MatcherAssert.assertThat(loaded.value?.created, `is`(character.created))
        MatcherAssert.assertThat(loaded.value?.url, `is`(character.url))
    }

}