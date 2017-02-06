import java.util.Collection;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public abstract class SearchQueue {

    Collection<Node> data;

    public abstract Node getNext();

    public abstract boolean goalTest(Node curr);

    public abstract void insert(Collection<Node> successors);

    public abstract Node remove();

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static SearchQueue getQueue(String type, char[] startState) {
        return null;
    }
}
