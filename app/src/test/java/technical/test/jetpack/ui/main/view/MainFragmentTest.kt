package technical.test.jetpack.ui.main.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import technical.test.jetpack.R
import technical.test.jetpack.ui.common.MainActivity

@Config(manifest=Config.NONE)
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Before
    fun setUp() {
    }

    @Test
    fun showHerosList() {
        onView(withId(R.id.contactList)).check(
            matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun showLoading() {
        onView(withId(R.id.progressBar)).check(
            matches(ViewMatchers.isDisplayed()))
    }

}