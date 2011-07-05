package kernel;

public interface JiNode {
	
	public int getId();
	public void setId(int id);	
	
	public int getValue();//数据包个数
	public void setValue(int value);
   
	public int getDegree();
	public void setDegree(int i);
	
	public int getCapacity();
}
