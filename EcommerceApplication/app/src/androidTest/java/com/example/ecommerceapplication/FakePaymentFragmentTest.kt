package com.example.ecommerceapplication
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isSystemAlertWindow
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.ecommerceapplication.activity.MainActivity
import com.example.ecommerceapplication.fragments.FakePaymentFragment
import org.junit.Rule
import org.junit.Test

@LargeTest
class FakePaymentFragmentTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testValidUser() {
        launchFragmentInContainer<FakePaymentFragment>()

        onView(withId(R.id.cardNumber)).perform(typeText("1234567890123456"))
        onView(withId(R.id.expiryDate)).perform(typeText("1225"))
        onView(withId(R.id.enterCvv)).perform(typeText("123"))
        closeSoftKeyboard()

        onView(withId(R.id.paymentBtn)).perform(click())

        onView(withId(R.id.payment_done_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun testInvalidUser() {
        launchFragmentInContainer<FakePaymentFragment>()

        onView(withId(R.id.cardNumber)).perform(typeText("12345")) // Invalid card number
        onView(withId(R.id.expiryDate)).perform(typeText("12")) // Invalid expiry date
        onView(withId(R.id.enterCvv)).perform(typeText("1")) // Invalid CVV

        closeSoftKeyboard()

        onView(withId(R.id.paymentBtn)).perform(click())

        onView(withText("Enter Valid Card Details"))
            .inRoot(isSystemAlertWindow())
            .check(matches(isDisplayed()))
    }
}

