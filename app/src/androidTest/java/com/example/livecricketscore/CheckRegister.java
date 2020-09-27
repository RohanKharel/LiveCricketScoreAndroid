package com.example.livecricketscore;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.livecricketscore.activity.RegisterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest


public class CheckRegister {

    private String stringToBetype;

    @Rule
    public ActivityTestRule<RegisterActivity> checkActivity
            = new ActivityTestRule<>(RegisterActivity.class);
    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetype = "Espresso";
    }

    @Test
    public void RegisterUITest() throws Exception{
        onView(withId(R.id.etUser)).perform(replaceText("rohan"));
        onView(withId(R.id.etEmail)).perform(replaceText("rohan@gmail.com"));
        onView(withId(R.id.etPhone)).perform(replaceText("9811715833"));
        onView(withId(R.id.etPass)).perform(replaceText("rohan123"));

        onView(withId(R.id.btnRegister)).perform(click());


    }



}
