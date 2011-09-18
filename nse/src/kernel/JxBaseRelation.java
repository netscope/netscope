package kernel;

import java.util.*;

/**
 * JxBaseRelation
 * Describes the relationship among different nodes. You can extend this class
 * to add your own properties.
 * 
 * @author Allen
 */
public class JxBaseRelation implements JiBaseRelation
{
	protected Object m_owner = null;
	protected int  m_id; 

	protected JiRelationType m_type = JiRelationType.BI_DIRECTION_RELATION;
	protected ArrayList<JiBaseNode> m_nodes = new ArrayList<JiBaseNode>();
	
	
	protected int m_weight = 0;
	protected int m_bandwidth = 0;
	
	// Statistical data
	// total sent = total received + total lost
	protected int m_totalsent;
	protected int m_totalreceived; 
	protected int m_totallost;
	
	
	JiBaseNode node=new JxBaseNode();
	protected Random m_random = JxBaseFoundation.random();
    
    public JxBaseRelation()
    {
    	m_id = 0;
		m_owner = null;
		m_bandwidth = 0;
		m_weight = 0;
		m_totalsent = 0;
	}
	
    public JxBaseRelation( int id )
    {
    	m_id = id;
		m_owner = null;
		m_bandwidth = 0;
		m_weight = 0;
		m_totalsent = 0;
	}
	
    public JxBaseRelation( Object owner )
    {
    	m_id = 0;
		m_owner = null;
		m_bandwidth = 0;
		m_weight = 0;
		m_totalsent=0;
	}
	
	public JxBaseRelation( Object owner, int id )
	{
		m_id = id;
		m_owner = owner;
		m_bandwidth = 0;
		m_weight = 0;
		m_totalsent=0;
	}

    public JxBaseRelation ( Object owner,int id, int nodefrom,int nodeto )
    {
     	super();
     	m_id = id;
     	m_owner = owner;
     	m_type = JiRelationType.BI_DIRECTION_RELATION;
		m_totalsent=0;
    }
    
	@Override
	public int getId()
	{
	  return m_id;
    }
	@Override
	public void setId(int id)
	{
		m_id=id; 	
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
	public JiRelationType getType()
	{
		return  m_type;
	}
	@Override
	public void setType(JiRelationType type)
	{
		m_type = type;	
	}
	 
	public int getPacket()
	{
		return m_totalsent;
	}
    public void setPacket(int totalsent)
    {
	   m_totalsent=totalsent;
    }
   
	@Override
	public ArrayList<JiBaseNode> nodelist() 
	{
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
	
	/**
	 * Set broadcast relation. This is essentially the master-slave relation. 
	 * The first node will be the sender and all other nodes will be the receiver. 
	 * 
	 * @param nodelist
	 */
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
		assert false;
		return null;
	}
	
	public JiBaseNode next()
	{
		assert false;
		return null;
	}
	
	/**
	 * Returns the first one in the node list of the current relation object.
	 *  
	 * @return
	 */
	@Override
	public JiBaseNode getNodeFrom()
	{
		assert(m_nodes.size() > 0);
		return m_nodes.get(0); 
	}
	
	@Override
	public void setNodeFrom(JiBaseNode nodeFrom)
	{
		if (m_nodes.size() == 0)
			m_nodes.add(nodeFrom);
		else
			m_nodes.set(0, nodeFrom);
	}
	
	/**
	 * Returns the second one in the node list of the current relation object.
	 *   
	 * @param nodefrom
	 */
	@Override
	public JiBaseNode getNodeTo()
	{
		assert(m_nodes.size() > 0);
		return m_nodes.get(1);
	}
	
	@Override	
	public void setNodeTo(JiBaseNode nodeTo)
	{
		switch (m_nodes.size())
		{
		  case 0:
			 // You should call setNodeFrom first
			  assert(false);
			  m_nodes.add(null);
			  break;
		  case 1:
			 m_nodes.add(nodeTo);
			 break;
		  default:
			 m_nodes.set(1, nodeTo);
	  }
	}
	
	@Override
	public int getWeight()
	{
		return m_weight;
	}
	
	@Override
	public void setWeight(int weight)
	{
		 m_weight = weight;
	}
	
	@Override
	public int getBandWidth()
	{
		return m_bandwidth;
	}
	
	@Override
	public void setBandWidth(int bandwidth)
	{
		 m_bandwidth=bandwidth;
	}

	public int getTotalSent()
	{
       return m_totalsent;		
	}
	public void setTotalSent(int totalsent)
	{
		m_totalsent=totalsent;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + m_id;
		result += m_bandwidth;
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
		
		JxBaseRelation other = (JxBaseRelation) obj;
		return ((m_id == other.m_id) && (m_bandwidth == other.m_bandwidth));
	}
	public String tostring()
	{
		return String.format("JxBaseRelation [m_id=%d,m_nodefrom=%d,m_nodeto=%d,m_totalsent=%d]", m_id,m_nodes.get(0),m_nodes.get(1),m_totalsent);
	}
}
