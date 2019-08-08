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

    /*
     * @States -> Lista enlazada que contiene los estados a priori.
     */
    public default String savage(LinkedList<State>states)
    {
	LinkedList<String> options = states.stream().map(state->state.option()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<String> nature = states.stream().map(state->state.natureState()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<Double> values = new LinkedList<>();
	LinkedList<State> newStates = new LinkedList<>();
	State state;

	//Determinar para cada estado de la naturaleza la mejor ganancia.
	nature.forEach(ns->
	{
	    values.add(states.stream()
		    .filter(st->st.natureState().equalsIgnoreCase(ns))
		    .sorted(Comparator.comparing(State::value)
			    .reversed()).limit(1).map(m->m.value())
		    .collect(Collectors.toList()).get(0)); 
	});

	/*
	 * Calcular el costo de oportunidad para cada alternativa, como la diferencia entre su ganancia y la mejor calculada. 
	 * Una vez calculadas las diferencias se hace una nueva matriz en sustitución de los valores del resto de la columna, 
	 * esta diferencia es el costo de oportunidad o arrepentimiento por no haber escogido la alternativa que diera el valor óptimo.
	 */
	for(int j=0;j<nature.size();j++)
	{
	    for(int i=0;i<states.size();i++)
	    {
		State aux = states.get(i);
		if(nature.get(j).equalsIgnoreCase(aux.natureState()))aux.setValue(values.get(j)-aux.value());
	    }

	}

	//Generar un nuevo vector, escogiendo la mejor opción de cada alternativa.
	options.forEach(ns->
	{
	    newStates.add(states.stream()
		    .filter(st->st.option().equalsIgnoreCase(ns))
		    .sorted(Comparator.comparing(State::value).reversed()
			    ).limit(1)
		    .collect(Collectors.toList()).get(0)); 
	});

	//La alternativa con el menor valor dentro del vector es la respuesta.
	newStates.sort(Comparator.comparing(State::value));
	state = newStates.getFirst();

	return "Savage: The best option is " + state.option() + " at " + state.natureState() + " with a gain of " + state.value();
    }
    
    /*
     * @States -> Lista enlazada que contiene los estados a priori.
     */
    public default String laplace(LinkedList<State>states)
    {
	LinkedList<String> options = states.stream().map(state->state.option()).distinct().collect(Collectors.toCollection(LinkedList::new));
	LinkedList<String> nature = states.stream().map(state->state.natureState()).distinct().collect(Collectors.toCollection(LinkedList::new));
	State state;
	DecimalFormat format = new DecimalFormat("0.00");
	Double n = Double.parseDouble(format.format(1.0d/nature.size()));
	LinkedList<State> newStates = new LinkedList<>();
	 

	//Calcular la sumatoria de las probabilidades de cada alternativa por el valor a priori del estado.
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
	//Del vector resultante, escoger el mayor valor.
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
