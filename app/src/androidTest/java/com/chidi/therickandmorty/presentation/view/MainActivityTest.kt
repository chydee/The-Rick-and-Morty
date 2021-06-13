package com.chidi.therickandmorty.presentation.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chidi.therickandmorty.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_checkIfActivityIsInDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.mainActivity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_checkIfToolBarIsInDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.toolbar))
            .check(ViewAssertions.matches(withText(R.string.app_name)))
    }

    @Test
    fun test_checkIfFragmentContainerIsInDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.nav_host_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}