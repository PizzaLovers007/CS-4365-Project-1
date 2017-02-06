import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class UCSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;

    public UCSSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getSteps() - two.getSteps());
        data = queue;
        Node start = new Node(startState, 0, useCost);
        queue.add(start);
    }

    @Override
    public void insert(Collection<Node> successors) {
        queue.addAll(successors);
    }

    @Override
    public Node remove() {
        return queue.remove();
    }
}
