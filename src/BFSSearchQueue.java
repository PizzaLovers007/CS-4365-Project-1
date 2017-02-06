import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class BFSSearchQueue extends SearchQueue {

    LinkedList<Node> queue;
    HashSet<Node> visited;

    public BFSSearchQueue(char[] startState, boolean useCost) {
        queue = new LinkedList<>();
        data = queue;
        visited = new HashSet<>();
        Node start = new Node(startState, 0, useCost);
        queue.add(start);
    }

    @Override
    public void insert(ArrayList<Node> successors) {
        for (Node n : successors) {
            queue.add(n);
        }
    }

    @Override
    public Node remove() {
        return queue.remove();
    }

    @Override
    public boolean didVisit(Node curr) {
        return visited.contains(curr);
    }

    @Override
    public void visit(Node curr) {
        visited.add(curr);
    }
}
