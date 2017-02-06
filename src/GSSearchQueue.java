import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 * Queue used in the generic algorithm for a Greedy Search
 */
public class GSSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue; //The priority queue of nodes that are the successors which need to be expanded
    HashSet<Node> visited; //Set of nodes which are already visted to prevent revisiting states

    public GSSearchQueue(char[] startState, boolean useCost) { //Constructor of the search queue for GS
        queue = new PriorityQueue<>((one, two) -> one.getHeuristic() - two.getHeuristic()); //Use of lambda to set the comparing of nodes for the priority queue
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
