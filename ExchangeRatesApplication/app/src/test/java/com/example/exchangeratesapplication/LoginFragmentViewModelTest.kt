import android.service.autofill.UserData
import com.example.exchangeratesapplication.LoginFragmentViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginFragmentViewModelTest {
    private var loginFragmentViewModel : LoginFragmentViewModel = LoginFragmentViewModel()
    @Test
    fun `test setData and getData`() {
        loginFragmentViewModel.setData("Saikumar", "h1234")
        assertEquals("Saikumar", loginFragmentViewModel.getData())
    }
    @Test
    fun `test isValid with valid data`() {
        loginFragmentViewModel.setData("Saikumar", "h1234")
        assertTrue(loginFragmentViewModel.isValid())
    }

    @Test
    fun `test isValid with invalid username`() {
        loginFragmentViewModel.setData("John", "h1234")
        assertFalse(loginFragmentViewModel.isValid())
    }

    @Test
    fun `test isValid with invalid password`() {
        loginFragmentViewModel.setData("Saikumar", "password123")
        assertFalse(loginFragmentViewModel.isValid())
    }

    @Test
    fun `test isValid with both invalid username and password`() {
        loginFragmentViewModel.setData("John", "password123")
        assertFalse(loginFragmentViewModel.isValid())
    }

    @Test
    fun `test isValid with empty data`() {
        assertFalse(loginFragmentViewModel.isValid())
    }

    @Test
    fun `test isValid with null data`() {
        assertFalse(loginFragmentViewModel.isValid())
    }
}