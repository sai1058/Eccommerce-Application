import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mynewsapplication.DetailedNewsFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailedNewsFragmentInstrumentedTest {

    @Test
    fun testFragmentViewsDisplayedWithMockData() {
        // Mock data
        val mockTitle = "Sample Title"
        val mockDescription = "Sample Description"
        val mockAuthor = "Sample Author"
        val mockPublished = "Sample Published Date"
        val mockImageUrl = "https://example.com/sample-image.jpg"
        val mockContent = "Sample Content"

        // Create a bundle with mock data
        val args = DetailedNewsFragment.Builder()
            .setTitle(mockTitle)
            .setDescription(mockDescription)
            .setAuthor(mockAuthor)
            .setPublished(mockPublished)
            .setImage(mockImageUrl)
            .setContent(mockContent)
            .build()
            .toBundle()

        // Launch the fragment with the mock data
        val scenario = launchFragmentInContainer<DetailedNewsFragment>(args)

        // Verify that the fragment's views are displayed correctly
        onView(withId(R.id.detailedTitleTextView)).check(matches(withText(mockTitle)))
        onView(withId(R.id.detailedDescriptionTextView)).check(matches(withText(mockDescription)))
        onView(withId(R.id.detailedAuthorTextView)).check(matches(withText(mockAuthor)))
        onView(withId(R.id.detailedPublishedDateTextView)).check(matches(withText(mockPublished)))
        onView(withId(R.id.detailedContentTextView)).check(matches(withText(mockContent)))

        // Note: Testing image loading with Glide in Espresso tests is more complex.
        // You may need to use custom ViewMatchers and IdlingResources for this.
    }
}
