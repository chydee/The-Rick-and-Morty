package com.chidi.therickandmorty.presentation.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chidi.therickandmorty.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {


    @get:Rule
    var activityRule: ActivityScenarioRule<LauncherActivity> = ActivityScenarioRule(LauncherActivity::class.java)


    @Test
    fun checkIfActivityIsInDisplayed() {
        onView(ViewMatchers.withId(R.id.launcherActivity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun check_if_the_app_title_is_in_view() {
        onView(ViewMatchers.withId(R.id.appNameLogoTextView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun check_if_the_launchScreen_quote_is_displayed() {
        onView(ViewMatchers.withId(R.id.appQuoteTextView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isTheLaunchScreenTitleTextDisplayed() {
        onView(ViewMatchers.withId(R.id.appNameLogoTextView))
            .check(matches(withText(R.string.app_title)))
    }

    @Test
    fun test_isTheLaunchScreenQuoteTextDisplayed() {
        onView(ViewMatchers.withId(R.id.appNameLogoTextView))
            .check(matches(withText(R.string.launcher_quote)))
    }


}