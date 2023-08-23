//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tester class
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:          I received help checking for the exception in the tweet tester method
// Online Sources:   I received no help on this class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Calendar;

public class TwiteratorTester {
    public static boolean testUser() {
        Tweet.setCalendar(Calendar.getInstance());
        User user = new User("Sharath");
        // System.out.println(user);
        // test getUsername() method
        if (!user.getUsername().equals("Sharath")) {
            System.out.println("getUsername test failed");

        }
        if(!user.toString().equals("Sharath"))
        return false;


        user.verify();
        //System.out.println(user.getUsername());
        //System.out.println(user.isVerified());
        if (!user.isVerified() || !user.getUsername().equals("Sharath")) {
            System.out.println("verify() test failed!");
        }
        if(!user.toString().equals("Sharath*"))
            return false;
        //System.out.println(user);


        user.revokeVerification();
        //System.out.println(user);
        if (user.isVerified() || !user.getUsername().equals("Sharath")) {
            System.out.println("revokeVerification() test failed!");
        }

        try {
            User invalidUser = new User(null);
            System.out.println("IllegalArgumentException test failed!");
        } catch (IllegalArgumentException e) {
            // System.out.println("Invalid user test working1");
        }

        try {
            User invalidUser = new User("");
            System.out.println("IllegalArgumentException test failed!");
        } catch (IllegalArgumentException e) {
            //System.out.println("Invalid user test working2");

        }

        try {
            User invalidUser = new User("johndoe*");
            System.out.println("IllegalArgumentException test failed!");
        } catch (IllegalArgumentException e) {
            // System.out.println("Invalid user test working3");
        }

        return true;
    }

    public static boolean testTweet() {
        User user = new User("Sharath");
        Tweet.setCalendar(Calendar.getInstance());
        Tweet tweet = new Tweet(user, "It was nice today!");
        try {
            Tweet tweetNullText = new Tweet(user, null);
        } catch (NullPointerException e) {
            //System.out.println("Caught NullPointerException: " + e.getMessage());
        } catch (Exception v) {
            return false;
        }
        try {
            Tweet tweetovercharacthers = new Tweet(user, "Testing this tweet to see if it has more than 280 " +
                    "characters, I am only at 60 right now how do I add more? What the hell is this…. " +
                    "How do I double this in length. Testing this tweet to see if it has more than 280 characters, " +
                    "I am only at 60 right now how do I add more? What the hell is this…. How do I double this in " +
                    "length. ");
        } catch (IllegalArgumentException e) {
            //System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        } catch (Exception V) {
            return false;
        }
        try {
            Tweet tweetempty = new Tweet(user, "");
        } catch (IllegalArgumentException e) {
            //System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        } catch (Exception V) {
            return false;
        }

        if (tweet.getTotalEngagement() != 0)
            System.out.println("Total Engagement failed! ");
        tweet.like();
        tweet.like();
        tweet.retweet();
        tweet.retweet();
        if (tweet.getTotalEngagement() != 4)
            System.out.println("Total Engagement failed!");
        //System.out.println("like ratio is " + tweet.getLikesRatio());
        if (tweet.getLikesRatio() != 0.5)
            System.out.println("get likes ratio failed");
        if (!tweet.equals(tweet))
            return false;

        return true;

    }


    public static boolean testNode() {
        Tweet.setCalendar(Calendar.getInstance());
        User user = new User("Sharath");
        Tweet tweet1 = new Tweet(user, "Hello World");
        Tweet tweet2 = new Tweet(user, "test tweet");

        TweetNode node1 = new TweetNode(tweet1);
        TweetNode node2 = new TweetNode(tweet2);

        node1.setNext(node2);
//        System.out.println(node1.getTweet().getText());
//        System.out.println(node2.getTweet().getText());
//        System.out.println(node1.getNext().getTweet().getText());

        if (!node1.getTweet().getText().equals("Hello World"))
            return false;
        if (!node2.getTweet().getText().equals("test tweet."))
            return false;
        if (!node1.getNext().getTweet().getText().equals("test tweet."))
            return false;
        return true;
    }

    public static boolean testAddTweet() {
        Tweet.setCalendar(Calendar.getInstance());
        User user = new User("Sharath");
        Tweet tweet = new Tweet(user, "Hello World");
        User user1 = new User("temp");
        Tweet tweet1 = new Tweet(user, "tempish");
        TwitterFeed TF = new TwitterFeed();
        TF.add(0, tweet);
        if (TF.indexOf(tweet) != 0)
            return false;
        if (TF.size() != 2)
            return false;
        if (TF.indexOf(tweet1) != 1)
            return false;
        return true;
    }

    public static boolean testInsertTweet() {
        Tweet.setCalendar(Calendar.getInstance());
        User user = new User("Sharath");
        Tweet tweet = new Tweet(user, "Hello World");
        User user1 = new User("temp");
        Tweet tweet1 = new Tweet(user, "tempish");
        User user2 = new User("temp1");
        Tweet tweet2 = new Tweet(user2, "tempish");
        TwitterFeed TF = new TwitterFeed();
        TF.add(0, tweet);
        TF.add(1, tweet1);
        TF.add(2, tweet2);
        if (!TF.getHead().equals(tweet))
            return false;
        if (!TF.getTail().equals(tweet2))
            return false;
        if (!TF.get(1).equals(tweet1))
            return false;

        return true;

    }

