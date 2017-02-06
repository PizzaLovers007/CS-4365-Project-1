import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class Node {

    private static int nextPriority = 0;

    private boolean useCost;
    private char[] state;
    private int steps;
    private int move;
    private int cost;
    private int pathCost;
    private int priority;
    private Node prev;

    public Node(char[] state, int steps, boolean useCost) {
        this.state = state;
        this.steps = steps;
        this.move = -1;
        this.cost = 0;
        this.useCost = useCost;
        this.pathCost = 0;
        this.priority = nextPriority++;
    }

    public ArrayList<Node> successors() {
        int emptySpace = 0;
        for (int i = 1; i < state.length; i++) {
            if (state[i] == 'x') {
                emptySpace = i;
                break;
            }
        }
        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < state.length; i++) {
            if (i == emptySpace) {
                continue;
            }
            char[] nextState = Arrays.copyOf(state, state.length);
            nextState[emptySpace] = nextState[i];
            nextState[i] = 'x';
            Node nextNode = new Node(nextState, steps + 1, useCost);
            nextNode.move = i;
            nextNode.cost = Math.abs(i - emptySpace);
            nextNode.pathCost = nextNode.cost + pathCost;
            nextNode.prev = this;
            list.add(nextNode);
        }
        return list;
    }

    public int getHeuristic() {
        int numBlack = 0;
        for (char c : state) {
            if (c == 'B') {
                numBlack++;
            }
        }
        int outOfPlace = 0;
        for (int i = 0; i < state.length; i++) {
            if (i < numBlack && state[i] == 'W'
                    || i == numBlack && state[i] != 'x'
                    || i > numBlack && state[i] == 'B') {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    public char[] getState() {
        return state;
    }

    public int getPathCost() {
        if (useCost) {
            return pathCost;
        }
        return steps;
    }

    public int getPriority() {
        return priority;
    }

    public Node getPrev() {
        return prev;
    }

    public int hashCode() {
        return new String(state).hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node other = (Node)obj;
            if (state.length != other.state.length) {
                return false;
            }
            for (int i = 0; i < state.length; i++) {
                if (state[i] != other.state[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (move == -1) {
            sb.append(String.format("%s", new String(state)));
        } else {
            sb.append(String.format("Step %d: move %d %s", steps-1, move, new String(state)));
        }
        if (useCost && cost > 0) {
            sb.append(String.format(" (c=%d)", cost));
        }
        return sb.toString();
    }
}
