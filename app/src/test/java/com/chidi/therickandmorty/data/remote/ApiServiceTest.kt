package com.chidi.therickandmorty.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chidi.therickandmorty.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ApiServiceTest : ApiAbstract<ApiService>() {

    @get: Rule
    val coroutinesRule = MainCoroutinesRule()

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ApiService

    @Before
    fun initService() {
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun test_getCharactersFromNetwork() = runBlocking {
        enqueueResponse("/Characters.json")
        val response = (service.getAllCharacters())
        val responseBody = response.body()
        mockWebServer.takeRequest()

        val loaded = responseBody?.results?.get(0)
        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(loaded?.name, CoreMatchers.`is`("Rick Sanchez"))
        MatcherAssert.assertThat(loaded?.status, CoreMatchers.`is`("Alive"))
        MatcherAssert.assertThat(loaded?.species, CoreMatchers.`is`("Human"))
        MatcherAssert.assertThat(loaded?.type, CoreMatchers.`is`(""))
        MatcherAssert.assertThat(loaded?.gender, CoreMatchers.`is`("Male"))
        MatcherAssert.assertThat(loaded?.image, CoreMatchers.`is`("https://rickandmortyapi.com/api/character/avatar/1.jpeg"))
        MatcherAssert.assertThat(loaded?.url, CoreMatchers.`is`("https://rickandmortyapi.com/api/character/1"))
        MatcherAssert.assertThat(loaded?.created, CoreMatchers.`is`("2017-11-04T18:48:46.250Z"))
    }
}