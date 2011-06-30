package extend.scalefree;

public class JxScaleFreeEdge {
	
	private int m_id;
	private Object owner;
	private int m_nodefrom;   
	private int m_nodeto;     
	private int m_bandwith;  
    private int m_weight;        
	private int m_distance; 

	// @todo what does it used for?
	private int packetsum;
	
	public JxScaleFreeEdge(){
		super();
		this.owner = null;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwith = 0;
		this.m_weight = 0;
	}
	
	public JxScaleFreeEdge( Object owner ){
		super();
		this.owner = owner;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwith = 0;
		this.m_weight = 0;
	}

	public JxScaleFreeEdge(int edgeid, int nodefrom, int nodeto, int bandwith, int weight){
		super();
		this.owner = null; // todo
		this.m_id=edgeid;
		this.m_nodefrom =nodefrom;
		this.m_nodeto = nodeto;
		this.m_bandwith = bandwith;
		this.m_weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result +m_nodefrom;
		result = prime * result + m_nodeto;
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
		JxScaleFreeEdge other = (JxScaleFreeEdge) obj;
		if (m_nodefrom != other.m_nodefrom)
			return false;
		if (m_nodeto != other.m_nodeto)
			return false;
		return true;
	}

	@Override
	public String toString() { //Ĭ�ϵ���
		return "JxScaleFreeEdge [m_nodefrom=" +m_nodefrom + ", nodeto=" + m_nodeto
			+ "]";
	}
/*
	double distance()
	{
		JxScaleFreeNode node1 = JxScaleFreeNodeCollection.search(m_nodefrom);
		JxScaleFreeNode node2 = JxScaleFreeNodeCollection.search(nodeto);
		
		return Math.sqrt(((node1.x() - node2.x()) * (node1.x() - node2.x())
				+ (node1.y() - node2.y()) * (node1.y() - node2.y())));
		
	}
*/	
	public int id() {  
		return m_edgeid;
	}
	public void set_id(int id) {  
		 m_edgeid=id;
	}
	public int nodefrom(){
		return m_nodefrom;
	}
	public void  set_nodefrom(int nodefrom){
		 m_nodefrom=nodefrom;
	}
	public int nodeto(){
		return m_nodeto;
	}
	public int set_nodeto(int node_to){
		return m_nodeto=node_to;
	}
	public int get_bandwidth() {        
		return m_bandwith;
	}
	
	public int distance() {  
		return m_distance;
	}

	public void set_distance(int distance) {
		this.m_distance = distance;
	}
	
	// @todo Why add this function 
	// that's the meaning of this function?
    public int get_packetsum(){
		return packetsum;
	}
    
	// @todo Why add this function 
    public void  set_packetsum(int sum){
    	packetsum=sum;
    }

}
