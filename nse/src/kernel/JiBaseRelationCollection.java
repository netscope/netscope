package kernel;

import java.util.Iterator;

public interface JiBaseRelationCollection {
	public void setTrace( JiBaseTrace trace );
	public void generate();
    public void randomize();	
    Iterator<JiBaseRelation> iterator();
}
