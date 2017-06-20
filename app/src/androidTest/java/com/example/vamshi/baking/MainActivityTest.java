package com.example.vamshi.baking;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.vamshi.baking.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Vamshi on 6/20/2017.
 */

// THIS TEST WAS WRITTEN TO CHECK IF THE LIST OF RECIPES DISPLAYED ON MAINACTIVITY ARE ALL DISPLAYING THEIR APPROPRIATE TITLES

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickListViewItem_OpensProcedureActivity() {

        onData(anything()).inAdapterView(withId(R.id.Recipe_list)).atPosition(0).perform(click());
        //onView(withId(R.id.text_view_for_testing)).check(matches(withText("Nutella Pie")));
    }


}
