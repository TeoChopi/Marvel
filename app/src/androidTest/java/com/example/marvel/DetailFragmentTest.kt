package com.example.marvel

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvel.ui.detail.DetailFragment
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
class DetailFragmentTest {

    private lateinit var scenario: FragmentScenario<DetailFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_Marvel
        )
    }

    @Test
    fun check_if_name_is_visible() {
        onView(withId(R.id.txtName)).check(matches(isDisplayed()))
    }

    @Test
    fun check_if_description_is_visible() {
        onView(withId(R.id.txtDescription)).check(matches(isDisplayed()))
    }

    @Test
    fun check_if_image_is_visible() {
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }
}