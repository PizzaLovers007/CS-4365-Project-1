import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Terrence Park and Skye Pekerti
 * Stack used in the generic algorithm for a Depth-First Search
 */
public class DFSSearchQueue extends SearchQueue {

    Stack<Node> queue; //The stack of nodes that are the successors which need to be expanded
    HashSet<Node> visited; //Set of nodes which are already visted to prevent revisiting states

    public DFSSearchQueue(char[] startState, boolean useCost) { //Constructor of the search queue for DFS
        queue = new Stack<>();
        data = queue; //Gives the stack of nodes, only used for the isEmpty function
        visited = new HashSet<>();
        Node start = new Node(startState, 0, useCost); //Initialization of the start state
        queue.push(start);
    }

    @Override
    public void insert(ArrayList<Node> successors) { //Override of the insert method which inserts all the successors of the current node to the stack
        Collections.reverse(successors);
        for (Node n : successors) {
            queue.add(n);
        }
    }

    @Override
    public Node remove() { //Override of the remove method which removes and returns the head of the stack
        return queue.pop();
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
