import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class AstarSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;
    HashSet<Node> visited;

    public AstarSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getSteps()+one.getHeuristic() - (two.getSteps()+two.getHeuristic()));
        data = queue;
        visited = new HashSet<>();
        Node start = new Node(startState, 0, useCost);
        queue.add(start);
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
        return queue.remove();
    }
}
