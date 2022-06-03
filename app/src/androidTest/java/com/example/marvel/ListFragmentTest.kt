package com.example.marvel

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvel.ui.list.ListFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListFragmentTest {

    private lateinit var scenario: FragmentScenario<ListFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_Marvel
        )
    }

    @Test
    fun check_if_recyclerview_is_visible() {
        onView(withId(R.id.recyclerList)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun check_if_progressbar_is_visible() {
        onView(withId(R.id.progressBar)).check(ViewAssertions.matches(isDisplayed()))
    }
}