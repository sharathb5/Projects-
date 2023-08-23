//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tweet
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         I received help on the toString from a TA at the CS help hours
// Online Sources:   I received no help on this class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Calendar;
import java.util.Date;

public class Tweet {
    /**
     * A shared Calendar object for this class to use to generate consistent dates.
     */
    private static Calendar dateGenerator;
    /**
     * The User associated with this tweet
     */
    private User user;
    /**
     * The text of this tweet
     */
    private String text;
    /**
     * The number of likes this tweet has
     */
    private int numLikes;
    /**
     * The number of retweets this tweet has
     */
    private int numRetweets;
    /**
     * The date and time this tweet was posted
     */
    private Date timestamp;
    /**
     * A value determining the maximum length of a tweet. Set to 280 by default.
     */
    private static final int MAX_LENGTH = 280;

    /**
     * Creates a new tweet with no likes or retweets and a current time stamp
     *
     * @param user - the User posting this tweet
     * @param text - the text of the tweet
     * @return true if this winner equals the input provided object and false otherwise
     * @throws IllegalArgumentException - if the tweet's text exceeds MAX_LENGTH characters
     * @throws NullPointerException     - if the provided text or user are null
     * @throws IllegalStateException    - if the dateGenerator field has not yet been initialized
     */
    public Tweet(User user, String text) {
        if (text == null || user == null)
            throw new NullPointerException("text or user is null");
        if (text.isEmpty())
            throw new IllegalArgumentException("tweet is blank");
        if (text.length() > MAX_LENGTH)
            throw new IllegalArgumentException("tweet exceeds max number of characters");
        if (dateGenerator == null)
            throw new IllegalStateException("Date generator not initialized");
        this.user = user;
        this.text = text;
        this.numRetweets = 0;
        this.numLikes = 0;
        this.timestamp = dateGenerator.getTime();
    }

    /**
     * Initializes the dateGenerator static field to the provided Calendar object
     *
     * @param c the calendar to use for the date generation
     */
    public static void setCalendar(Calendar c) {
        dateGenerator = c;
    }

    /**
     * Accesses the text of the tweet
     *
     * @return the text of the tweet
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the total engagement numbers (likes + retweets) of this tweet
     *
     * @return the total engagement of this tweet
     */
    public int getTotalEngagement() {
        int totalEngagement = numLikes + numRetweets;
        // System.out.println("Total engagement" + totalEngagement);
        return totalEngagement;
    }

    /**
     * Checks whether the author of this tweet is a verified user
     *
     * @return true if they are verified and false if not
     */
    public boolean isUserVerified() {
        return user.isVerified();
    }

    /**
     * returns a ratio of the total engagement by likes
     *
     * @return a number between 0.0 and 1. -1 if the total engagement is 0
     */
    public double getLikesRatio() {
        if (getTotalEngagement() == 0) {
            return -1;
        }
        double ratio = (double) numLikes / getTotalEngagement();
        if (ratio > 0.0 && ratio < 1.0) {
            return ratio;
        }
        return ratio;
    }

    //Add one (1) to the number of likes for this tweet
    public void like() {
        numLikes += 1;
        //System.out.println("numLikes " + numLikes);
    }

    //Add one (1) to the number of retweets for this tweet
    public void retweet() {
        numRetweets += 1;
        //System.out.println("numRetweets " + numRetweets );
    }

    /**
     * Compares the tweet to see if it's the same tweet but the same tweet by the same user
     *
     * @param o - the object to compare this Tweet to
     * @return true if this Tweet is equivalent to the provided object, false otherwise
     * @Override equals in the object class
     */
    @Override
    public boolean equals(Object o) {
        Tweet other = (Tweet) o;
        if (other.text.equals(this.text) && other.timestamp.equals(this.timestamp)
                && other.user.toString().equals(this.user.toString())) {
            return true;
        }
        return false;
    }
    /**
     * A string representation of this tweet.
     * @return a formatted string representation of this tweet
     * @Override toString in the object class
     */
    @Override
    public String toString() {
        return "tweet from " + user.toString() + " at " + timestamp.toString() + ":\n-- " + text + "\n-- likes: "
                + numLikes + "\n-- retweets: " + numRetweets;
    }


}
