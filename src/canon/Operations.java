package canon;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
/**
 * @author Daniel Rosillo;
 */
public interface Operations
{
    
    public default String maxiMax(LinkedList<State>states)
    {
	State state = states.stream().sorted(Comparator.comparing(State::value).reversed()).limit(1).collect(Collectors.toList()).get(0);
	return "MaxiMax: The best option is " + state.option() + " at " + state.natureState() + " with a gain of " + state.value();
    }
    
    public default String maxiMin(LinkedList<State>states)
    {
	LinkedList<String> options = states.stream().map(state->state.option()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<State> source = new LinkedList<>();
	State state;
	
	options.forEach(ns->
	{
	    source.add(states.stream()
		.filter(st->st.option().equalsIgnoreCase(ns))
		.sorted(Comparator.comparing(State::value)
			).limit(1)
		.collect(Collectors.toList()).get(0)); 
	});
	
	source.sort(Comparator.comparing(State::value).reversed());
	state = source.getFirst();	
	
	return "Wald: The best option is " + state.option() + " at " + state.natureState() + " with a gain of " + state.value();
    }

    public default String savage(LinkedList<State>states)
    {
	LinkedList<String> options = states.stream().map(state->state.option()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<String> nature = states.stream().map(state->state.natureState()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<Double> values = new LinkedList<>();
	LinkedList<State> newStates = new LinkedList<>();
	State state;

	nature.forEach(ns->
	{
	    values.add(states.stream()
		    .filter(st->st.natureState().equalsIgnoreCase(ns))
		    .sorted(Comparator.comparing(State::value)
			    .reversed()).limit(1).map(m->m.value())
		    .collect(Collectors.toList()).get(0)); 
	});

	for(int j=0;j<nature.size();j++)
	{
	    for(int i=0;i<states.size();i++)
	    {
		State aux = states.get(i);
		if(nature.get(j).equalsIgnoreCase(aux.natureState()))aux.setValue(values.get(j)-aux.value());
	    }

	}

	options.forEach(ns->
	{
	    newStates.add(states.stream()
		    .filter(st->st.option().equalsIgnoreCase(ns))
		    .sorted(Comparator.comparing(State::value).reversed()
			    ).limit(1)
		    .collect(Collectors.toList()).get(0)); 
	});

	newStates.sort(Comparator.comparing(State::value));
	state = newStates.getFirst();

	return "Savage: The best option is " + state.option() + " at " + state.natureState() + " with a gain of " + state.value();
    }
    
    public default String laplace(LinkedList<State>states)
    {
	LinkedList<String> options = states.stream().map(state->state.option()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<String> nature = states.stream().map(state->state.natureState()).distinct().collect(Collectors.toCollection(LinkedList::new));
	State state;
	DecimalFormat format = new DecimalFormat("0.00");
	Double n = Double.parseDouble(format.format(1.0d/nature.size()));
	LinkedList<State> newStates = new LinkedList<>();
	 
	options.forEach(op->
	{
	   Double value = 0.0d;
	   State source = new State();
	   for(State st:states)
	       if(st.option().equalsIgnoreCase(op))
	       {
		   value+=(n*st.value());
		   source = st;
	       }
	   newStates.add(new State(source.natureState(),source.option(),value));
	});
	
	newStates.sort(Comparator.comparing(State::value).reversed());
	state = newStates.getFirst();

	return "Laplace: The best option is " + state.option() + " at " + state.natureState() + " with a gain of " + state.value();
    }
    
    
    public default String hurwicz(LinkedList<State>states,Double alfa)
    {
	LinkedList<String> options = states.stream().map(state->state.option()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<State> source = new LinkedList<>();
	LinkedList<State> last = new LinkedList<>();
	State state;
	DecimalFormat format = new DecimalFormat("0.00");
	Double alfa_1 = Double.parseDouble(format.format(1.0d-alfa));
	
	options.forEach(ns->
	{
	    source.add(states.stream()
		.filter(st->st.option().equalsIgnoreCase(ns))
		.sorted(Comparator.comparing(State::value)
			).limit(1)
		.collect(Collectors.toList()).get(0)); 
	    
	    source.add(states.stream()
			.filter(st->st.option().equalsIgnoreCase(ns))
			.sorted(Comparator.comparing(State::value).reversed()
				).limit(1)
			.collect(Collectors.toList()).get(0)); 
	    
	   Double value = (source.get(1).value()*alfa)+(source.get(0).value()*alfa_1);
	   last.add(new State(source.get(0).natureState(),source.get(0).option(),value));
	   source.clear();
	});
	
	last.sort(Comparator.comparing(State::value).reversed());
	state = last.getFirst();	
	return "Hurwicz: The best option is " + state.option() + " at " + state.natureState() + " with a gain of " + state.value();
    }

}
