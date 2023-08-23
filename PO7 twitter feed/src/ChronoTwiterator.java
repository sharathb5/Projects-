import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChronoTwiterator implements Iterator<Tweet> {

    //The TweetNode containing the next tweet to be returned in the iteration
    private TweetNode next;
/**
 * Constructs a new twiterator at the given starting node
 *
 * @param  node

 * */
    public ChronoTwiterator(TweetNode node) {
        this.next = node;
    }


    /**
     * Checks whether there is a next tweet to return
     *
     * @return true if there is a next tweet, false if the value of next is null
     */
    @Override
    public boolean hasNext() {
        return next != null;
    }

    /**
     * Returns the next tweet in the iteration if one exists, and advances next to the next tweet
     *
     * @return Returns the next tweet in the iteration if one exists, and advances next to the next tweet
     * @throws "NoSuchElementException" - if there is not a next tweet to return
     */
    @Override
    public Tweet next() {
        if (next == null) {
            throw new NoSuchElementException("next value is null");
        }
        Tweet tweet = next.getTweet();
        next = next.getNext();
        return tweet;
    }

}
