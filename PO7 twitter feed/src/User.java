//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    User class
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         I received no help on this class
// Online Sources:   I received no help on this class
//
///////////////////////////////////////////////////////////////////////////////
public class User extends Object {

    // the username under the tweet
    private String username;
    //the verified status of the user
    private boolean isVerified;
    //Constructs a new User object with a given username. All Users are unverified by default.

    /**
     * Constructs a new User object with a given username. All Users are unverified by default.
     *
     * @param username - the username that the new user will have
     * @return the new user
     * @throws throws an illegal argument exception if input contains an astrick or a null or blank
     */
    public User(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Invalid username:" + username);
        }
        if (username.contains("*")) {
            throw new IllegalArgumentException("user cannot be verified");
        }

        this.username = username;
        this.isVerified = false;
    }

    /**
     * gets the username the user
     *
     * @return the username under the tweet
     */
    public String getUsername() {
        this.username = username;
        return username;
    }

    /**
     * checks if the user is verified or not
     *
     * @return true if the user is verified
     */
    public boolean isVerified() {
        return this.isVerified == true;

    }

    /**
     * puts an asterisk next to the user
     */
    public void verify() {
        //this.username += "*";
        this.isVerified = true;
    }

    /**
     * takes away asterisk next to the user
     */
    public void revokeVerification() {
        this.isVerified = false;
    }

    /**
     * Creates a String representation of this User for display.
     *
     * @return A string of the username
     */
    public String toString() {
        String Username = "@" + this.username;
        if (this.isVerified) {
            Username += "*";
        }
        return Username;
    }
}
