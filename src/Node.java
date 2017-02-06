import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class Node {

    private char[] state;
    private int moves;
    private Node prev;

    public Node(char[] st, int mov) {
        state = st;
        moves = mov;
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
            Node nextNode = new Node(nextState, moves+1);
            nextNode.prev = this;
            list.add(nextNode);
        }
        return list;
    }

    public char[] getState() {
        return state;
    }

    public int getMoves() {
        return moves;
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
}
