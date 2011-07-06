package kernel;

public interface JiNode {
	
	public int getId();
	public void setId(int id);	
	
	public int getValue();//数据包个数
	public void setValue(int value);
   
	public int getDegree();
	public void setDegree(int i);
	
	public int getCapacity();
	
    public int getLocx();
    public void setLocx(int loc_x);
  
    public int getLocy();
    public void setLocy(int loc_y);

}
