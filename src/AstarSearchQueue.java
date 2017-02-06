import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class AstarSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;
    HashSet<Node> visited;

    public AstarSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> {
            if (one.getPathCost()+one.getHeuristic() == two.getPathCost()+two.getHeuristic()) {
                return one.getPriority() - two.getPriority();
            }
            return one.getPathCost()+one.getHeuristic() - (two.getPathCost()+two.getHeuristic());
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
