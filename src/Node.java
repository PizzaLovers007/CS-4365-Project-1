import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Terrence Park and Skye Pekerti
 * Holds the data for each state.
 */
public class Node {

    /**
     * Keeps track of how many nodes have been created to maintain ordering in the search tree.
     */
    private static int nextPriority = 0;

    private boolean useCost;    //true if path cost should be the spaces a tile needs to move
    private char[] state;   //Current state representation
    private int steps;  //Number of steps to get to this state
    private int move;   //Tile number to move to get to this state
    private int cost;   //Cost of the tile move to get to this state
    private int pathCost;   //Total path cost to get to this state
    private int priority;   //Current priority of this state, lower means higher in the search tree
    private Node prev;  //Parent state of this state

    /**
     * Constructor for making a starting {@code Node}.
     * @param state the start state
     * @param useCost true if tile moves use distance as the cost
     */
    public Node(char[] state, boolean useCost) {
        this(state, 0, -1, 0, 0, useCost, null);
    }

    /**
     * Constructor for making a successor {@code Node}.
     * @param state the successor state
     * @param steps the number of steps to get to the successor
     * @param move the tile number to move to get to the successor
     * @param cost the cost of the tile move to get to the successor
     * @param pathCostParent the total path cost to get to the successor's parent
     * @param useCost true if tile moves use distance as the cost
     * @param prev the parent {@code Node} of the successor
     */
    private Node(char[] state, int steps, int move, int cost, int pathCostParent, boolean useCost, Node prev) {
        this.state = state;
        this.steps = steps;
        this.move = move;
        this.cost = cost;
        this.pathCost = cost + pathCostParent;
        this.useCost = useCost;
        this.prev = prev;
        this.priority = nextPriority++;
    }

    /**
     * Gets a list of successors of the current state.
     * @return list of successors
     */
    public ArrayList<Node> successors() {
        int emptySpace = 0; //Index of the empty space
        ArrayList<Node> list = new ArrayList<>();   //List of successors

        //Loop to find the index of the empty space
        for (int i = 1; i < state.length; i++) {
            if (state[i] == 'X') {
                emptySpace = i;
                break;
            }
        }

        //Loop to create the successors
        for (int i = 0; i < state.length; i++) {
            //Cannot swap empty space with the empty space, so skip creating this successor
            if (i == emptySpace) {
                continue;
            }

            //Create a copy of the current state
            char[] nextState = Arrays.copyOf(state, state.length);

            //Put i-th tile in the empty space
            nextState[emptySpace] = nextState[i];
            nextState[i] = 'X';

            //Create a successor Node and add it to the list
            Node nextNode = new Node(nextState, steps + 1, i, Math.abs(i - emptySpace), pathCost, useCost, this);
            list.add(nextNode);
        }

        //Return successors
        return list;
    }

    /**
     * Gets the heuristic of the current state.
     * @return the heuristic value
     */
    public int getHeuristic() {
        int numBlack = 0;   //Number of black tiles

        //Loop to count the number of black tiles
        for (char c : state) {
            if (c == 'B') {
                numBlack++;
            }
        }

        //state[numBlack] should hold the space
        //Any white tiles to the left of the correct space position is out of place
        //Any black tiles to the right of the correct space position is out of place
        //Any tile on the correct space position is out of place

        int outOfPlace = 0; //Number of tiles that are out of place

        //Loop to check if the i-th tile is out of place
        for (int i = 0; i < state.length; i++) {
            //Perform checks as described above
            if (i < numBlack && state[i] == 'W'
                    || i == numBlack && state[i] != 'X'
                    || i > numBlack && state[i] == 'B') {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    /**
     * Gets the state representation.
     * @return the state representation
     */
    public char[] getState() {
        return state;
    }

    /**
     * Gets the total path cost to the current state.
     * @return the total path cost
     */
    public int getPathCost() {
        if (useCost) {
            return pathCost;
        }
        return steps;
    }

    /**
     * Gets the priority of the state. Lower priority means higher up and further left
     * in the search tree.
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Gets the parent of the current state.
     * @return the parent
     */
    public Node getPrev() {
        return prev;
    }

    public int hashCode() {
        return new String(state).hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node other = (Node)obj;

            //Check if states are the same length
            if (state.length != other.state.length) {
                return false;
            }

            //Check each character
            for (int i = 0; i < state.length; i++) {
                if (state[i] != other.state[i]) {
                    //Found differing character
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        //On start state, only have the state description
        if (move == -1) {
            sb.append(String.format("%s", new String(state)));
        } else {
            sb.append(String.format("Step %d: move %d %s", steps-1, move, new String(state)));
        }

        //If -cost flag is active, put extra (c=?) info
        if (useCost && cost > 0) {
            sb.append(String.format(" (c=%d)", cost));
        }

        return sb.toString();
    }
}
