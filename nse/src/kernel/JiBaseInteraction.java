package kernel;

/**
 * Describe the interaction rule among nodes listed in the relation object.
 * 
 * @author Allen
 *
 */

public interface JiBaseInteraction {
	
	/** 
	 * Return the owner of the interaction rule object. The owner is often the simulator
	 * or the engine itself.
	 * 
	 * @return
	 */
	public Object getOwner();
	
	/**
	 * Set the owner of this interaction rule object.
	 * @return
	 */
	public void setOwner( Object owner );
	
	//public JiBaseTrace getTrace();	
	//public void setTrace( JiBaseTrace trace );	
	
	/**
	 * Perform interaction among some nodes describe by the relation object.
	 * @param relation The Relation object containing all the nodes to be affected.
	 */
	//public void interact(int time, JiBaseRelation relation, JiBaseTrace trace);
	public void interact(JiBaseRelation relation, JiBaseTrace trace);
}
