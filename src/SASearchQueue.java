import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Created by Terrence Park and Skye Pekerti
 * Queue used in the generic algorithm for Simulated Annealing
 */
public class SASearchQueue extends SearchQueue
{

    ArrayList<Node> list; //The list of nodes that are the successors available
	int timestep; //Current time of the simulation for the SA algorithm
	Node curnode; //Keeps track of the current node if the next node is not chosen
	
    public SASearchQueue(char[] startState, boolean useCost) //Constructor of the search queue for SA
	{
        list = new ArrayList<>();
        data = list; //Gives the list of nodes, only used for the isEmpty function
		Node start = new Node(startState, useCost); //Initialization of the start state
		curnode = start;
		timestep = 1;
		list.add(start);
    }

    @Override
    public void insert(ArrayList<Node> successors) //Override of the insert method which inserts valid successors to list
	{
		Node bestnode = null; //Keeps track of the best node under the heuristic
		list.clear(); //Clears the list so no previous successors stay in the list
		for (Node n : successors) //Loop which runs through each of the nodes n in sucessors to find the best one
		{
			if(bestnode == null || n.getHeuristic() < bestnode.getHeuristic()) //Determines if n in successors is better than the one in bestnode
			{
				bestnode = n;
			}
		}
		list.add(curnode); //Adds the current node to list
		list.add(bestnode); //Adds the next node to list
		timestep++; //Increment timestep for the SA algorithm
    }

	@Override
    public Node remove() //Override of the remove method which removes and returns the chosen node based on the temperature
	{
		double temperature = 1000*Math.pow(0.995,timestep);
		if(list.size()==1) //Check to see if the only node in list is the start state, returns and removes it
		{
			return list.remove(0);
		}
		curnode = list.get(0);
		Node next = list.get(1);
        double E = next.getHeuristic()-curnode.getHeuristic();
		if(E>0) //Check to see if the heuristic of next is better the the heuristic is of the current node and return next if true
		{
			return list.remove(1);
		}
		else //Otherwise see if the next node can be chosen based on the current temperature of the algorithm
		{
			Random rand = new Random();
			if(Math.pow(Math.E,(E/temperature))>=rand.nextDouble()) //Check if the next node is randomly chosen and return next if true
			{
				return list.remove(1);
			}
		}
		return list.remove(0); //Return of current node if next is not chosen
    }

	@Override
	public boolean didVisit(Node curr) //Override of the didVisit function but unused for SA
	{
		return false;
	}

	@Override
	public void visit(Node curr) //Override of the visit function but unused for SA
	{
	}
}
