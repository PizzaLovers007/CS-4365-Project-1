import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Terrence Park and Skye Pekerti
 * Driving class that gets the commandline arguments and has the generic search algorithm.
 */
public class Driver {

    /**
     * Runs the search algorithm and prints the path to the goal state.
     * @param startState the starting state
     * @param useCost true if tile moves use distance as the cost
     * @param type the search type
     */
    public void go(char[] startState, boolean useCost, String type) {
        //Print iteration header if local search HCS
        if (type.equals("HCS")) {
            System.out.println(new String(startState));
        }

        //Get the path from the search algorithm
        ArrayList<Node> path = search(startState, useCost, type);

        //Print final result header if local search
        if (type.equals("HCS") || type.equals("SA")) {
            System.out.println();
            System.out.printf("Final Result For %s:%n", type);
        }

        //Check if the path is 10000+, this should only be the case if HCS
        //or SA fail to find a solution
        if (path.size() >= 10000) {
            System.out.println("No solution could be found");
        } else {
            //Print the path
            for (Node n : path) {
                System.out.println(n);
            }
        }
    }

    /**
     * Performs a search of type {@code type} starting at state {@code startState}.
     * @param startState the starting state
     * @param useCost true if tile moves use distance as the cost
     * @param type the search type
     * @return the path the search algorithm took to find the goal state
     */
    public ArrayList<Node> search(char[] startState, boolean useCost, String type) {
        //Create the search queue using type
        SearchQueue queue = SearchQueue.createQueue(type, startState, useCost);

        //Check if the queue was created properly (null if search type doesn't exist)
        if (queue == null) {
            System.err.println("Search type argument was invalid.");
            System.exit(1);
        }

        //Loop until queue is empty
        while (!queue.isEmpty()) {
            //Get next state in the queue
            Node curr = queue.remove();

            //Check if state has been expanded already
            if (queue.didVisit(curr)) {
                continue;
            }

            //Mark state as expanded
            queue.visit(curr);

            //Check if state is the goal
            if (queue.goalTest(curr)) {
                //State is the goal, so return the path to the goal
                return pathToGoal(curr);
            }

            //State is not the goal, so get successors
            ArrayList<Node> successors = curr.successors();

            //Insert successors into the queue
            queue.insert(successors);
        }

        //Goal state could not be found, so no path exists
        return null;
    }

    /**
     * Gets the list of the states in order from start to goal.
     * @param goal the goal state
     * @return the list of states
     */
    public ArrayList<Node> pathToGoal(Node goal) {
        ArrayList<Node> path = new ArrayList<>();   //List of states from start to goal

        //Add the goal state to the list
        path.add(goal);

        //Loop until the last state in the list does not have a parent state
        while (path.get(path.size()-1).getPrev() != null) {
            //Add parent state to end of the list
            path.add(path.get(path.size()-1).getPrev());
        }

        //Reverse the list to make the path order correct
        Collections.reverse(path);

        return path;
    }

    /**
     * Main method.
     * @param args the commandline arguments
     */
    public static void main(String[] args) {
        //Check if the number of arguments is valid
        if (args.length < 2 || args.length > 3) {
            System.err.println("Incorrect number of arguments.");
            System.exit(1);
        }

        String filename;    //Filename from the arguments
        String searchType;  //Search type from the arguments
        boolean useCost;    //true if -cost flag was added

        //Check if -cost flag was added
        if (args.length == 2) {
            //No -cost flag
            filename = args[1];
            searchType = args[0];
            useCost = false;
        } else {
            //Has -cost flag
            filename = args[2];
            searchType = args[1];
            useCost = true;

            //Check if the first argument is the -cost flag
            if (!args[0].equals("-cost")) {
                System.err.printf("Invalid flag: %s%n", args[0]);
                System.exit(1);
            }
        }

        try {
            //Get the starting state from the input file
            Scanner in = new Scanner(new File(filename));
            char[] startState = in.nextLine().toUpperCase().trim().toCharArray();

            //Run the main program
            new Driver().go(startState, useCost, searchType);

        } catch (FileNotFoundException e) {
            System.err.printf("Could not find file: %s%n", filename);
            System.exit(1);
        }
    }
}
