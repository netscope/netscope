package kernel;

/**
 * JiNode: Node Interface. Node, Relation and Interaction are three main
 * entities to model an complex system.
 * 
 * @author Allen
 * @modified by Allen on 2011.07.08
 * - Revision
 * @modified by Allen on 2011.09.06
 * - Revision
 */

public interface JiBaseNode {

	/**
	 * Return the Id of the current node object. Each node object has an unique
	 * identifier (Id) in the system.
	 * 
	 * @return
	 */
	public int getId();
	public void setId(int id);

	
	public int getLength();
	public void setLength(int length);
	/**
	 * Get the owner of the current node object. The owner of a node is always
	 * an an node collection object (JxBaseNodeCollection object)
	 * 
	 * @return The owner of the current node object.
	 */
	public Object getOwner();
	public void setOwner(Object owner);
	
	public int getWeight();
	public void setWeight(int value);

	/**
	 * Return the value of the current node object. The semantic of the value is
	 * determined by the simulation itself. For example, it can be explained as
	 * "queue length".
	 * 
	 * @return
	 */
	public int getValue();
	public void setValue(int value);

	public int getDegreeIn();
	public int getDegreeOut();
	
	public int getTotalTraffic();
	public int getTotalLost();
	
	public int getCapacity();
	public void setCapacity(int value);

	public int getX();
	public void setX(int x);
	
	public int getY();
	public void setY(int y);

	public int getZ();
	public void setZ(int z);
}