    public static boolean testDeleteTweet() {
        Tweet.setCalendar(Calendar.getInstance());
        User user = new User("Sharath");
        Tweet tweet = new Tweet(user, "Hello World");
        User user1 = new User("temp");
        Tweet tweet1 = new Tweet(user, "tempish1");
        User user2 = new User("temp1");
        Tweet tweet2 = new Tweet(user2, "tempish2");
        User user3 = new User("temp2");
        Tweet tweet3 = new Tweet(user3, "tempish3");
        User user4 = new User("temp3");
        Tweet tweet4 = new Tweet(user4, "tempish4");
        TwitterFeed TF = new TwitterFeed();
        TF.add(0, tweet);
        TF.add(1, tweet1);
        TF.add(2, tweet2);
        TF.add(3, tweet3);
        TF.add(4, tweet4);
        //remove the last to check the tail
        TF.delete(4);
        if (!TF.getTail().equals(tweet3))
            return false;
        //remove the first to check head
        TF.add(4, tweet4);
        TF.delete(0);
        if (!TF.getHead().equals(tweet1))
            return false;
        //remove a middle and check the index
        TF.add(0, tweet);
        //System.out.println(TF.size());
        TF.delete(2);
        if (!TF.get(2).equals(tweet3))
            return false;
        return true;
    }

    public static boolean testChronoTwiterator() {
        User u1 = new User("uwmadison");
        u1.verify();
        User u2 = new User("dril");
        Calendar test = Calendar.getInstance();
        test.set(2023, Calendar.APRIL, 3, 17, 07, 50);
        Tweet.setCalendar(test);
        TwitterFeed feed = new TwitterFeed();
        feed.addFirst(new Tweet(u1, "Join us for Bucky's Big Event next Wednesday! " +
                "#CelebrateUW"));
        feed.addFirst(new Tweet(u2, "type \"Gaming is back \", if you think gaming is " +
                "back"));
        feed.addFirst(new Tweet(u1, "It's a GREAT day for @BadgerMHockey! #OnWisconsin"));
        feed.addFirst(new Tweet(u1, "Welcome to the University of Wisconsin-Madison, " +
                "#FutureBadger!"));
        feed.addLast(new Tweet(u2, "got botulism from a pair of bad jeans"));
        Tweet tmp = feed.get(2);
        for (int i = 0; i < 5243; i++) tmp.like();
        for (int i = 0; i < 4702; i++) tmp.retweet();
        tmp = feed.get(3);
        tmp.like();
        tmp = feed.get(4);
        for (int i = 0; i < 307; i++) tmp.like();
        for (int i = 0; i < 4015; i++) tmp.retweet();
        // System.out.println("---------- \"REVERSE CHRONOLOGICAL\" ORDER -----------");
//        for (Tweet t : feed) {
//            System.out.println(t);
//        }
//        System.out.println("\n---------- VERIFIED ONLY -----------");
//        feed.setMode(TimelineMode.VERIFIED_ONLY);
//        for (Tweet t : feed) { System.out.println(t); }
        System.out.println("\n---------- HIGH LIKES RATIO ONLY -----------");
        feed.setMode(TimelineMode.LIKE_RATIO);
        for (Tweet t : feed) {
            System.out.println(t);
        }
        return true;
    }

    public static boolean testChronoTwiterator2() {
        int i = 0;
        Tweet.setCalendar(Calendar.getInstance());
        TwitterFeed TF = new TwitterFeed();
        User user = new User("Sharath");
        Tweet tweet = new Tweet(user, "Hello World");
        User user1 = new User("temp");
        Tweet tweet1 = new Tweet(user, "tempish1");
        User user2 = new User("temp1");
        Tweet tweet2 = new Tweet(user2, "tempish2");
        TF.add(0, tweet);
        TF.add(1, tweet1);
        TF.add(2, tweet2);
        for (Tweet tn : TF) {
            //System.out.println(tn.toString());
        }
        Tweet temp = TF.get(i);
        if (!temp.equals(tweet))
            return false;
        return true;
    }

    public static boolean testVerifiedTwiterator(){
        int i = 0;
        Tweet.setCalendar(Calendar.getInstance());
        TwitterFeed TF = new TwitterFeed();
        User user = new User("Sharath");
        Tweet tweet = new Tweet(user, "Hello World");
        User user1 = new User("temp");
        Tweet tweet1 = new Tweet(user, "tempish1");
        User user2 = new User("temp1");
        Tweet tweet2 = new Tweet(user2, "tempish2");
        user.verify();
        user2.verify();
        TF.add(0,tweet);
        TF.add(1,tweet1);
        TF.add(2,tweet2);
        for (Tweet tn : TF) {
            //System.out.println(tn.toString());
        }
        return true;



    }
    public static boolean testRatioTwiterator(){
        int i = 0;
        Tweet.setCalendar(Calendar.getInstance());
        TwitterFeed TF = new TwitterFeed();
        User user = new User("Sharath");
        Tweet tweet = new Tweet(user, "Hello World");
        User user1 = new User("temp");
        Tweet tweet1 = new Tweet(user, "tempish1");
        User user2 = new User("temp1");
        Tweet tweet2 = new Tweet(user2, "tempish2");
        user.verify();
        user2.verify();
        TF.add(0,tweet);
        TF.add(1,tweet1);
        TF.add(2,tweet2);
        tweet.like();
        tweet.like();
        tweet2.like();
        tweet2.like();
        tweet.retweet();
        tweet2.retweet();
        for (Tweet tn : TF) {
            System.out.println(tn.toString());
        }

        return true;
    }


    public static void main(String[] args) {
        testUser();
        testTweet();
        testNode();
        testAddTweet();
        testInsertTweet();
        testDeleteTweet();
        testChronoTwiterator();
        //testChronoTwiterator2();
        //testVerifiedTwiterator();
        //testRatioTwiterator();
    }
}

