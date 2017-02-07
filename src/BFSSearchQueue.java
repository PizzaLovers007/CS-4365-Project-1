import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Terrence Park and Skye Pekerti
 * Queue used in the generic algorithm for a Breadth-First Search
 */
public class BFSSearchQueue extends SearchQueue {

    LinkedList<Node> queue; //The queue of nodes that are the successors which need to be expanded
    HashSet<Node> visited; //Set of nodes which are already visted to prevent revisiting states

    public BFSSearchQueue(char[] startState, boolean useCost) { //Constructor of the search queue for BFS
        queue = new LinkedList<>();
        data = queue; //Gives the queue of nodes, only used for the isEmpty function
        visited = new HashSet<>();
        Node start = new Node(startState, useCost); //Initialization of the start state
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
