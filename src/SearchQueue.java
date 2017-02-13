import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Terrence Park and Skye Pekerti
 * Abstract superclass for all queues used in the different searches.
 */
public abstract class SearchQueue {

    /**
     * Holds a basic representation of successors that can be expanded.
     */
    Collection<Node> data;

    /**
     * Performs the insert function given a list of successors.
     * @param successors list of successors of the current node
     */
    public abstract void insert(ArrayList<Node> successors);

    /**
     * Removes the next state to be expanded
     * @return next state to expand
     */
    public abstract Node remove();

    /**
     * Checks if state {@code curr} has already been expanded. This should
     * return {@code null} in local searches.
     * @param curr the state to check
     * @return true if {@code curr} has been expanded
     */
    public abstract boolean didVisit(Node curr);

    /**
     * Marks state {@code curr} as expanded. This should only print out the
     * current state in local searches.
     * @param curr the state to mark
     */
    public abstract void visit(Node curr);

    /**
     * Checks if state {@code curr} is a goal state.
     * @param curr the state to check
     * @return true if {@code curr} is a goal state
     */
    public boolean goalTest(Node curr) {
        char[] state = curr.getState();
        boolean seenSpace = false;  //true if the space has been seen

        for (char c : state) {
            //Check if a black tile is to the right of the space or a white
            //tile is to the left of the space
            if (c == 'B' && seenSpace || c == 'W' && !seenSpace) {
                //Tile is out of place, not a goal state
                return false;
            }
            if (c == 'X') {
                //We have seen the space now
                seenSpace = true;
            }
        }

        //All black tiles are to the left of the space and all white tiles
        //are to the right of the space, goal state found
        return true;
    }

    /**
     * Checks if there are no more states to expand.
     * @return true if no states are left
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Creates a {@code SearchQueue} for search type {@code type} for
     * starting state {@code startState}.
     * @param type the search type
     * @param startState the starting state
     * @param useCost true if tile moves use distance as the cost
     * @return a {@code SearchQueue} object as described above, null if search type doesn't exist
     */
    public static SearchQueue createQueue(String type, char[] startState, boolean useCost) {
        SearchQueue queue = null;

        //Check type to set queue to the correct queue type
        switch (type) {
        case "DFS":
            queue = new DFSSearchQueue(startState, useCost);
            break;
        case "BFS":
            queue = new BFSSearchQueue(startState, useCost);
            break;
        case "UCS":
            queue = new UCSSearchQueue(startState, useCost);
            break;
        case "GS":
            queue = new GSSearchQueue(startState, useCost);
            break;
        case "A-star":
            queue = new AstarSearchQueue(startState, useCost);
            break;
        case "HCS":
            queue = new HCSSearchQueue(startState, useCost);
            break;
        case "SA":
            queue = new SASearchQueue(startState, useCost);
            break;
        }

        return queue;
    }
}
