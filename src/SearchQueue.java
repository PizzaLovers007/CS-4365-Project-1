import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public abstract class SearchQueue {

    Collection<Node> data;

    public abstract void insert(ArrayList<Node> successors);

    public abstract Node remove();

    public abstract boolean didVisit(Node curr);

    public abstract void visit(Node curr);

    public boolean goalTest(Node curr) {
        char[] state = curr.getState();
        boolean seenSpace = false;
        for (char c : state) {
            if (c == 'B' && seenSpace || c == 'W' && !seenSpace) {
                return false;
            }
            if (c == 'x') {
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
        case "BFS":
            queue = new BFSSearchQueue(startState, useCost);
            break;
        case "UCS":
            queue = new UCSSearchQueue(startState, useCost);
            break;
        case "GS":
            queue = new GSSearchQueue(startState, useCost);
            break;
        case "A-star":
            queue = new AstarSearchQueue(startState, useCost);
            break;
        case "HCS":
            queue = new HCSSearchQueue(startState, useCost);
            break;
        }
        return queue;
    }
}
