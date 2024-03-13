package com.ualberta.rallyup;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.GrantPermissionRule;

import com.ualberta.rallyup.uiReference.attendees.AttendeeHomepageActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class attendeeHomepageTest {
    @Rule
    public ActivityScenarioRule<AttendeeHomepageActivity> scenario = new ActivityScenarioRule<>(AttendeeHomepageActivity.class);
    @Rule
    public GrantPermissionRule permissionCamera = GrantPermissionRule.grant("android.permission.CAMERA");
    @Test
    public void testGoToMyEvents() {
        onView(withId(R.id.attendee_my_events_button)).perform(click());
        onView(withId(R.id.attendeeMyEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void testGoToBrowseEvents() {
        onView(withId(R.id.attendee_browse_events_button)).perform(click());
        onView(withId(R.id.attendeeBrowseEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void testReturnFromMyEvents() {
        onView(withId(R.id.attendee_my_events_button)).perform(click());
        onView(withId(R.id.browse_events_back_button)).perform(click());
        onView(withId(R.id.attendeeHomepage)).check(matches(isDisplayed()));
    }

    @Test
    public void testReturnFromBrowseEvents() {
        onView(withId(R.id.attendee_browse_events_button)).perform(click());
        onView(withId(R.id.browse_events_back_button)).perform(click());
        onView(withId(R.id.attendeeHomepage)).check(matches(isDisplayed()));
    }

    @Test
    public void testQRScanner() {
        onView(withId(R.id.QRScannerButton)).perform(click());
        onView(withId(R.id.scannerActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testMyEventsQRScanner() {
        onView(withId(R.id.attendee_my_events_button)).perform(click());
        onView(withId(R.id.QRScannerButton)).perform(click());
        onView(withId(R.id.scannerActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testGoToEditProfile() {
        onView(withId(R.id.edit_profile_button)).perform(click());
        onView(withId(R.id.attendeeUpdateInfo)).check(matches(isDisplayed()));
    }

    @Test
    public void testReturnFromEditProfile() {
        onView(withId(R.id.edit_profile_button)).perform(click());
        onView(withId(R.id.attendee_update_back_button)).perform(click());
        onView(withId(R.id.attendeeHomepage)).check(matches(isDisplayed()));
    }
}