package kernel;
public interface JiTrace {
  
	public void saveNode();
	public void saveEdge();
	
	public void traceNode();
	public void traceEdge();
	
	public void loadNode();
    public void loadEdge();
} 
