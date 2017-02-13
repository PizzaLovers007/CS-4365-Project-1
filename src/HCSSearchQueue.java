import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 * Queue used in the generic algorithm for Hill-Climbing Search.
 */
public class HCSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue;  //The queue of direct successors to the current state
    int iteration;  //The iteration number

    /**
     * Main constructor for creating a Hill-Climbing Search Queue.
     * @param startState the starting state
     * @param useCost true if tile moves use distance as the cost
     */
    public HCSSearchQueue(char[] startState, boolean useCost) {
        queue = new PriorityQueue<>((one, two) -> one.getHeuristic() - two.getHeuristic());
        data = queue;
        Node start = new Node(startState, useCost);
        queue.add(start);
        iteration = 0;
    }

    @Override
    public void insert(ArrayList<Node> successors) {
        //Empty queue of previous successors
        queue.clear();

        if (iteration < 10000) {
            //10000 iterations have not passed, add more successors
            queue.addAll(successors);
        }
    }

    @Override
    public Node remove() {
        iteration++;
        return queue.remove();
    }

    @Override
    public boolean didVisit(Node curr) {
        //Should always return false, local searches do not save previous states
        return false;
    }

    @Override
    public void visit(Node curr) {
        //Print current iteration
        System.out.printf("Iteration %d: %s%n", iteration, new String(curr.getState()));
    }
}
