package kernel;

public class JxStdNode implements JiNode {
	
	private int m_id;
	private int m_loc_x;
	private int m_loc_y;
	private int m_length;
	private int m_capacity; 
	private int degree;
	 
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
}

