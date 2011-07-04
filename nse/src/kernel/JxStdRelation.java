package kernel;

import extend.scalefree.JxScaleFreeEdge;

public class JxStdRelation implements JiRelation {
	
	
	private Object m_owner;
	private int m_relation_id; 
	private JiRelationType m_type;
	private int m_nodefrom;
	private int m_nodeto;
	private int m_bandwidth;
	private int m_weight;
	
	
    public JxStdRelation(){
		this.m_owner = null;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwidth = 0;
		this.m_weight = 0;
	}
	
    
	public JxStdRelation( Object owner ){
		this.m_owner = owner;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwidth = 0;
		this.m_weight = 0;
	}
	
	
    public JxStdRelation (int nodefrom,int nodeto){
     	super();
    	this.m_nodefrom = nodefrom;
		this.m_nodeto = nodeto;	
    }
    
    
    public JxStdRelation(int nodefrom,int  nodeto, int bandwidth){
    	super();
    	this.m_nodefrom=nodefrom;
    	this.m_nodeto=nodeto;
    	this.m_bandwidth=bandwidth;	
    }
    
    
	public JxStdRelation(int relationid, int nodefrom, int nodeto, int bandwidth, int weight){
		super();
		this.m_owner = null;
		this.m_relation_id=relationid;
        this.m_nodefrom =nodefrom;
		this.m_nodeto = nodeto;
		this.m_bandwidth = bandwidth;
		this.m_weight = weight;
	}
	
	
	
	public int getId(){
	  return m_relation_id;
   }
	public void setId(int id){
		m_relation_id=id; 	
   }
	
	
	public JiRelationType getType(){
		return  m_type;
	}
	public void setType(JiRelationType m_type){
		this.m_type=m_type;	
	}
	
	
	public int getNodeFrom(){
		return  m_nodefrom;
	}
	public void setNodeFrom (int nodefrom){
		this.m_nodefrom=nodefrom;
	}
	
	
	public int getNodeTo(){
		return m_nodeto;
	}
	public void setNodeTo(int nodeto){
		this.m_nodeto=nodeto;	
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m_bandwidth;
		result = prime * result + m_nodefrom;
		result = prime * result + m_nodeto;
		result = prime * result + ((m_owner == null) ? 0 : m_owner.hashCode());
		result = prime * result + m_relation_id;
		result = prime * result + ((m_type == null) ? 0 : m_type.hashCode());
		result = prime * result + m_weight;
		return result;
	}
    public JiRelation generate(){
	   
	   return null;
   }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JxStdRelation other = (JxStdRelation) obj;
		if (m_bandwidth != other.m_bandwidth)
			return false;
		if (m_nodefrom != other.m_nodefrom)
			return false;
		if (m_nodeto != other.m_nodeto)
			return false;
		if (m_owner == null) {
			if (other.m_owner != null)
				return false;
		} else if (!m_owner.equals(other.m_owner))
			return false;
		if (m_relation_id != other.m_relation_id)
			return false;
		if (m_type != other.m_type)
			return false;
		if (m_weight != other.m_weight)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "JxStdRelation [m_type=, m_nodefrom=" + m_nodefrom
				+ ", m_nodeto=" + m_nodeto + ", m_bandwidth=" + m_bandwidth
				+ ", m_weight=" + m_weight + "]";
	}
	
	
	
	
}
