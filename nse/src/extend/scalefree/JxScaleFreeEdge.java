package extend.scalefree;
import kernel.JiBaseRelation;
import kernel.*;
/**
 * @author lipengfei
 *
 */
public class JxScaleFreeEdge extends JxBaseRelation
{
		       
	private int m_distance; 
	
	/** count the packet-traffics on each edge */ 
	private int packet; 
	
	public JxScaleFreeEdge()
	{
		super();	
	}

	public JxScaleFreeEdge( Object owner )
	{	
		this.m_owner = owner;	
	}
	
	public JxScaleFreeEdge(JxScaleFreeNode nodefrom,JxScaleFreeNode nodeto)
    { 	
    	super();
    	this.m_nodefrom = nodefrom;
		this.m_nodeto = nodeto; 	
    }
	
	/**
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
*/

	public int distance() 
	{  
		return m_distance;
	}
    public void setDistance(int distance)
    {
    	this.m_distance=distance;
    }
    
    
    public void  setPacket(int sum)
    {
    	packet=sum;
    }
    public int getPacketsum()
    {
		return packet;
	}
    
	@Override
	public String toString() 
	{ 
		return "JxScaleFreeEdge [m_nodefrom=" +m_nodefrom + ", nodeto=" + m_nodeto
			+ "]";
	}

}
