package kernel;
public class JxBaseNode implements JiBaseNode {
	
	private int m_id;
	private int m_loc_x;
	private int m_loc_y;
	private int m_length;
	private int m_capacity; 
	private int m_degree;
	
	
	private JxBaseRelationCollection relationCollection;


    public  JxBaseNode() {	
    this.m_loc_x = 0;
	this.m_loc_y = 0;
	this.m_capacity = 0;
   }
    
   public JxBaseNode(int loc_x,int loc_y,int capacity ) {	
		this.m_loc_x = loc_x;
		this.m_loc_y = loc_y;
		this.m_capacity = capacity;
    }
   
   public JxBaseNode(int node_id,int loc_x,int loc_y,int capacity) {
		this.m_id =node_id;
		this.m_loc_x = loc_x;
		this.m_loc_y = loc_y;
		this.m_capacity = capacity;
	} 
   
   public JxBaseNode(int node_id,int loc_x,int loc_y, int m_length,int capacity) {
		this.m_id =node_id;
		this.m_loc_x = loc_x;
		this.m_loc_y = loc_y;
		this.m_length = m_length;  //网络负载量的大小
		this.m_capacity = capacity;
	}
   public String toString() { 
		return "JxStdNode [m_id="+ m_id +",m_loc_x= " + m_loc_x + ", m_loc_y=" + m_loc_y+",m_length="+m_length+"]";
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
		result = prime * result + ((relationCollection == null) ? 0 : relationCollection.hashCode());
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
		
		JxBaseNode other = (JxBaseNode) obj;
		if (m_capacity != other.m_capacity)
			return false;
		
		if (relationCollection == null) {
			if (other.relationCollection != null)
				return false;
			
		} else if (!relationCollection.equals(other.relationCollection))
			return false;
		if (m_length != other.m_length)
			return false;
		if (m_loc_x != other.m_loc_x)
			return false;
		if (m_loc_y != other.m_loc_y)
			return false;
		return true;
	}

	
	public int getId(){
		return m_id;
	}
	public void setId(int id){
		m_id=id;
	}
	
	
	public int getValue(){
		return m_length;
	}
	public void setValue(int value){
		 m_length=value;
	}
	
	
	public int getDegree(){ 
		return m_degree;	
	}
	public void setDegree(int degree){
		 this.m_degree=degree;	
	}

	public  int getCapacity(){
	  return m_capacity;	
	}
	
	public int getLocx(){
		return m_loc_x;
	}
	public void setLocx(int loc_x){
		m_loc_x=loc_x;
	}
	
	public int getY(){
		return m_loc_y;
	}
	public void setY(int loc_y){
		m_loc_y=loc_y;
	}
	
}

