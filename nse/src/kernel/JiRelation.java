
package kernel;
public interface JiRelation {
public enum JiRelationType{BI_DIRECTION, SINGLE_DIRECTIOIN, BROADCAST_RELATION, GROUP_RELATION;};
    
	
	public int getId();
	public void setId( int id );
	
	
	public JiRelationType getType();
	public void setType(JiRelationType type);
	
	
	public int getNodeFrom();
	public void setNodeFrom( int nodefrom);
    
	
	public int getNodeTo();
	public void setNodeTo( int nodeto);
	
	
	public int getBandWidth();
	
	
	public int getPacketSum();
	public void setPacketSum(int sum);
	
}
