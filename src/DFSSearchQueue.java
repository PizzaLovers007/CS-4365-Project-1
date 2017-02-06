import java.util.Collection;
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
        Node start = new Node(startState, 0, useCost);
        queue.push(start);
    }

    @Override
    public void insert(Collection<Node> successors) {
        for (Node n : successors) {
            if (!visited.contains(n)) {
                queue.add(n);
            }
        }
    }

    @Override
    public Node remove() {
        return queue.pop();
    }
}
