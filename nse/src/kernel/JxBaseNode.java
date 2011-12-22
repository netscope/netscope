package kernel;
import java.util.*;
/**
 * JxBaseNode
 * Saves the property of a basic node. You can get your own node class by extending
 * this one. 
 * 
 * @author Allen
 * @section History
 * - modified by zhangwei on 2011.09.06
 */
public class JxBaseNode implements JiBaseNode {

	protected int m_id = 0;
	
	protected int m_weight = 0;
	
	protected int m_loc_x = 0;
	protected int m_loc_y = 0;
	protected int m_loc_z = 0;
	
	protected int m_length = 0;
	protected int m_capacity = 0;
	
	protected int stat_degreein = 0;
	protected int stat_degreeout = 0;
	
    protected int stat_totaltraffic = 0;
    protected int stat_totallost = 0;
    
    protected int traffic_in=0;
    protected int traffic_out=0;
    protected int traffic_lost=0;
	
    protected Random m_random=new Random();
	protected Object m_owner = null;
	
	protected ArrayList<JiBaseNode> m_neighborNodes=new ArrayList<JiBaseNode>();
	protected ArrayList<JiBaseRelation> m_neighborRelations=new ArrayList<JiBaseRelation>();

	public JxBaseNode()
	{
		m_id = 0;
		m_owner = null;
		m_loc_x = 0;
		m_loc_y = 0;
		m_loc_z = 0;
		m_length = 0;
		m_capacity = 0;
	}

	public JxBaseNode(Object owner, int id) 
	{
		m_id = id;
		m_owner = owner;
		m_loc_x = 0;
		m_loc_y = 0;
		m_loc_z = 0;
		m_length = 0;
		m_capacity = 0;
	}

	public JxBaseNode(Object owner, int id, int x, int y) 
	{
		m_id = id;
		m_owner = owner;
		m_loc_x = x;
		m_loc_y = y;
		m_length = 0;
		m_capacity = 0;
	}

	public JxBaseNode(Object owner, int id, int x, int y,int z) 
	{
		m_id = id;
		m_owner = owner;
		m_loc_x = x;
		m_loc_y = y;
		m_loc_z = z;
		m_length = 0;
		m_capacity = 0;
	}

	public JxBaseNode(Object owner,int id,int x,int y,int length,int capacity) 
	{
		m_id = id;
		m_owner = owner;
		m_loc_x = x;
		m_loc_y = y;
		m_length= length;
		m_capacity = capacity;
	}

	public JxBaseNode(int id) 
	{
		m_owner = null;
		m_id = id;
		m_loc_x = 0;
		m_loc_y = 0;
		m_loc_z = 0;
		m_length = 0;
		m_capacity = 0;
	}

	public JxBaseNode(int id, int x, int y) 
	{
		m_id = id;
		m_loc_x = x;
		m_loc_y = y;
		m_loc_z = 0;
		m_length = 0;
		m_capacity = 0;

	}

	public JxBaseNode(int id, int x, int y, int z) 
	{
		m_id = id;
		m_loc_x = x;
		m_loc_y = y;
		m_loc_z = z;
		m_length = 0;
		m_capacity = 0;
	}

	public JxBaseNode(int id, int x, int y, int z, int capacity) 
	{
		m_id = id;
		m_loc_x = x;
		m_loc_y = y;
		m_loc_z = z;
		m_length = 0;
		m_capacity = capacity;
	}

	@Override
	public String toString() 
	{
		return String.format(
				"JxBaseNode [id=%d, x=%d, y=%d, z=%d, length=%d, capacity=%d,stat_degreein=%d]",
				m_id, m_loc_x, m_loc_y, m_loc_z, m_length, m_capacity,stat_degreein);
	}

