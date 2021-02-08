package truebeans.fyruz.meteofy.UI


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import truebeans.fyruz.meteofy.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.fab),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val sheetInputEditText = onView(
            allOf(
                withId(R.id.textInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        sheetInputEditText.perform(replaceText("Firenze"), closeSoftKeyboard())

        val sheetButton = onView(
            allOf(
                withText("OK"),
                childAtPosition(
                    allOf(
                        withId(R.id.btnPositiveContainer),
                        childAtPosition(
                            withId(R.id.buttons),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        sheetButton.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.main_recycler),
                childAtPosition(
                    withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView = onView(
            allOf(
                withId(R.id.city_name), withText("Florence"),
                withParent(withParent(withId(R.id.cardView))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Florence")))

        val imageButton = onView(
            allOf(
                withId(R.id.fab),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java))),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val frameLayout = onView(
            allOf(
                withId(R.id.cardView),
                withParent(
                    allOf(
                        withId(R.id.main_recycler),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
