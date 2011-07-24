package kernel;

import java.util.ArrayList;
import java.util.*;

public class JxBaseRelation implements JiBaseRelation {
	
	protected int m_id = 0; 
	protected Object m_owner = null;
	protected JiRelationType m_type = JiRelationType.BI_DIRECTION_RELATION;
	protected ArrayList<JiBaseNode> m_nodes = new ArrayList<JiBaseNode>;

	//protected int  m_nodefrom;
	//protected int  m_nodeto;
	protected int  m_bandwidth;
	protected int  m_weight;
	protected int  m_packetsum;
	
	protected Object m_value;
	
	

	Random random= new Random();

   static  JxBaseNodeCollection nodeSet=new JxBaseNodeCollection();
   static  JxBaseRelationCollection relationSet=new JxBaseRelationCollection();

    
    JiBaseNode node=new JxBaseNode();
    
    ArrayList<Integer> addedSet = new ArrayList<Integer>();
    
    public JxBaseRelation(){
    	m_id = 0;
		m_owner = null;
		m_nodefrom = 0;
		m_nodeto = 0;
		m_bandwidth = 0;
		m_weight = 0;
		m_packetsum=0;
	}
	
    public JxBaseRelation( int id ){
    	m_id = id;
		m_owner = null;
		m_nodefrom = 0;
		m_nodeto = 0;
		m_bandwidth = 0;
		m_weight = 0;
		m_packetsum=0;
	}
	
	public JxBaseRelation( int id, Object owner ){
		m_id = id;
		m_owner = owner;
		m_nodefrom = 0;
		m_nodeto = 0;
		m_bandwidth = 0;
		m_weight = 0;
		m_packetsum=0;
	}

    public JxBaseRelation ( int id, Object owner, int nodefrom,int nodeto ){
     	super();
     	m_id = id;
     	m_owner = owner;
     	m_type = JiRelationType.BI_DIRECTION_RELATION;
    	m_nodefrom = nodefrom;
		m_nodeto = nodeto;	
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
		return m_nodelist;
	}
		
	public void setBiDirRelation( JiBaseNode node1, JiBaseNode node2 )
	{
		m_nodelist.clear();
		m_nodelist.add( node1 );
		m_nodelist.add( node2 );
		m_type = JiRelationType.BI_DIRECTION_RELATION;
	}
	
	public void setSingleDirRelation( JiBaseNode nodefrom, JiBaseNode nodeto )
	{
		m_nodelist.clear();
		m_nodelist.add( nodefrom );
		m_nodelist.add( nodeto );
		m_type = JiRelationType.SINGLE_DIRECTIOIN_RELATION;
	}
	
	public void setBroadcastRelation( ArrayList<JiBaseNode> nodelist )
	{
		m_nodelist.clear();
		m_nodelist.addAll(nodelist);
		m_type = JiRelationType.BROADCAST_RELATION;
	}
	
	public void setGroupRelation( ArrayList<JiBaseNode> nodelist )
	{
		m_nodelist.clear();
		m_nodelist.addAll(nodelist);
		m_type = JiRelationType.GROUP_RELATION;
	}
	
	public int count()
	{
		return m_nodelist.size();
	}
	
	public void add( JiBaseNode node )
	{
		m_nodelist.add( node );
	}
	
	public void remove( JiBaseNode node )
	{
		m_nodelist.remove( node );
	}
	
	public JiBaseNode first()
	{
		//
		return null;
	}
	
	public JiBaseNode next()
	{
		//
		return null;
	}
	
	/**
	 * Returns the first one in the node list of the current relation object.
	 *  
	 * @return
	 */
	public JiBaseNode getNodeFrom(){
		return  m_nodelist.get(0);
	}
	/*
	public void setNodeFrom(int nodefrom){
		m_nodefrom=nodefrom;
	}
	*/
	
	/**
	 * Returns the second one in the node list of the current relation object.
	 *   
	 * @param nodefrom
	 */
	public JiBaseNode getNodeTo(){
		return  m_nodelist.get(1);
	}
	/*
	public void setNodeTo(int nodeto){
		m_nodeto=nodeto;	
	}
	*/
	public int getBandWidth(){
		return m_bandwidth;
	}
	
	public int getPacketSum(){
       return m_packetsum;		
	}
	public void setPacketSum(int sum){
		m_packetsum=sum;
	}
	
	@Override
  	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m_bandwidth;
		result = prime * result + m_nodefrom;
		result = prime * result + m_nodeto;
		result = prime * result + ((m_owner == null) ? 0 : m_owner.hashCode());
		result = prime * result + m_id;
		result = prime * result + ((m_type == null) ? 0 : m_type.hashCode());
		result = prime * result + m_weight;
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
		if (!m_nodelist.equals(other)) 
			return false;
		if (m_owner == null) {
			if (other.m_owner != null)
			return false;
		} 
		else if (!m_owner.equals(other.m_owner))
			return false;
		
		if (m_id != other.m_id)
			return false;
		if (m_type != other.m_type)
			return false;
		if (m_weight != other.m_weight)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "JxStdRelation [m_type="+m_type+",m_relation_id="+ m_id +",m_nodefrom=" + m_nodefrom
				+ ", m_nodeto=" + m_nodeto + ", m_bandwidth=" + m_bandwidth
				+ ", m_packetsum=" + m_packetsum+"]";
	}
   /** 最后一个点不用加如到addedset中
       if(i==nodecount-1)
	   currentNodeId=randomNodeSerial[i]; 
		   selectNodeId =selectnodeto();
		  
		   edgeCollection.add(selectNodeId, currentNodeId);

		JiNode currentNode=nodeCollection.get(currentNodeId);
		JiNode selectNode=nodeCollection.get(selectNodeId);
		
		/** 将当前点与选中点的度分别加1 
		int currentNodeDegree =currentNode.getDegree();
		currentNode.setDegree(currentNodeDegree+1);
		
		int selectNodeDegree=selectNode.getDegree();
		selectNode.setDegree(selectNodeDegree+1);
	}  */

}
