package kernel;

import java.util.Iterator;

public interface JiBaseRelationCollection {
	
	public void setTrace( JiBaseTrace trace );
	
    public int count();
	public boolean add(JiBaseRelation relation);
	
	public void generate();
    public void randomize();	
    Iterator<JiBaseRelation> iterator();
}
