package com.ualberta.rallyup.firestoreObjects;

/**
 * Represents a QR code information.
 */
public class QrCode {

    /**
     * The ID of the event associated with the QR code.
     */
    private String qrId;

    /**
     * Indicates whether the QR code has been checked in.
     */
    private boolean checkIn;

    /**
     * The ID of the event associated with the QR code.
     */
    private String eventID;

    /**
     * The image URL of the QR code.
     */
    private String image;


    /**
     * Constructs a new QrCodes object.
     */
    public QrCode() {
    }

    /**
     * Checks if the QR code has been checked in.
     * @return true if the QR code has been checked in; otherwise, false.
     */
    public boolean isCheckIn() {
        return checkIn;
    }

    /**
     * Sets the check-in status of the QR code.
     * @param checkIn true if the QR code has been checked in; otherwise, false.
     */
    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Gets the ID of the event associated with the QR code.
     * @return The ID of the event.
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Sets the ID of the event associated with the QR code.
     * @param eventID The ID of the event.
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * Gets the image URL of the QR code.
     * @return The image URL of the QR code.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image URL of the QR code.
     * @param image The image URL of the QR code.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the ID of the event associated with the QR code.
     * @return The ID of the event.
     */
    public String getQrId() {
        return qrId;
    }

    /**
     * Sets the ID of the code associated with the QR code.
     * @param qrId The ID of the code.
     */
    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

}

