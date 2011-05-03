package extend.scalefree;
import java.util.ArrayList;
/** 
 * @author Allen
 *
 */
/**
 * JxScaleFreeNode represents a single node in the network.
 */
public class JxScaleFreeNode {
	
	private int m_loc_x;
	
	private int m_loc_y;
	
	private int m_length;
	
	private int m_capacity;
	
	private ArrayList<JxScaleFreeEdge>  m_edgelist; //注意用法???
	
	private int m_nodeid;
	
	 public JxScaleFreeNode() {//构造函数1
		
		super();
		this.m_loc_x = 0;
		this.m_loc_y = 0;
		this.m_capacity = 0;
	}

	public JxScaleFreeNode(int x, int y, int capacity) {//构造函数2
		
		super();
		this.m_loc_x = x;
		this.m_loc_y = y;
		this.m_capacity = capacity;
	}

	@Override//覆盖
	public String toString() { //输出接点x,y坐标
		return "JxScaleFreeNode [m_loc_x=" + m_loc_x + ", m_loc_y=" + m_loc_y + "]";//注意引号用法
	}

	@Override//覆盖
	public int hashCode() {    //hash函数(需要对比开源程序看明白)
		
		final int prime = 31; //素数
		int result = 1;
		result = prime * result + m_capacity;
		result = prime * result + ((m_edgelist == null) ? 0 : m_edgelist.hashCode());
		result = prime * result + m_loc_x;
		result = prime * result + m_loc_y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {  //判断是否相等
		if (this == obj)   
			return true;   
		if (obj == null)   
			return false; 
		if (getClass()!= obj.getClass())
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

	public int get_nodeid() { 
		return m_nodeid;
	}
	
	public int get_x() { 
		return m_loc_x;
	}

	public void set_x(int loc_x) {
		this.m_loc_x = loc_x;
	}

	public int get_y() {
		return m_loc_y;
	}

	public void set_y(int loc_y) {
		this.m_loc_y = loc_y;
	}

	public int get_length() {  
		return m_length;
	}

	public void set_length(int length) {
		this.m_length = length;
	}

	public int get_capacity() {  
		return m_capacity;
	}

    /*public int get_weight() {
		return m_weight;
	  } 
 
	  public void set_weight(int weight) {
		this.m_weight = weight;
	  }
      */
	public void set_capacity(int capacity) {
		this.m_capacity = capacity;
	}

	public ArrayList<JxScaleFreeEdge> edgelist() {//（函数）边列表（邻集列表）？？？
		return m_edgelist;
	}
	public ArrayList<JxScaleFreeNode> neighborhood(){
		return  
	} 

   public int degree() {
		return m_edgelist.size(); //接点的度
	}
   
	public float degree_ratio_sum(){
		
	}  
	
	int setDegree(int number)	{   //设置节点的度？为何？
  
	    return 0 ;
	}
}
