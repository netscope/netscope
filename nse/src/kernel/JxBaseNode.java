package kernel;
public class JxBaseNode implements JiBaseNode {
	
	protected int m_id;
	protected Object m_owner;
	protected int m_loc_x;
	protected int m_loc_y;
	protected int m_length = 0;
	protected int m_capacity = 0; 
	
	
    public JxBaseNode() {
    	m_id = 0;
    	m_loc_x = 0;
    	m_loc_y = 0; 
    	m_length = 0;
    	m_capacity = 0; 	
   }
    
    public JxBaseNode( Object owner, int id ) {
    	m_owner=owner;
    	m_id = id;
    	m_loc_x = 0;
    	m_loc_y = 0;  
    	m_length = 0;
    	m_capacity = 0;  
   }
    
    public JxBaseNode( int id ) {
    	m_id = id;
    	m_loc_x = 0;
    	m_loc_y = 0;
    	m_length = 0;
    	m_capacity = 0; 	
   }
    
    public JxBaseNode(int id, int x, int y ) {	
    	m_id = id;
    	m_loc_x = x;
    	m_loc_y = y;   
     	m_length = 0;
     	m_capacity = 0;
     
     }
   
   public JxBaseNode(Object owner,int id, int x, int y, int capacity) {
		m_id =id;
		m_loc_x = x;
		m_loc_y = y;
    	m_length = 0;
		m_capacity = capacity;
	} 
   
   public JxBaseNode(int id, int x, int y,int length,int capacity) {
		m_id =id;
		m_loc_x = x;
		m_loc_y = y;		
     	m_length = length;
		m_capacity = capacity;   
	}
   
	@Override
   public String toString() { 
		return String.format( "JxBaseNode [id=%d, x=%d, y=%d, length=%d]", m_id, m_loc_x, m_loc_y,
				 m_length );
	}

	/**
	 * Generate an nearly unique identifier of current object for hashing operations. 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31; 
		int result = 1;
		result = prime * result + m_id;
		result = prime * result + m_owner.hashCode();
		result = prime * result + m_loc_x;
		result = prime * result + m_loc_y;
		result = prime * result + m_loc_x;
		result = prime * result + m_capacity;
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
		
		if (m_owner == null) {
			if (other.m_owner != null)
				return false;
		}

		if (m_length != other.m_length)
			return false;
		if (m_loc_x != other.m_loc_x)
			return false;
		if (m_loc_y != other.m_loc_y)
			return false;
		
		return true;
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
	
	
	public int getValue(){
		return m_length;
	}
	public void setValue(int value){
		 m_length = value;
	}
	
	public  int getCapacity(){
	  return m_capacity;	
	}
	
	@Override
	public int getX(){
		return m_loc_x;
	}
	@Override
	public void setX(int x){
		m_loc_x = x;
	}
	
	
	@Override
	public int getY(){
		return m_loc_y;
	}
	@Override
	public void setY(int y){
		m_loc_y = y;
	}

	
}

