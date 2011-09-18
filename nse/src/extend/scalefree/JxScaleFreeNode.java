package extend.scalefree;

import java.util.ArrayList;
import kernel.*;

/** 
 * @author Allen, LiPengfei (TongJi University)
 *
 */
/**
 * JxScaleFreeNode represents a single node in the network.
 */
public class JxScaleFreeNode extends JxBaseNode implements JiBaseNode 
{
	/** edge list enables the developer can find all neighbor edges rapidly */
	private ArrayList<JiBaseRelation> m_edgelist = null;
	
	private int degree;

	public JxScaleFreeNode() 
	{
		super();
	}
	
	public JxScaleFreeNode(Object owner,int id )
	{
		this.m_owner = owner;
		this.m_id=id;
	}

	public JxScaleFreeNode( int id,int loc_x,int loc_y ) 
	{
		super( id, loc_x, loc_y );
	}

	public JxScaleFreeNode( Object owner,int id,int loc_x,int loc_y,int capacity ) 
	{
		super( owner, id, loc_x, loc_y, capacity);
	}
   
	public JxScaleFreeNode(Object owner,int id,int loc_x,int loc_y,int length,int capacity)
	{
		super(owner,id,loc_x,loc_y,length,capacity);
	}

	public String toString()
	{ 
		return "JxScaleFreeNode [id="+m_id+",x=" + m_loc_x + ",y=" + m_loc_y+",length="+m_length+",capacity="+m_capacity
			+ "]";
	}

	/** 
	 * The degree of current node.
	 * @return
	 */
     public int degree() 
    {	   
		return this.degree; 
	}
	public void setdegree(int number)	
	{   
	    degree=number;
	}
	
	/**
	 * @return An array list containing the references to all neighbor edges.
	 */
	public ArrayList<JiBaseRelation> edgelist() 
	{
		return m_edgelist;
	}

	/**
	 * @return An array list containing the reference to all neighbor nodes.
	 */
	public ArrayList<JxScaleFreeNode> neighborhood()
	{
		return null;
	}

	
	public boolean addNeighbor( JxScaleFreeNode neighbornode )
	{
		boolean ret = false;
		
		/*
		search whether the edge existed.
		add the edge of <current node, neighbor node> into the edge list
		
		if 	m_edgelist
		*/
		return ret;
	}
}
