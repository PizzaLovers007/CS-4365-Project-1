import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class Driver {

    public void go(char[] startState, boolean useCost, String type) {
        ArrayList<Node> path = search(startState, useCost, type);
//        System.out.println();
        for (Node n : path) {
            System.out.println(n);
        }
    }

    public ArrayList<Node> search(char[] startState, boolean useCost, String type) {
        SearchQueue queue = SearchQueue.createQueue(type, startState, useCost);
        if (queue == null) {
            System.out.println("Search type argument was invalid.");
            System.exit(1);
        }
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            if (queue.didVisit(curr)) {
                continue;
            }
//            System.out.println(curr);
            queue.visit(curr);
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

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Incorrect number of arguments.");
            System.exit(1);
        }
        String filename, searchType;
        boolean useCost;
        if (args.length == 2) {
            filename = args[1];
            searchType = args[0];
            useCost = false;
        } else {
            filename = args[2];
            searchType = args[1];
            if (!args[0].equals("-cost")) {
                System.out.printf("Invalid flag: %s%n", args[0]);
                System.exit(1);
            }
            useCost = true;
        }
        try {
            Scanner in = new Scanner(new File(filename));
            char[] startState = in.nextLine().trim().toCharArray();
            new Driver().go(startState, useCost, searchType);
        } catch (FileNotFoundException e) {
            System.out.printf("Could not find file: %s%n", filename);
            System.exit(1);
        }
//        new Driver().go("WxBBW".toCharArray(), false, "SA");
    }
}
