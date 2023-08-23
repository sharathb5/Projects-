//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TweetNode
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:          I received no help on this class
// Online Sources:   I received no help on this class
//
///////////////////////////////////////////////////////////////////////////////
import java.lang.Object;

public class TweetNode extends Object {
    //The tweet contained in this node
    private Tweet tweet;
    //The next TweetNode in this linked list
    private TweetNode nextTweet;

    /**
     * Constructs a singly-linked node containing a tweet
     *
     * @param tweet - the tweet to put in this node
     * @param next  - the next tweet in the linked list
     **/
    public TweetNode(Tweet tweet, TweetNode next) {
        this.tweet = tweet;
        this.nextTweet = next;

    }

    /**
     * Constructs a singly-linked node containing a tweet with no successor tweet
     *
     * @param tweet - the tweet to put in this node
     **/
    public TweetNode(Tweet tweet) {
        this.tweet = tweet;
        this.nextTweet = null;
    }

    //the tweet in this node
    public Tweet getTweet() {
        return tweet;
    }

    //Accesses the next TweetNode in the list
    public TweetNode getNext() {
        return nextTweet;
    }

    /**
     * Links this node to another node
     *
     * @param next - the successor TweetNode (can be null)
     */
    public void setNext(TweetNode next) {
        this.nextTweet = next;
    }


}