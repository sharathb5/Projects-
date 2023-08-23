import java.util.Iterator;
import java.util.NoSuchElementException;

public class VerifiedTwiterator implements Iterator<Tweet> {


//The TweetNode containing the next tweet to be returned in the iteration
    private TweetNode next;
    /**
     * Constructs a new twiterator at the given starting node
     *
     * @param startNode - the node to begin the iteration at
     */
    public VerifiedTwiterator(TweetNode startNode) {
        this.next = startNode;
        while(!next.getTweet().isUserVerified()){
            next = next.getNext();
        }


    }

    /**
     * Checks whether there is a next tweet to return
     *
     * @return true if there is a next tweet, false if the value of next is null
     */
    @Override
    public boolean hasNext() {
        TweetNode curr = next;
        while (curr != null) {
            if (curr.getTweet().isUserVerified()) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;

    }
    /**
     * Returns the next tweet in the iteration if one exists, and advances next to the
     * next tweet by a verified user
     * @throws NoSuchElementException - if there is not a next tweet to return
     */
    @Override
    public Tweet next() {
        if (next == null) {
            throw new NoSuchElementException("next value is null");
        }
        TweetNode curr = next;
        while (curr != null && !curr.getTweet().isUserVerified()) {
            curr = curr.getNext();
        }
        if (curr == null) {
            throw new NoSuchElementException("no next tweet from a verified user");
        }
        Tweet tweet = curr.getTweet();
        next = curr.getNext();
        return tweet;
    }
}
