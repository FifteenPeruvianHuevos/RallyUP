package com.example.rallyup.firestoreObjects;

/**
 * Represents an event with a name, location, and description.
 */
public class Event {
    private String eventName;
    private String eventLocation;
    private String eventDescription;
    private int eventDate;
    private int eventTime;


    /**
     * Constructs a new Event object with the given name, location, and description.
     *
     * @param eventName        The name of the event.
     * @param eventLocation    The location of the event.
     * @param eventDescription The description of the event.
     */
    public Event(String eventName, String eventLocation, String eventDescription) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
    }

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
}

