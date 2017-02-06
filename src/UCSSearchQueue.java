import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class UCSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;

    public UCSSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getHeuristic() - two.getHeuristic());
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
