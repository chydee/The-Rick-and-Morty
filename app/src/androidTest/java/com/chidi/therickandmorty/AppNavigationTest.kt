package com.chidi.therickandmorty

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.chidi.therickandmorty.core.di.NetworkModule
import com.chidi.therickandmorty.data.repository.AppRepository
import com.chidi.therickandmorty.presentation.utils.EspressoUriIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Large instrumented test for navigation between screens.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class AppNavigationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: AppRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoUriIdlingResource.uriIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoUriIdlingResource.uriIdlingResource)
    }
}