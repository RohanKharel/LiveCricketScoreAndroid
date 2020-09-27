package com.example.livecricketscore;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import com.example.livecricketscore.activity.LoginActivity;

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
public class CheckLogin {

    private String stringToBetype;

    @Rule
    public ActivityTestRule<LoginActivity> checkActivity
            = new ActivityTestRule<>(LoginActivity.class);


    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetype = "Espresso";
    }


    @Test
    public void LoginUITest() throws Exception{
        onView(withId(R.id.etMailID)).perform(replaceText("rohansharma@gmail.com"));
        onView(withId(R.id.etPassword)).perform(replaceText("rohan123"));

        onView(withId(R.id.btnLogin)).perform(click());

    }




}
