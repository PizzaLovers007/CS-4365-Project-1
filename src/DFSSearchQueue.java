import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class DFSSearchQueue extends SearchQueue {

    Stack<Node> queue;
    HashSet<Node> visited;

    public DFSSearchQueue(char[] startState, boolean useCost) {
        queue = new Stack<>();
        data = queue;
        visited = new HashSet<>();
        Node start = new Node(startState, useCost);
        queue.push(start);
    }

    @Override
    public void insert(ArrayList<Node> successors) {
        Collections.reverse(successors);
        for (Node n : successors) {
            queue.add(n);
        }
    }

    @Override
    public Node remove() {
        return queue.pop();
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
