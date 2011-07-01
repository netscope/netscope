package kernel;
import java.util.ArrayList;

public interface JiRelation {
	public enum JiRelationType{BI_DIRECTION, SINGLE_DIRECTIOIN, BROADCAST_RELATION, GROUP_RELATION;};

	public int getId();
	public void setId( int id );
	
	public int getType();
	public void setType(int type);
	
	public int getNodeFrom();
	public void setNodeFrom( int nodeid );

	public int getNodeTo();
	public void setNodeTo( int nodeid );
	
	// public ArrayList<int> getAllNodesTo();
	// public ArrayList<int> getNeighborNodes();
	// public ArrayList<int> getAllNodes();
}
