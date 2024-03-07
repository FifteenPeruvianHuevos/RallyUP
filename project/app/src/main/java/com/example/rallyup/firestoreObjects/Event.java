package com.example.rallyup.firestoreObjects;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Represents an event with a name, location, and description.
 */
public class Event {
    private String eventName;
    private String eventLocation;
    private String eventDescription;
    private String eventDate;
    private String eventTime;
    private int signUpLimit;
    private Boolean signUpLimitBool;
    private Boolean geolocation;
    private Boolean reUseQR;
    private Boolean newQR;
    private String posterRef;
    private String shareQRRef;
    private String checkInQRRef;


    /**
     * Constructs a new Event object with the given name, location, and description.
     *
     * @param eventName        The name of the event.
     * @param eventLocation    The location of the event.
     * @param eventDescription The description of the event.
     */
    public Event(String eventName, String eventLocation, String eventDescription, String eventDate, String eventTime,
                 int signUpLimit, Boolean signUpLimitBool, Boolean geolocation, Boolean reUseQR, Boolean newQR,
                 String posterRef, String shareQRRef, String checkInQRRef) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.signUpLimit = signUpLimit;
        this.signUpLimitBool = signUpLimitBool;
        this.geolocation = geolocation;
        this.reUseQR = reUseQR;
        this.newQR = newQR;
        this.posterRef = posterRef;
        this.shareQRRef = shareQRRef;
        this.checkInQRRef = checkInQRRef;
    }
    public Event() {}

    /**
     * Gets the name of the event.
     *
     * @return The name of the event.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the name of the event.
     *
     * @param eventName The new name of the event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Gets the location of the event.
     *
     * @return The location of the event.
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * Sets the location of the event.
     *
     * @param eventLocation The new location of the event.
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Sets the description of the event.
     *
     * @param eventDescription The new description of the event.
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public int getSignUpLimit() {
        return signUpLimit;
    }

    public void setSignUpLimit(int signUpLimit) {
        this.signUpLimit = signUpLimit;
    }

    public Boolean getSignUpLimitBool() {
        return signUpLimitBool;
    }

    public void setSignUpLimitBool(Boolean signUpLimitBool) {
        this.signUpLimitBool = signUpLimitBool;
    }

    public Boolean getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Boolean geolocation) {
        this.geolocation = geolocation;
    }

    public Boolean getReUseQR() {
        return reUseQR;
    }

    public void setReUseQR(Boolean reUseQR) {
        this.reUseQR = reUseQR;
    }

    public Boolean getNewQR() {
        return newQR;
    }

    public void setNewQR(Boolean newQR) {
        this.newQR = newQR;
    }

    public String getPosterRef() {
        return posterRef;
    }

    public void setPosterRef(String posterRef) {
        this.posterRef = posterRef;
    }

    public String getShareQRRef() {
        return shareQRRef;
    }

    public void setShareQRRef(String shareQRRef) {
        this.shareQRRef = shareQRRef;
    }

    public String getCheckInQRRef() {
        return checkInQRRef;
    }

    public void setCheckInQRRef(String checkInQRRef) {
        this.checkInQRRef = checkInQRRef;
    }
}

