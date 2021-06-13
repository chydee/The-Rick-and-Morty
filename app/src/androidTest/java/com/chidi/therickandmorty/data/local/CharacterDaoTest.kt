package com.chidi.therickandmorty.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.chidi.therickandmorty.utils.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
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

        val loaded = characterDao.getCharacter(characterRE.id)

        MatcherAssert.assertThat(loaded.id, `is`(article.id))
        MatcherAssert.assertThat(loaded.url, `is`(article.url))
        MatcherAssert.assertThat(loaded.author, `is`(article.author))
        MatcherAssert.assertThat(loaded.title, `is`(article.title))
        MatcherAssert.assertThat(loaded.description, `is`(article.description))
        MatcherAssert.assertThat(loaded.imgUrl, `is`(article.imgUrl))
        MatcherAssert.assertThat(loaded.content, `is`(article.content))
        MatcherAssert.assertThat(loaded.source.name, `is`(article.source.name))
        MatcherAssert.assertThat(loaded.category, `is`(article.category))
        MatcherAssert.assertThat(loaded.language, `is`(article.language))
        MatcherAssert.assertThat(loaded.date, `is`(article.date))
    }

}