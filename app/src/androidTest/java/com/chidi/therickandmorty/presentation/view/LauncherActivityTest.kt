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
    fun test_isAppLogoInView() {
        onView(ViewMatchers.withId(R.id.appLogo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isLaunchScreenQuoteTextViewInView() {
        onView(ViewMatchers.withId(R.id.appQuoteTextView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isTheLaunchScreenQuoteTextDisplayed() {
        onView(ViewMatchers.withId(R.id.appQuoteTextView))
            .check(matches(withText(R.string.launcher_quote)))
    }


}