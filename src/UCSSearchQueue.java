import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 * Queue used in the generic algorithm for a Uniform Cost Search
 */
public class UCSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue; //The priority queue of nodes that are the successors which need to be expanded
    HashSet<Node> visited; //Set of nodes which are already visted to prevent revisiting states

    public UCSSearchQueue(char[] startState, boolean useCost) { //Constructor of the search queue for UCS
        queue = new PriorityQueue<>((one, two) -> { //Use of lambda to set the comparing of nodes for the priority queue
            if (one.getPathCost() == two.getPathCost()) { //Checks to see if the path costs of nodes one and two are equal, used as the tiebreaker if their path costs are the same
                return one.getPriority() - two.getPriority();
            }
            return one.getPathCost() - two.getPathCost(); //Return the value of the path cost to see which node has a higher priority in terms of their path cost. value>0 = one, value<0 = two
        });
        data = queue; //Gives the stack of nodes, only used for the isEmpty function
        visited = new HashSet<>();
        Node start = new Node(startState, 0, useCost); //Initialization of the start state
        queue.add(start);
    }

    @Override
    public void insert(ArrayList<Node> successors) { //Override of the insert method which inserts all the successors of the current node to the queue
        for (Node n : successors) {
            queue.add(n);
        }
    }

    @Override
    public Node remove() { //Override of the remove method which removes and returns the head of the queue
        return queue.remove();
    }

    @Override
    public boolean didVisit(Node curr) { //Override of the didVisit method which returns the boolean if a node has been visited
        return visited.contains(curr);
    }

    @Override
    public void visit(Node curr) { //Override of the visit method which sets a node as visited
        visited.add(curr);
    }
}
