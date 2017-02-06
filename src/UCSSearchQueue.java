import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class UCSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;
    HashSet<Node> visited;

    public UCSSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> {
            if (one.getPathCost() == two.getPathCost()) {
                return one.getPriority() - two.getPriority();
            }
            return one.getPathCost() - two.getPathCost();
        });
        data = queue;
        visited = new HashSet<>();
        Node start = new Node(startState, 0, useCost);
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
