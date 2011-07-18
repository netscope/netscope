package kernel;

/**
 * JiNode: Node Interface. 
 * Node, Relation and Interaction are three main entities to model an complex system.
 * 
 * @author Allen
 * @modified by Allen on 2011.07.08
 */

public interface JiNode {
	
	/**
	 * Return the Id of the current node object. 
	 * Each node object has an unique identifier (Id) in the system. 
	 * @return
	 */
	public int getId();
	public void setId(int id);	
	
	/**
	 * Return the value of the current node object. The semantic of the value is 
	 * determined by the simulation itself. For example, it can be explained as
	 * "queue length".    
	 * @return
	 */
	public int getValue();
	public void setValue(int value);
   
	public int getDegree();
	public void setDegree(int i);
	
	public int getCapacity();
	
    public int getLocx();  
    public void setLocx(int loc_x);
  
    public int getLocy();
    public void setLocy(int loc_y);

}
