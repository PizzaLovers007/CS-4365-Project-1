import java.util.Collection;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public abstract class SearchQueue {

    Collection<Node> data;

    public abstract void insert(Collection<Node> successors);

    public abstract Node remove();

    public boolean goalTest(Node curr) {
        char[] state = curr.getState();
        boolean seenSpace = false;
        for (char c : state) {
            if (c == 'B' && seenSpace || c == 'W' && !seenSpace) {
                return false;
            }
            if (c == ' ') {
                seenSpace = true;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static SearchQueue createQueue(String type, char[] startState, boolean useCost) {
        SearchQueue queue = null;
        switch (type) {
        case "DFS":
            queue = new DFSSearchQueue(startState, useCost);
            break;
        case "UCS":
            queue = new UCSSearchQueue(startState, useCost);
            break;
        }
        return queue;
    }
}
