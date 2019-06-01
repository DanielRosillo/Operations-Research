package canon;
/**
 * @author Daniel Rosillo
 */
public class State
{
    private String natureState;
    private String option;
    private Double value;
    
    public State()
    {
	option = "default";
	natureState = "default";
	value = 0.0d;
    }
    
    public State(String natureState,String option,Double value)
    {
	this.natureState = natureState;
	this.option = option;
	this.value =value;
    }
    
    public String option()
    {
	return option;
    }
    
    public String natureState()
    {
	return natureState;
    }
    
    public Double value()
    {
	return value;
    }
    
    public void setValue(Double value)
    {
	this.value = value;
    }
    
    public void setOption(String option)
    {
	this.option = option;
    }
    
    public void setNature(String natureState)
    {
	this.natureState = natureState;
    }
}
