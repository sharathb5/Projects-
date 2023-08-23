public class ChronoTwiteratorBuilder {
    private TweetNode startNode;
    private TweetNode nextnode;

    public ChronoTwiteratorBuilder setStartNode(TweetNode startNode) {
        this.startNode = startNode;
        return this;
    }

    public ChronoTwiteratorBuilder setNextnode(TweetNode nextnode) {
        this.nextnode = nextnode;
        return this;
    }

    public ChronoTwiterator createChronoTwiterator() {
        return new ChronoTwiterator(startNode);
    }
}