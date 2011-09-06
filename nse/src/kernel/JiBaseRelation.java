package kernel;

import java.util.ArrayList;

/**
 * JiBaseRelation 
 * Defines the interface of the relation class. The relation class is used to save
 * relation information among node.
 *  
 * @author Allen
 * @modified by Allen on 2011.09.06
 * - Revision
 */
public interface JiBaseRelation {
	
	public enum JiRelationType{BI_DIRECTION_RELATION, SINGLE_DIRECTIOIN_RELATION, 
		BROADCAST_RELATION, GROUP_RELATION;};
    	
	public int getId();
	public void setId( int id );
	
	public Object getOwner();
	public void setOwner( Object owner );
	
	public JiRelationType getType();
	public void setType( JiRelationType type );
	
	/**
	 * Get all the nodes in the relation object.
	 *  
	 * The nodes relation depends on the relation type:
	 * 	- If the relation type is BI_DIRECTION_RELATION, then "node from" is the first
	 * element in the list, and "node to" is the second element. 
	 * 	- If the relation type is BROADCAST_RELATION, then the first node is the one 
	 * doing broadcasting, and all other nodes are listeners.
	 * 	
	 * @return
	 */
	public ArrayList<JiBaseNode> nodelist();
    
	public int getWeight();
	public void setWeight(int weight);
	
	public int getBandWidth();
	public void setBandWidth(int bandwidth);
	
	//public int getPacket();
	//public void setPacket(int sum);
	
	public JiBaseNode getNodeFrom();
	public void setNodeFrom(JiBaseNode nodeFrom);
	
	public JiBaseNode getNodeTo();
	public void setNodeTo(JiBaseNode nodeTo);
}
