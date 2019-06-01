package canon;

import java.util.LinkedList;

import static java.lang.System.out;
/**
 * @author Daniel Rosillo
 */
public class App implements Operations
{
    static public void main(String[] args)
    {
	App app = new App();
	
	app.runDemo();
	
    }

    public void runDemo()
    {
	String[] nature = {"1","2","3"};
	String[] options = {"A1","A2","A3"};
	
	LinkedList<State> stats = new LinkedList<>()
	{private static final long serialVersionUID = 1L;
	{
	    add(new State(nature[0],options[0],300d));
	    add(new State(nature[0],options[1],200d));
	    add(new State(nature[0],options[2],100d));
	    
	    add(new State(nature[1],options[0],-400D));
	    add(new State(nature[1],options[1],200D));
	    add(new State(nature[1],options[2],100d));
	    
	    add(new State(nature[2],options[0],-500d));
	    add(new State(nature[2],options[1],-300d));
	    add(new State(nature[2],options[2],100d));
	}};
	
	out.println(maxiMax(stats));
	out.println(maxiMin(stats));
	out.println(hurwicz(stats,.80d));
	out.println(laplace(stats));
	out.println(savage(stats));
	out.println("\n");

    }
    
}
