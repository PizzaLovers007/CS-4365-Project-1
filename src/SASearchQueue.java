import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public class SASearchQueue extends SearchQueue
{

    ArrayList<Node> list;
	int timestep;
	Node curnode;
	
    public SASearchQueue(char[] startState, boolean useCost)
	{
        list = new ArrayList<>();
        data = list;
		Node start = new Node(startState, 0, useCost);
		curnode = start;
		timestep = 1;
		list.add(start);
    }

    @Override
    public void insert(Collection<Node> successors)
	{
		Node bestnode = null;
		list.clear();
		for (Node n : successors) {
			if(bestnode == null || n.getHeuristic() < bestnode.getHeuristic())
			{
				bestnode = n;
			}
		}
		list.add(curnode);
		list.add(bestnode);
		timestep++;
    }

    @Override
    public Node remove()
	{
		double temperature = 1000*Math.pow(0.995,timestep);
		double E = next.getHeuristic()-curnode.getHeuristic();
		if(list.size()==1)
		{
			return list.remove(0);
		}
		curnode = list.get(0);
		Node next = list.get(1);
		if(E>0)
		{
			return list.remove(1);
		}
		else
		{
			Random rand = new Random();
			if(Math.pow(2.71828,(E/temperature))>=rand.nextDouble())
			{
				return list.remove(1);
			}
		}
		return list.remove(0);
    }
}
