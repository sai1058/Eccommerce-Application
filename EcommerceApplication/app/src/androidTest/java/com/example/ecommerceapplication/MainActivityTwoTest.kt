package com.example.ecommerceapplication

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ecommerceapplication.activity.MainActivity
import com.example.ecommerceapplication.fragments.ProductDetailedFragment
import com.example.ecommerceapplication.viewModels.CartScreenViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTwoTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testDisplayHomeScreenFragment() {
        onView(withId(R.id.fragment_container_main))
            .check(matches(isDisplayed()))

        onView(withId(R.id.fragment_container_main))
            .check(matches(hasDescendant(withId(R.id.fragment_container_one))))
    }

    @Test
    fun testFragmentReplacement() {
        val bundle = Bundle()
        bundle.putString("key", "value")

        val expectedFragment = ProductDetailedFragment()
        expectedFragment.arguments = bundle

        activityScenarioRule.scenario.onActivity { activity ->
            activity.replaceCurrentFragment(expectedFragment)
        }

        onView(withId(R.id.fragment_container_main))
            .check(matches(hasDescendant(withId(R.id.fragment_container_two))))
    }

    @Test
    fun testViewModelInitialization() {
        activityScenarioRule.scenario.onActivity { activity ->
            val cartScreen = activity.cartScreen

            assertNotNull(cartScreen)
            assertTrue(cartScreen is CartScreenViewModel)
        }
    }
}
