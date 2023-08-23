//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Twitter Feed
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

import java.util.Iterator;

public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {
    private TweetNode head;
    // the node containing the oldest tweet
    private TweetNode tail;
    // the number of tweets in the linked list
    private int size;
    // The iteration mode for the timeline display
    private TimelineMode mode;
    // The ratio of likes to total engagement that we want to see, is set to .5 by default
    private static double ratio = 0.5;

    //Default constructor; creates an empty TwitterFeed by setting all data fields to their default values,
    // and the timeline mode to CHRONOLOGICAL.
    public TwitterFeed() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Accessor for the size of the feed
     *
     * @return the number of TweetNodes in this TwitterFeed
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Determines whether this feed is empty
     *
     * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Determines whether a given Tweet is present in the TwitterFeed
     *
     * @param findObject - the Tweet to search for
     * @return true if the Tweet is present, false otherwise
     */
    @Override
    public boolean contains(Tweet findObject) {
        TweetNode curr = head;
        while (curr != null) {
            if (curr.getTweet().equals(findObject))
                return true;
            curr = curr.getNext();
        }
        return false;
    }

    /**
     * Accessor method for the index of a given Tweet in the TwitterFeed.
     *
     * @param findObject - the Tweet to search for
     * @return the index if the Tweet is present, -1 otherwise
     */
    @Override
    public int indexOf(Tweet findObject) {
        TweetNode curr = head;
        int i = -1;
        while (curr != null) {
            i++;
            if (curr.getTweet().equals(findObject))
                return i;
            curr = curr.getNext();

        }
        return i;
    }

    /**
     * Accessor method for the Tweet at a given index
     *
     * @param index - the index of the Tweet in question
     * @return the Tweet object at that index
     */
    @Override
    //FIX THIS!
    public Tweet get(int index) {
        TweetNode curr = head;
        int i = -1;
        while (curr != null) {
            i++;
            if (i == index)
                return (Tweet) curr.getTweet();
            curr = curr.getNext();

        }
        return null;
    }

    /**
     * Accessor method for the first Tweet in the TwitterFeedx
     *
     * @return the Tweet object at the head of the linked list
     * @throws "NoSuchElementException" - if the TwitterFeed is empty
     */
    public Tweet getHead() {
        return (Tweet) head.getTweet();
    }

    /**
     * Accessor method for the last  Tweet in the TwitterFeedx
     *
     * @return the Tweet object at the tail of the linked list
     * @throws "NoSuchElementException" - if the TwitterFeed is empty
     */
    public Tweet getTail() {
        return (Tweet) tail.getTweet();
    }

    /**
     * Adds the given Tweet to the tail of the linked list
     *
     * @param newObject - the Tweet to add
     */
    @Override
    public void addLast(Tweet newObject) {
        TweetNode tn = new TweetNode(newObject, null);
        if(size == 0){
            head = tn;
            tail = tn;
        }else {
            tail.setNext(tn);
            tail = tn;
        }
        size++;
    }

    /**
     * Adds the given Tweet to the head of the linked list
     *
     * @param newObject - the Tweet to add
     */
    @Override
    public void addFirst(Tweet newObject) {
        TweetNode tn = new TweetNode(newObject, head);
        head = tn;
        if (size == 0)
            tail = tn;
        size++;
    }

    /**
     * Adds the given Tweet to the specified position of the linked list
     *
     * @param newObject - the Tweet to add
     * @throws "IndexOutofBoundsExcpetion" - if the index is negative or greater than the size of the linked list
     */
    @Override
    public void add(int index, Tweet newObject) {
        TweetNode curr = head;
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (size == 0) {
            TweetNode tn = new TweetNode(newObject);
            head = tn;
            tail = tn;
            size++;
            return;
        }
        if(index == 0){
            TweetNode tn = new TweetNode(newObject);
            tn.setNext(head);
            if(head == tail )
                tail = tn;
            head = tn;
            size++;
            return;
        }
        int i = -1;
        while (curr != null) {
            i++;
            if (i == index - 1) {
                TweetNode tn = new TweetNode(newObject, curr.getNext());
                curr.setNext(tn);
                if (index == size) {
                    tail = tn;
                }
                size++;
                return;
            }
            curr = curr.getNext();
        }

    }

    /**
     * Removes and returns the Tweet at the given index
     *
     * @param index - the position of the Tweet to remove
     * @return the Tweet object that was removed from the list
     * @throws IndexOutOfBoundsException - if the index is negative or greater than the largest index currently
     *                                   present in the linked list
     */
    @Override
    public Tweet delete(int index) {
        TweetNode curr = head;
        TweetNode prev = null;

        // System.out.println(size);
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index greater than size or less than 0");
        int i = 0;
        if (index == 0) {
            TweetNode res = head;
            head = head.getNext();
            if (size == 1)
                tail = head;
            size--;
            return res.getTweet();
        }
        while (curr != null) {
            //checks for head
            //curr = curr.getNext();
            //checks for tail
            if (i == index - 1) {
                if (curr.getNext().getNext() == null) {
                    Tweet temp = curr.getNext().getTweet();
                    if (tail == curr.getNext())
                        tail = curr;
                    curr.setNext(null);
                    tail = curr;
                    size--;
                    return temp;
                } else {
                    TweetNode temp1 = curr.getNext();
                    curr.setNext(curr.getNext().getNext());
                    size--;
                    return temp1.getTweet();
                }
                //from 9th node skipping 10th to 11th

            }
            if (i == index)
                break;
            prev = curr;
            curr = curr.getNext();
            i++;
        }
        tail = prev;
        if (curr != null)
            return curr.getTweet();
        else
            throw new IndexOutOfBoundsException("Something went wrong");
    }

    /**
     * Sets the iteration mode of this TwitterFeed
     *
     * @param m - the iteration mode; any value from the TimelineMode enum
     */
    public void setMode(TimelineMode m) {
        mode = m;
    }

    /**
     * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
     *
     * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized to the head of this
     * TwitterFeed list
     */
    public Iterator<Tweet> iterator() {
        if (mode == TimelineMode.CHRONOLOGICAL)
            return new ChronoTwiterator(this.head);
        if (mode == TimelineMode.VERIFIED_ONLY)
            return new VerifiedTwiterator(this.head);
        return new RatioTwiterator(this.head, 0.0);

    }
    // the node containing the most recent tweet


}
