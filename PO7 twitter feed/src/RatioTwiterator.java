import java.util.Iterator;
import java.util.NoSuchElementException;


public class RatioTwiterator implements Iterator<Tweet> {
    //The TweetNode containing the next tweet to be returned in the iteration
    private TweetNode next;
    //The minimum threshold value for the ratio of likes to total engagement
    private final double THRESHOLD;

    /**
     * Constructs a new twiterator at the given starting node
     *
     * @param startNode - the node to begin the iteration at
     * @param threshold - the minimum threshold value for the ratio of likes to total engagement, assumed
     *                  to be between 0.0 and 1.0 thanks to range checking in Timeline
     */
    public RatioTwiterator(TweetNode startNode, double threshold) {
        this.THRESHOLD = threshold;
        while (startNode != null && startNode.getTweet().getLikesRatio() < threshold) {
            startNode = startNode.getNext();
        }
        next = startNode;
    }

    /**
     * Checks whether there is a next tweet to return
     *
     * @return true if there is a next tweet, false if the value of next is null
     */
    @Override
    public boolean hasNext() {
        if (next == null)
            return false;
        if (next.getTweet().getLikesRatio() >= THRESHOLD)
            return true;
        while (next.getTweet() != null && next.getTweet().getLikesRatio() < THRESHOLD) {
            next = next.getNext();
        }
        //return next.getNext() != null;
        return next != null;
    }

    /**
     * Returns the next tweet in the iteration if one exists, and advances next to the next tweet with a likes ratio in
     * excess of the given threshold
     *
     * @throws NoSuchElementException - if there is not a next tweet to return
     */
    @Override
    public Tweet next() {
        while (next != null) {
            if (next.getTweet().getLikesRatio() >= THRESHOLD) {
                Tweet temp = next.getTweet();
                next = next.getNext();
                return temp;
            }
            next = next.getNext();
        }
        throw new NoSuchElementException();
    }
}
