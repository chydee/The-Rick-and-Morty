package com.chidi.therickandmorty.presentation.view.characterDetails

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.chidi.therickandmorty.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CharacterDetailsFragmentTest {

    @get:Rule
    val scenario = launchFragmentInContainer<CharacterDetailsFragment>()

    @Test
    fun test_isFragmentInDisplay() {
        // Check if Fragment is visible
        Espresso.onView(withId(R.id.characterDetailsScreen))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    fun test_isCharacterImageViewDisplayed() {
        Espresso.onView(withId(R.id.characterImage))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    fun test_isSpeciesLabelAndSpeciesInDisplay() {
        Espresso.onView(withId(R.id.characterSpeciesLabel))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterSpeciesText))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterSpeciesLabel))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.label_species)))
    }

    fun test_isStatusLabelAndStatusInDisplay() {
        Espresso.onView(withId(R.id.characterStatusLabel))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterStatusText))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterStatusLabel))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.label_status)))
    }

    fun test_isTypeLabelAndTypeInDisplay() {
        Espresso.onView(withId(R.id.characterTypeLabel))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterTypeText))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterTypeLabel))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.label_type)))
    }

    fun test_isGenderLabelAndGenderInDisplay() {
        Espresso.onView(withId(R.id.characterGenderLabel))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterGenderText))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.characterGenderLabel))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.label_gender)))
    }

    @Test
    fun test_backNavigation_toCharactersFragment() {
        Espresso.pressBack()

        // Confirm CharactersFragment in view
        Espresso.onView(withId(R.id.charactersListFragment)).check(ViewAssertions.matches(isDisplayed()))
    }
}