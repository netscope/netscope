package kernel;

public interface JiBaseRelationCollection {
	
	public void setTrace( JiBaseTrace trace );
	public boolean add( JiBaseRelation relation );
	
    public int count();	
	
    public void generate(int relationCount);
    public void randomize();	
    
    public JiBaseRelation get(int index); 
  
}
