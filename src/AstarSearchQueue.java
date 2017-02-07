import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terrence Park and Skye Pekerti
 * Queue used in the generic algorithm for an A* Search
 */
public class AstarSearchQueue extends SearchQueue {

    PriorityQueue<Node> queue; //The priority queue of nodes that are the successors which need to be expanded
    HashSet<Node> visited; //Set of nodes which are already visited to prevent revisiting states

    public AstarSearchQueue(char[] startState, boolean useCost) { //Constructor of the search queue for A*
        queue = new PriorityQueue<>((one, two) -> { //Use of lambda to set the comparing of nodes for the priority queue
            //Checks to see if the path costs + heuristic of nodes one and two are equal, used as the
            //tiebreaker if their path costs + heuristics are the same
            if (one.getPathCost()+one.getHeuristic() == two.getPathCost()+two.getHeuristic()) {
                return one.getPriority() - two.getPriority();
            }
            //Return the value of the path cost + heuristic to see which node has a higher priority
            //in terms of their path cost plus heuristic. value>0 = one, value<0 = two
            return one.getPathCost()+one.getHeuristic() - (two.getPathCost()+two.getHeuristic());
        });
        data = queue; //Gives the stack of nodes, only used for the isEmpty function
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
