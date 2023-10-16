package com.example.ecommerceapplication

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.example.ecommerceapplication.activity.MainActivity
import com.example.ecommerceapplication.fragments.PaymentDoneFragment
import org.junit.Before
import org.junit.Test

@LargeTest
class PaymentDoneFragmentTest {

    @Before
    fun setUp() {

       launchFragmentInContainer<PaymentDoneFragment>()
    }

    @Test
    fun testClickOkButton_NavigateToNewFragment() {

        onView(withId(R.id.clickOk)).check(matches(isDisplayed()))

        onView(withId(R.id.clickOk)).perform(click())

        onView(withId(R.id.fragment_container_one)).check(matches(isDisplayed()))
    }

    @Test
    fun testClickOkButton_LaunchActivity() {

        onView(withId(R.id.clickOk)).check(matches(isDisplayed()))

        onView(withId(R.id.clickOk)).perform(click())

        intended(hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun testClickOkButton_ShowToastMessage() {

        onView(withId(R.id.clickOk)).check(matches(isDisplayed()))

        onView(withId(R.id.clickOk)).perform(click())

    }
}

