package com.example.rallyup.firestoreObjects;

public class User {
    String email = "Sample email";

    public User() {
    }

    // Constructor with email parameter
    public User(String email) {
        this.email = email;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
}
