package com.chidi.therickandmorty.presentation.view.characters

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.chidi.therickandmorty.R
import com.chidi.therickandmorty.domain.Character
import com.chidi.therickandmorty.presentation.adapter.CharacterViewHolder
import com.chidi.therickandmorty.utils.TestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CharactersFragmentTest {
    @get:Rule
    val scenario = launchFragmentInContainer<CharactersFragment>()

    private lateinit var characters: List<Character>

    private val LIST_ITEM_IN_TEST = 1

    @Before
    fun setUp() {
        characters = listOf(TestUtil.createCharacter(), TestUtil.createCharacter())
    }

    @Test
    fun test_isFragmentInDisplay() {
        // Check if Fragment is visible
        Espresso.onView(withId(R.id.charactersListFragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun test_isEmptyStateViewInvisibleByDefault() {
        Espresso.onView(withId(R.id.charactersRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun text_isEmptyStateTextViewNotInView() {
        Espresso.onView(withId(R.id.emptyStateTextView))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun test_selectListItem_isCharacterDetailFragmentVisible() {
        // Click list item #LIST_ITEM_IN_TEST
        Espresso.onView(withId(R.id.charactersRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CharacterViewHolder>(LIST_ITEM_IN_TEST, ViewActions.click()))

        // Confirm nav to DetailFragment and display title
        Espresso.onView(withId(R.id.characterDetailsScreen)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun test_backNavigation_toCharactersFragment() {
        // Click list item #LIST_ITEM_IN_TEST
        Espresso.onView(withId(R.id.charactersRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CharacterViewHolder>(LIST_ITEM_IN_TEST, ViewActions.click()))

        // Confirm nav to CharacterDetailFragment and display title
        Espresso.onView(withId(R.id.characterDetailsScreen)).check(ViewAssertions.matches(isDisplayed()))

        Espresso.pressBack()

        // Confirm CharactersFragment in view
        Espresso.onView(withId(R.id.charactersRecyclerView)).check(ViewAssertions.matches(isDisplayed()))
    }
}