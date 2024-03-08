package com.example.rallyup;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import android.app.Activity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.rallyup.uiReference.splashScreen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class splashIntentTest {
    @Rule
    public ActivityScenarioRule<splashScreen> scenario = new ActivityScenarioRule<>(splashScreen.class);

    @Test
    public void testGoToAttendeeHomePage() {
        onView(withId(R.id.attendee_button)).perform(click());
        onView(withId(R.id.attendeeHomepage)).check(matches(isDisplayed()));
    }

    @Test
    public void testGoToOrganizerEventList() {
        onView(withId(R.id.organizer_button)).perform(click());
        onView(withId(R.id.activityOrganizerEventList)).check(matches(isDisplayed()));
    }

    @Test
    public void testReturnFromAttHomePage(){
        onView(withId(R.id.attendee_button)).perform(click());
        onView(withId(R.id.attendee_homepage_back_button)).perform(click());;
        onView(withId(R.id.activitySplashScreen)).check(matches(isDisplayed()));
    }

    @Test
    public void testReturnFromOrgEventList(){
        onView(withId(R.id.organizer_button)).perform(click());
        onView(withId(R.id.organizer_events_back_button)).perform(click());;
        onView(withId(R.id.activitySplashScreen)).check(matches(isDisplayed()));
    }



}