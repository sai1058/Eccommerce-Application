import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mynewsapplication.HomeFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    private lateinit var navController: NavController

    @Before
    fun setUp() {

        navController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<HomeFragment>()

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun testRecyclerViewDisplayed() {

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchButton() {

        onView(withId(R.id.enter_search)).perform(typeText("android"), closeSoftKeyboard())


        onView(withId(R.id.btn_search)).perform(click())


        verify(navController).navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailedNewsFragment(
                "Sample Title",
                "Sample Image URL",
                "Sample Description",
                "Sample Author",
                "Sample Published Date",
                "Sample Content"
            )
        )
    }
}
