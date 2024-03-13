package com.ualberta.rallyup.firestoreObjects;

/**
 * Represents a user with email, first name, and last name.
 */
public class User {
    private String email = "Sample email";
    private String firstName = "";
    private String lastName = "";
    private String id = "";

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Constructor for User with email parameter.
     *
     * @param email The email of the user.
     */
    public User(String email) {
        this.email = email;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The new first name for the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The new last name for the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the id of the user.
     *
     * @return The id of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the user.
     *
     * @param id The id for the user.
     */
    public void setId(String id) {
        this.id = id;
    }
}
