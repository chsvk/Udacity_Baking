package com.example.vamshi.baking;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.vamshi.baking.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Vamshi on 6/20/2017.
 */

// THIS TEST WAS WRITTEN TO CHECK IF THE LIST OF RECIPES DISPLAYED ON MAINACTIVITY ARE ALL DISPLAYING THEIR APPROPRIATE TITLES
    // TEST ALSO INCLUDES TO CHECK THE COUNT OF THE RECIPIES

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);



    @Test
    public void clickListViewItem_OpensProcedureActivity() {

        onView(withId(R.id.Recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Set as invisible after Testing
        onView(withId(R.id.text_view_for_testing_number_of_recipies)).check(matches(withText("3")));
        onView(withId(R.id.text_view_for_testing)).check(matches(withText("Nutella Pie")));

    }




}
