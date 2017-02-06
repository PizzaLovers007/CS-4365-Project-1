import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class GSSearchQueue extends SearchQueue {

    PriorityQueueQueue<Node> queue;

    public GSSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getHeuristic() - two.getHeuristic());;
        data = queue;
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
