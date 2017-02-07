
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class GSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;
    HashSet<Node> visited;

    public GSSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getHeuristic() - two.getHeuristic());
        data = queue;
        visited = new HashSet<>();
        Node start = new Node(startState, useCost);
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
