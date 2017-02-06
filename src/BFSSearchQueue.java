import java.util.Collection;
import java.util.Queue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class BFSSearchQueue extends SearchQueue {

    Queue<Node> queue;

    public BFSSearchQueue(char[] startState, boolean useCost) {
        queue = new Queue<>();
        data = queue;
        Node start = new Node(startState, 0, useCost);
        queue.add(start);
    }

    @Override
    public void insert(Collection<Node> successors) {
        for (Node n : successors) {
            queue.add(n);
        }
    }

    @Override
    public Node remove() {
        return queue.remove();
    }
}
