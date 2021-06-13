package com.chidi.therickandmorty.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chidi.therickandmorty.MainCoroutinesRule
import com.chidi.therickandmorty.data.local.RickAndMortyDB
import com.chidi.therickandmorty.data.remote.ApiService
import com.chidi.therickandmorty.data.remote.RemoteDataSource
import com.chidi.therickandmorty.data.repository.AppRepository
import com.chidi.therickandmorty.domain.Character
import com.chidi.therickandmorty.presentation.view.characterDetails.CharacterDetailsViewModel
import com.chidi.therickandmorty.utils.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterDetailsViewModelTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: AppRepository
    private lateinit var viewModel: CharacterDetailsViewModel

    private lateinit var remoteDataSource: RemoteDataSource

    private val _character = MutableLiveData<Character>()
    private val character: LiveData<Character>
        get() = _character
    private val database = Mockito.mock(RickAndMortyDB::class.java, Mockito.RETURNS_DEEP_STUBS)
    private val service = Mockito.mock(ApiService::class.java)

    @Before
    fun setup() {
        _character.value = TestUtil.createCharacter()
        remoteDataSource = RemoteDataSource(service)
        repository = AppRepository(remoteDataSource, database.characterDao())
        viewModel = CharacterDetailsViewModel(repository)
    }

    @Test
    fun test_fetchCharacterTest() = runBlockingTest {
        Mockito.`when`(database.characterDao().getCharacter(2)).thenReturn(character)
        viewModel.read(id = 2)
        val c = TestUtil.createCharacter()
        val value = viewModel.character.value

        MatcherAssert.assertThat(value?.data?.id, `is`(character.value?.id))
        MatcherAssert.assertThat(value?.data?.name, `is`(character.value?.name))
        MatcherAssert.assertThat(value?.data?.status, `is`(character.value?.status))
        MatcherAssert.assertThat(value?.data?.created, `is`(character.value?.created))
        MatcherAssert.assertThat(value?.data?.type, `is`(character.value?.type))
        MatcherAssert.assertThat(value?.data?.url, `is`(character.value?.url))
        MatcherAssert.assertThat(value?.data?.image, `is`(character.value?.image))
        MatcherAssert.assertThat(value?.data?.species, `is`(character.value?.species))

    }
}