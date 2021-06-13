package com.chidi.therickandmorty.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chidi.therickandmorty.MainCoroutinesRule
import com.chidi.therickandmorty.data.local.CharacterDao
import com.chidi.therickandmorty.data.local.RickAndMortyDB
import com.chidi.therickandmorty.data.remote.ApiService
import com.chidi.therickandmorty.data.remote.RemoteDataSource
import com.chidi.therickandmorty.domain.Character
import com.chidi.therickandmorty.utils.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.bytebuddy.matcher.ElementMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Unit tests for the implementation of [AppRepository]
 */
@ExperimentalCoroutinesApi
class AppRepositoryTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: AppRepository
    private lateinit var database: RickAndMortyDB
    private lateinit var service: ApiService

    private lateinit var remoteDataSource: RemoteDataSource

    private val _character = MutableLiveData<Character>()
    private val character: LiveData<Character>
        get() = _character

    @Before
    fun setUp() {
        database = Mockito.mock(RickAndMortyDB::class.java)
        service = Mockito.mock(ApiService::class.java)
        remoteDataSource = RemoteDataSource(service)
        repository = AppRepository(remoteDataSource, database.characterDao())

        _character.value = TestUtil.createCharacter()
    }

    @Test
    fun test_getCharacter() = runBlocking {
        //Stub out database to return a mock dao.
        val dao = Mockito.mock(CharacterDao::class.java)
        Mockito.`when`(database.characterDao()).thenReturn(dao)

        Mockito.`when`(dao.getCharacter(2)).thenReturn(character)

        //Method under test.


        //Verify data in the result.
        assertThat(repository.character(2).value?.data.toString(), `is`(TestUtil.createCharacter()))

    }
}