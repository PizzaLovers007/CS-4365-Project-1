import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class AstarSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;

    public AstarSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getSteps()+one.getHeuristic() - (two.getSteps()+two.getHeuristic()));
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
