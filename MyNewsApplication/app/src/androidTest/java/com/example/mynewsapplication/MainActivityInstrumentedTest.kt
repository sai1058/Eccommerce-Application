import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mynewsapplication.HomeFragment
import com.example.mynewsapplication.InterFragmentCommunicator
import com.example.mynewsapplication.LoginFragment
import com.example.mynewsapplication.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        mainActivity = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testLoginFragmentDisplayedOnStart() {
        // Verify that the LoginFragment is displayed when the activity starts
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
        onView(withId(R.id.loginFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun testCommunicationBetweenFragments() {
        // Create a mock HomeFragment
        val homeFragment = mock(HomeFragment::class.java)

        // Launch the HomeFragment
        val scenario: FragmentScenario<HomeFragment> = FragmentScenario.launchInContainer(HomeFragment::class.java)

        // Communicate with MainActivity
        mainActivity.communicator(homeFragment)

        // Verify that the HomeFragment was added to the fragment container
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))

        // Verify that the fragment transaction occurred
        verify(homeFragment).onResume()
    }

    @Test
    fun testFragmentNavigation() {
        // Simulate clicking a button to navigate to the HomeFragment
        onView(withId(R.id.someButton)).perform(click())

        // Verify that the HomeFragment is displayed
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))
    }
}
