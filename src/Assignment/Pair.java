package Assignment;

/**
 * Groups a pair of 2 objects into 1
 * @param <A> Datatype of the first object
 * @param <B> Datatype of the second object
 */
public class Pair<A, B> {
    private A first;
    private B second;

    /**
     * Constructs a pair of 2 objects
     * @param first An object representing the first object
     * @param second An object representing the second object
     */
    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    /**
     * A method for retrieving the first object
     * @return The value of the first object
     */
    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    /**
     * A method for retrieving the second object
     * @return The value of the second object
     */
    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}   