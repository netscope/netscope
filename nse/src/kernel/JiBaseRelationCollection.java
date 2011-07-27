package kernel;

import java.util.Iterator;

public interface JiBaseRelationCollection {
	
	public void setTrace( JiBaseTrace trace );
	public boolean add( JiBaseRelation relation );
    Iterator<JiBaseRelation> iterator();
	
    public int count();
	
	
	public void generate();
    public void randomize();	
  
}
