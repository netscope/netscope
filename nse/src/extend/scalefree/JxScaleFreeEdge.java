package extend.scalefree;
import kernel.JiBaseRelation;
/**
 * @author lipengfei
 *
 */
public class JxScaleFreeEdge implements JiBaseRelation {
	
	private Object m_owner;
		
	private int m_id;
	
	private int m_nodefrom;   
	private int m_nodeto;  
	
	private int m_bandwidth;  
    private int m_weight;        
	private int m_distance; 
	
	//count the packet-traffics on each edge 
	private int packetsum; 
	
	public JxScaleFreeEdge()
	{
		super();
		this.m_owner = null;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwidth = 0;
		this.m_weight = 0;
	}
	
	public JxScaleFreeEdge(int nodefrom,int nodeto)
    { 	
    	super();
    	this.m_nodefrom = nodefrom;
		this.m_nodeto = nodeto; 	
    }
	
    public JxScaleFreeEdge(int nodefrom,int  nodeto, int bandwidth)
    {
    	super();
    	this.m_nodefrom=nodefrom;
    	this.m_nodeto=nodeto;
    	this.m_bandwidth=bandwidth;	
    }
    
    public JxScaleFreeEdge(int edgeid, int nodefrom, int nodeto, int bandwidth,int weight) 
    {
		super();
		this.m_owner = null; 
		this.m_id = edgeid;
		this.m_nodefrom = nodefrom;
		this.m_nodeto = nodeto;
		this.m_bandwidth = bandwidth;
		this.m_weight = weight;
	}

	public JxScaleFreeEdge( Object owner )
	{	
		super();
		this.m_owner = owner;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwidth = 0;
		this.m_weight = 0;
	}

	public int distance() 
	{  
		return m_distance;
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

	public int get_bandwidth() 
	{        
		return m_bandwidth;
	}
    public int get_packetsum()
    {
		return packetsum;
	}
	@Override
	public int hashCode() 
	{		
		final int prime = 31;
		int result = 1;
		result = prime * result +m_nodefrom;
		result = prime * result + m_nodeto;
		return result;
	}
	public int id()
	{  
		return m_id;
	}
	public void set_id(int id)
	{  
		 m_id=id;
	}
	
	
	public int getnodefrom()
	{
		return m_nodefrom;
	}
	public void  set_nodefrom(int nodefrom)
	{
		 m_nodefrom=nodefrom;
	}
	
	public int getnodeto()
	{
		return m_nodeto;
	}
	public int set_nodeto(int node_to)
	{
		return m_nodeto=node_to;
	}
	
	
	public void set_distance(int distance)
	{
		this.m_distance = distance;
	}
	
	
    public void  set_packetsum(int sum)
    {
    	packetsum=sum;
    }
    
	@Override
	public String toString() 
	{ 
		return "JxScaleFreeEdge [m_nodefrom=" +m_nodefrom + ", nodeto=" + m_nodeto
			+ "]";
	}

}
