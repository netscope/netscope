package extend.scalefree;

import java.util.ArrayList;

/** 
 * @author Allen, LiPengfei (TongJi University)
 *
 */
/**
 * JxScaleFreeNode represents a single node in the network.
 */
public class JxScaleFreeNode {
	
	private int m_id;
	private int m_loc_x;
	private int m_loc_y;
	private int m_length;
	private int m_capacity; 
	private int degree;
	
	/* edge list enables the developer can find all neighbor nodes rapidly */
	private ArrayList<JxScaleFreeEdge> m_edgelist;

	public JxScaleFreeNode() {
		
		super();
		this.m_loc_x = 0;
		this.m_loc_y = 0;
		this.m_capacity = 0;
	}

	public JxScaleFreeNode(int node_id,int loc_x,int loc_y, int m_length,int capacity) {
		//super();
		this.m_id =node_id;
		
		this.m_loc_x = loc_x;
		
		this.m_loc_y = loc_y;
		
		this.m_length = m_length;  //网络负载量的大小
		
		this.m_capacity = capacity;
	}

	@Override
	public String toString() { 
		return "JxScaleFreeNode [m_loc_x=" + m_loc_x + ", m_loc_y=" + m_loc_y
			+ "]";
	}

	/**
	 * Generate an nearly unique identifier of current object for hashing operations. 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31; // 素数
		int result = 1;
		result = prime * result + m_capacity;
		result = prime * result + ((m_edgelist == null) ? 0 : m_edgelist.hashCode());
		result = prime * result + m_loc_x;
		result = prime * result + m_loc_y;
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
		JxScaleFreeNode other = (JxScaleFreeNode) obj;
		if (m_capacity != other.m_capacity)
			return false;
		if (m_edgelist == null) {
			if (other.m_edgelist != null)
				return false;
		} else if (!m_edgelist.equals(other.m_edgelist))
			return false;
		if (m_length != other.m_length)
			return false;
		if (m_loc_x != other.m_loc_x)
			return false;
		if (m_loc_y != other.m_loc_y)
			return false;
		return true;
	}

	public int id() {
		return m_id;
	}

	public void set_id(int id) {
		this.m_id = id;
	}

	public int x() {
		return m_loc_x;
	}

	public void set_x(int loc_x) {
		this.m_loc_x = loc_x;
	}

	public int y() {
		return m_loc_y;
	}

	public void set_y(int loc_y) {
		this.m_loc_y = loc_y;
	}

	public int  get_length() {
		return m_length;
	}

	public void set_length(int length) {
		this.m_length = length;
	}

	public int get_capacity() {
		return m_capacity;
	}

	/*
	 * public int get_weight() { return m_weight; }
	 * 
	 * public void set_weight(int weight) { this.m_weight = weight; }
	 */
	public void set_capacity(int capacity) {
		this.m_capacity = capacity;
	}

 /*  public ArrayList<JxScaleFreeEdge> edgelist() {//（函数）边列表（邻集列表）？？？
		
		return m_edgelist;
	}
	public ArrayList<JxScaleFreeNode> neighborhood(){
		
		return null;  
		
	} 
*/
     public int degree() {
	   
		return this.degree; //接点的度
	}
 
	public void set_degree(int number)	{   //设置节点的度？为何？
	   
		this.degree=number;
	}
	/**
	 * @return An array list containing the references to all neighbor edges.
	 */
	public ArrayList<JxScaleFreeEdge> edgelist() {
		return m_edgelist;
	}

	/**
	 * @return An array list containing the reference to all neighbor nodes.
	 */
	public ArrayList<JxScaleFreeNode> neighborhood() {
		return null;
	}

	/** 
	 * The degree of current node.
	 * @return
	 */
/*	public int degree() {
		return m_edgelist.size(); 
	} */
	
	public boolean add_neighbor( JxScaleFreeNode neighbornode )
	{
		boolean ret = false;
		
		/*
		search whether the edge existed.
		add the edge of <current node, neighbor node> into the edge list
		
		if 
		m_edgelist
		*/
		return ret;
	}
}
