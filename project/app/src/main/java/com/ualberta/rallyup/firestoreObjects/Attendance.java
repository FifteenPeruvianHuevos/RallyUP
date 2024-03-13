package com.ualberta.rallyup.firestoreObjects;

import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Represents an attendance record for an event.
 */
public class Attendance {

    /**
     * Indicates whether the attendee's presence has been verified.
     */
    private boolean attendeeVerified;

    /**
     * The ID of the event.
     */
    private String eventID;

    /**
     * The number of times the attendee has checked in.
     */
    private int timesCheckedIn;

    /**
     * The ID of the user attending the event.
     */
    private String userID;

    /**
     * Constructs a new Attendance object.
     */
    public Attendance() {};
    public Attendance(DocumentSnapshot documentSnapshot) {
        setEventID(documentSnapshot.getString("eventID"));
        setUserID(documentSnapshot.getString("userID"));
        setAttendeeVerified(Boolean.TRUE.equals(documentSnapshot.getBoolean("attendeeVerified")));
        setTimesCheckedIn(Math.toIntExact(documentSnapshot.getLong("timesCheckedIn")));
    }

    /**
     * Gets the verification status of the attendee.
     * @return true if the attendee's presence has been verified; otherwise, false.
     */
    public boolean isAttendeeVerified() {
        return attendeeVerified;
    }

    /**
     * Sets the verification status of the attendee.
     * @param attendeeVerified true if the attendee's presence has been verified; otherwise, false.
     */
    public void setAttendeeVerified(boolean attendeeVerified) {
        this.attendeeVerified = attendeeVerified;
    }

    /**
     * Gets the ID of the event.
     * @return The ID of the event.
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Sets the ID of the event.
     * @param eventID The ID of the event.
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * Gets the number of times the attendee has checked in.
     * @return The number of times the attendee has checked in.
     */
    public int getTimesCheckedIn() {
        return timesCheckedIn;
    }

    /**
     * Sets the number of times the attendee has checked in.
     * @param timesCheckedIn The number of times the attendee has checked in.
     */
    public void setTimesCheckedIn(int timesCheckedIn) {
        this.timesCheckedIn = timesCheckedIn;
    }

    /**
     * Gets the ID of the user attending the event.
     * @return The ID of the user attending the event.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the ID of the user attending the event.
     * @param userID The ID of the user attending the event.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}

