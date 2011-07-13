package kernel;

public interface JiTrace {
  
	public void openDatabase();
	public void closeDatabase();
	
	public void saveNode();
	public void saveEdge();
	
	public void traceNode(int time);
	public void traceEdge(int time);
	
	public void loadNode( String tablename);
    public void loadEdge( String tablename);
} 
