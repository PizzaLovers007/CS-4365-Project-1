import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class Driver {

    public static void main(String[] args) {

    }

    public ArrayList<Node> search(char[] startState, boolean useCost, String type) {
        SearchQueue queue = SearchQueue.createQueue(type, startState, useCost);
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            if (queue.goalTest(curr)) {
                return pathToGoal(curr);
            }
            ArrayList<Node> successors = curr.successors();
            queue.insert(successors);
        }
        return null;
    }

    public ArrayList<Node> pathToGoal(Node goal) {
        ArrayList<Node> path = new ArrayList<>();
        path.add(goal);
        while (path.get(path.size()-1).getPrev() != null) {
            path.add(path.get(path.size()-1).getPrev());
        }
        Collections.reverse(path);
        return path;
    }
}