	/**
	 * Generate an nearly unique identifier of current object for hashing
	 * operations.
	 * 
	 * @attention The following hashCode calculation is for stablized node. You
	 *            should modify it if the node is in moving state.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		// result = prime * result + super().hashCode();
		result = prime * result + m_id;
		result += m_loc_x;
		result += m_loc_y;
		result += m_loc_z;
		result += m_capacity;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		JxBaseNode other = (JxBaseNode) obj;
		if ((m_id != other.m_id) || (m_owner != other.m_owner)
				|| (m_loc_x != other.m_loc_x) || (m_loc_y != other.m_loc_y)
				|| (m_loc_z != other.m_loc_z) || (m_length != other.m_length)
				|| (m_capacity != other.m_capacity)) {
			return false;
		} else
			return true;
	}

	@Override
	public int getId() 
	{
		return m_id;
	}

	@Override
	public void setId(int id)
	{
		m_id = id;
	}

	@Override
	public Object getOwner()
{
		return m_owner;
	}

	@Override
	public void setOwner(Object owner)
	{
		m_owner = owner;
	}

	@Override
	public int getWeight()
	{
		return m_weight;
	}

	@Override
	public void setWeight(int value)
	{
		m_weight = value;
	}

	@Override
	public int getValue() {
		return m_length;
	}

	@Override
	public void setValue(int value) {
		m_length = value;
	}

	@Override
	public int getCapacity() {
		return m_capacity;
	}

	@Override
	public void setCapacity(int value) {
		m_length = value;
	}

	@Override
	public int getX() {
		return m_loc_x;
	}

	@Override
	public void setX(int x) {
		m_loc_x = x;
	}

	@Override
	public int getY() {
		return m_loc_y;
	}

	@Override
	public void setY(int y) {
		m_loc_y = y;
	}

	@Override
	public int getZ() {
		return m_loc_z;
	}

	@Override
	public void setZ(int z) {
		m_loc_z = z;
	}
	
	public int getLength()
	{
		return m_length;
	}
	
	public void setLength(int length)
	{
		m_length=length;
	}

	public int getDegreeIn() 
	{
		return stat_degreein;
	}

	public void setDegreeIn(int stat_degreein) 
	{
		this.stat_degreein = stat_degreein;
	}

	public int getDegreeOut()
	{
		return stat_degreeout;
	}

	public void setDegreeOut(int stat_degreeout)
	{
		this.stat_degreeout = stat_degreeout;
	}

	public int getTotalTraffic() 
	{
		return stat_totaltraffic;
	}

	public void setTotalTraffic(int stat_totaltraffic)
	{
		this.stat_totaltraffic = stat_totaltraffic;
	}

	public int getTotalLost() 
	{
		return stat_totallost;
	}

	public void setTotallost(int stat_totallost) 
	{
		this.stat_totallost = stat_totallost;
	}
	
	public void  setTrafficIn(int trafficIn)
	{
	   traffic_in = trafficIn;
	}
	
	public int getTrafficIn()
	{
		return  traffic_in;
	}
	
	public void  setTrafficOut(int trafficOut)
	{
		  traffic_out=trafficOut;   
	}
	
	public int getTrafficOut()
	{
		return traffic_out;
	}
	
	public void  setTrafficLost(int trafficLost)
	{
		traffic_lost=trafficLost;
	}
	
	public int  getTrafficLost()
	{
		return traffic_lost;
	}

	public ArrayList<JiBaseNode> getNeighborNodes() 
	{
		return m_neighborNodes;
	}

	public ArrayList<JiBaseRelation> getNeighborRelations() 
	{
		return m_neighborRelations;
	}
	
	
	public void addNeighborNode(JiBaseNode neighborNode) 
	{
		m_neighborNodes.add(neighborNode);
	}
	
	public void addNeighborRelation(JiBaseRelation neighborRelation) 
	{
	   m_neighborRelations.add(neighborRelation);
	}
	
	
	public int  neighborNodeSize()
	{
		return m_neighborNodes.size();
	}
	
	public int  neighborRelationSize()
	{
		return m_neighborRelations.size();
	}
	
	
	public JiBaseNode getNeighborNode(int index)
	{
	   JiBaseNode neighborNode = m_neighborNodes.get(index);
	   
	   return neighborNode;
	}
	
	public JiBaseRelation getNeighborRelation(int index)
	{
	   JiBaseRelation neighborRelation = m_neighborRelations.get(index);
	   
	   return neighborRelation;
	}
}
