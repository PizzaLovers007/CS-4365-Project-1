import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class HCSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;
    int iteration;

    public HCSSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getHeuristic() - two.getHeuristic());
        data = queue;
        Node start = new Node(startState, 0, useCost);
        queue.add(start);
        iteration = 0;
    }

    @Override
    public void insert(Collection<Node> successors) {
        queue.clear();
        if (iteration < 10000) {
            queue.addAll(successors);
        }
    }

    @Override
    public Node remove() {
        iteration++;
        return queue.remove();
    }
}
