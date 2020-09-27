package com.example.livecricketscore;

import androidx.test.rule.ActivityTestRule;


import com.example.livecricketscore.activity.UpdateProfileActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CheckUpdate {

    private String stringToBetype;

    @Rule
    public ActivityTestRule<UpdateProfileActivity> checkActivity
            = new ActivityTestRule<>(UpdateProfileActivity.class);


    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetype = "Espresso";
    }

    @Test
    public void UpdateTest() throws Exception{
        onView(withId(R.id.etUFullname)).perform(replaceText("Rohan Sharma"));
        onView(withId(R.id.etPhoneNo)).perform(replaceText("9860740264"));

        onView(withId(R.id.btnUpdate)).perform(click());
    }



}
