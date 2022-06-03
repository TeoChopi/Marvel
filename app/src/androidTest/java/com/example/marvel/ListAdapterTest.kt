package com.example.marvel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.marvel.repository.model.Data
import com.example.marvel.repository.model.ListResponse
import com.example.marvel.repository.model.ResultsItem
import com.example.marvel.ui.list.ListItemDelegate
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListAdapterTest {

    private lateinit var scenario: FragmentScenario<ListScenarioFragment>

    @Before
    fun launchFragment() {
        scenario = launchFragmentInContainer()

        scenario.onFragment {
            it.setResume()
        }
    }

    @Test
    fun check_if_item_label_is_not_empty() {
        Espresso.onView(ViewMatchers.withId(ListScenarioFragment.RecyclerView_ID))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(RecyclerViewMatcher(ListScenarioFragment.RecyclerView_ID)
            .atPositionOnView(0, R.id.txtItemDescription))
            .check(ViewAssertions.matches(ViewMatchers.withText("3-D Man")))
    }

    @Test
    fun check_if_item_label_is_empty() {
        Espresso.onView(ViewMatchers.withId(ListScenarioFragment.RecyclerView_ID))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(RecyclerViewMatcher(ListScenarioFragment.RecyclerView_ID)
            .atPositionOnView(1, R.id.txtItemDescription))
            .check(ViewAssertions.matches(ViewMatchers.withText("A-Bomb (HAS)")))
    }

    @Test
    fun check_if_item_display_is_displayed() {
        Espresso.onView(ViewMatchers.withId(ListScenarioFragment.RecyclerView_ID))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(RecyclerViewMatcher(ListScenarioFragment.RecyclerView_ID)
            .atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    class ListScenarioFragment : Fragment(), ListItemDelegate {

        companion object {
            const val RecyclerView_ID = 11111
        }

        private val mContext = InstrumentationRegistry.getInstrumentation().targetContext
        private lateinit var recyclerView: RecyclerView
        private lateinit var recyclerViewAdapter: com.example.marvel.ui.list.ListAdapter

        private val listResponse =
            ListResponse(
                "© 2022 MARVEL",
                200,
                Data(
                    1562,
                    0,
                    100,
                    100,
                    listOf(
                        ResultsItem(
                            null,
                            null,
                            null,
                            null,
                            null,
                            "3-D Man",
                            "",
                            "2014-04-29T14:18:17-0400",
                            1011334,
                            "http://gateway.marvel.com/v1/public/characters/1011334",
                            null
                        ),
                        ResultsItem(
                            null,
                            null,
                            null,
                            null,
                            null,
                            "A-Bomb (HAS)",
                            "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ",
                            "2013-09-18T15:54:04-0400",
                            1017100,
                            "http://gateway.marvel.com/v1/public/characters/1017100",
                            null
                        )
                    )
                ),
                "<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2022 MARVEL</a>",
                "Data provided by Marvel. © 2022 MARVEL",
                "9f35bb38eacf098955f74dc38cc85bd71ee64fab",
                "Ok"
            )

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View {
            recyclerView = RecyclerView(mContext)
            recyclerView.id = RecyclerView_ID
            return recyclerView
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAdapter = com.example.marvel.ui.list.ListAdapter(this)
            recyclerView.adapter = recyclerViewAdapter
        }

        fun setResume() {
            listResponse.data?.let {
                recyclerViewAdapter.setResume(it.results)
            }
        }

        override fun onItemClick(item: ResultsItem) {

        }
    }
}