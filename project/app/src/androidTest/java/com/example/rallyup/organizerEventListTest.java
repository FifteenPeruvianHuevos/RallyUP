package com.example.rallyup;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.rallyup.uiReference.attendees.AttendeeHomepageActivity;
import com.example.rallyup.uiReference.organizers.OrganizerEventListActivity;
import com.example.rallyup.uiReference.splashScreen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
// TEMPORARY
public class organizerEventListTest {
    @Rule
    public ActivityScenarioRule<OrganizerEventListActivity> scenario = new ActivityScenarioRule<>(OrganizerEventListActivity.class);

    @Test
    public void testGoToOrgEventDetails() {
        onData(anything()).inAdapterView(withId(R.id.events_list)).atPosition(0).perform(click());
        onView(withId(R.id.activityOrgEventDetails)).check(matches(isDisplayed()));
    }
    @Test
    public void testReturnFromEventDetails() {
        onData(anything()).inAdapterView(withId(R.id.events_list)).atPosition(0).perform(click());
        onView(withId(R.id.organizer_details_back_button)).perform(click());
        onView(withId(R.id.activityOrganizerEventList)).check(matches(isDisplayed()));
    }

    @Test
    public void testGoToEventViewAttendees() {
        onData(anything()).inAdapterView(withId(R.id.events_list)).atPosition(0).perform(click());
        onView(withId(R.id.event_attendees_button)).perform(click());
        onView(withId(R.id.activityEventAttendeesInfo)).check(matches(isDisplayed()));
    }

    @Test
    public void testReturnFromEventViewAttendees() {
        onData(anything()).inAdapterView(withId(R.id.events_list)).atPosition(0).perform(click());
        onView(withId(R.id.event_attendees_button)).perform(click());
        onView(withId(R.id.event_attendees_back_button)).perform(click());
        onView(withId(R.id.activityOrgEventDetails)).check(matches(isDisplayed()));
    }
}

