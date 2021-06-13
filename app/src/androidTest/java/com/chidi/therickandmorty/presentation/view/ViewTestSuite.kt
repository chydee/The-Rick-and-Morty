package com.chidi.therickandmorty.presentation.view

import com.chidi.therickandmorty.presentation.view.characterDetails.CharacterDetailsFragmentTest
import com.chidi.therickandmorty.presentation.view.characters.CharactersFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    LauncherActivityTest::class,
    MainActivityTest::class,
    CharactersFragmentTest::class,
    CharacterDetailsFragmentTest::class
)
class ViewTestSuite