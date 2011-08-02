package kernel;

import java.util.*;

public class JxBaseRelation implements JiBaseRelation {
	
	
	protected int m_id = 0; 
	protected Object m_owner = null;
	protected Object m_value;
	
	
	protected JiRelationType m_type = JiRelationType.BI_DIRECTION_RELATION;
	
	protected ArrayList<JiBaseNode> m_nodes = new ArrayList<JiBaseNode>();
	
	
	protected JiBaseNode  m_nodefrom;
	protected JiBaseNode  m_nodeto;
	
	
	protected int  m_bandwidth;
	protected int  m_weight;
	protected int  m_packetsum;
	
	
	protected Random m_random = JxBaseFoundation.random();

	
    JiBaseNode node=new JxBaseNode();
    
    public JxBaseRelation()
    {
    	m_id = 0;
		m_owner = null;
		m_bandwidth = 0;
		m_weight = 0;
		m_packetsum=0;
	}
	
    public JxBaseRelation( int id ){
    	m_id = id;
		m_owner = null;
		m_bandwidth = 0;
		m_weight = 0;
		m_packetsum=0;
	}
	
	public JxBaseRelation( int id, Object owner ){
		m_id = id;
		m_owner = owner;
		m_bandwidth = 0;
		m_weight = 0;
		m_packetsum=0;
	}

    public JxBaseRelation ( int id, Object owner, int nodefrom,int nodeto ){
     	super();
     	m_id = id;
     	m_owner = owner;
     	m_type = JiRelationType.BI_DIRECTION_RELATION;
		m_packetsum=0;
    }
    
	@Override
	public int getId(){
	  return m_id;
   }
	@Override
	public void setId(int id){
		m_id=id; 	
   }
	
	
	@Override
	public Object getOwner() {
		return m_owner;
	}
	@Override
	public void setOwner(Object owner) {
		m_owner = owner;
	}
	
	
	@Override
	public JiRelationType getType(){
		return  m_type;
	}
	@Override
	public void setType(JiRelationType type){
		m_type = type;	
	}
	
	
	@Override
	public ArrayList<JiBaseNode> nodelist() {
		return m_nodes;
	}
		
	public void setBiDirRelation( JiBaseNode node1, JiBaseNode node2 )
	{
		m_nodes.clear();
		m_nodes.add( node1 );
		m_nodes.add( node2 );
		m_type = JiRelationType.BI_DIRECTION_RELATION;
	}
	
	public void setSingleDirRelation( JiBaseNode nodefrom, JiBaseNode nodeto )
	{
		m_nodes.clear();
		m_nodes.add( nodefrom );
		m_nodes.add( nodeto );
		m_type = JiRelationType.SINGLE_DIRECTIOIN_RELATION;
	}
	
	public void setBroadcastRelation( ArrayList<JiBaseNode> nodelist )
	{
		m_nodes.clear();
		m_nodes.addAll(nodelist);
		m_type = JiRelationType.BROADCAST_RELATION;
	}
	
	
	public void setGroupRelation( ArrayList<JiBaseNode> nodelist )
	{
		m_nodes.clear();
		m_nodes.addAll(nodelist);
		m_type = JiRelationType.GROUP_RELATION;
	}
	
	
	public int count()
	{
		return m_nodes.size();
	}
	
	
	public void add( JiBaseNode node )
	{
		m_nodes.add( node );
	}
	public void remove( JiBaseNode node )
	{
		m_nodes.remove( node );
	}
	
	
	public JiBaseNode first()
	{
		return null;
	}
	public JiBaseNode next()
	{
		return null;
	}
	
	
	/**
	 * Returns the first one in the node list of the current relation object.
	 *  
	 * @return
	 */
	public JiBaseNode getNodeFrom(){
		return  m_nodes.get(0);
	}
	
	
	/**
	 * Returns the second one in the node list of the current relation object.
	 *   
	 * @param nodefrom
	 */
	public JiBaseNode getNodeTo()
	{
		return m_nodes.get(1);
	}
	
	
	public int getBandWidth()
	{
		return m_bandwidth;
	}
	public int setBandWidth(int bandwidth)
	{
		return m_bandwidth=bandwidth;
	}
	
	
	public int getPacketSum()
	{
       return m_packetsum;		
	}
	public void setPacketSum(int sum)
	{
		m_packetsum=sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m_bandwidth;
		result = prime * result + m_id;
		result = prime * result
				+ ((m_nodefrom == null) ? 0 : m_nodefrom.hashCode());
		result = prime * result + ((m_nodes == null) ? 0 : m_nodes.hashCode());
		result = prime * result
				+ ((m_nodeto == null) ? 0 : m_nodeto.hashCode());
		result = prime * result + ((m_owner == null) ? 0 : m_owner.hashCode());
		result = prime * result + m_packetsum;
		result = prime * result
				+ ((m_random == null) ? 0 : m_random.hashCode());
		result = prime * result + ((m_type == null) ? 0 : m_type.hashCode());
		result = prime * result + ((m_value == null) ? 0 : m_value.hashCode());
		result = prime * result + m_weight;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JxBaseRelation other = (JxBaseRelation) obj;
		if (m_bandwidth != other.m_bandwidth)
			return false;
		if (m_id != other.m_id)
			return false;
		if (m_nodefrom == null) {
			if (other.m_nodefrom != null)
				return false;
		} else if (!m_nodefrom.equals(other.m_nodefrom))
			return false;
		if (m_nodes == null) {
			if (other.m_nodes != null)
				return false;
		} else if (!m_nodes.equals(other.m_nodes))
			return false;
		if (m_nodeto == null) {
			if (other.m_nodeto != null)
				return false;
		} else if (!m_nodeto.equals(other.m_nodeto))
			return false;
		if (m_owner == null) {
			if (other.m_owner != null)
				return false;
		} else if (!m_owner.equals(other.m_owner))
			return false;
		if (m_packetsum != other.m_packetsum)
			return false;
		if (m_random == null) {
			if (other.m_random != null)
				return false;
		} else if (!m_random.equals(other.m_random))
			return false;
		if (m_type != other.m_type)
			return false;
		if (m_value == null) {
			if (other.m_value != null)
				return false;
		} else if (!m_value.equals(other.m_value))
			return false;
		if (m_weight != other.m_weight)
			return false;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}
	
	

}
