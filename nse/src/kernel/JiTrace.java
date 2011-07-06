package kernel;

import java.sql.Connection;
import java.sql.Statement;

import extend.scalefree.JxScaleFreeEdgeCollection;
import extend.scalefree.JxScaleFreeNodeCollection;

public interface JiTrace {
  
	public void saveNode(Statement sta);
	public void saveEdge(Statement sta);
	
	public void traceNode(Statement sta,int time);
	public void traceEdge(Statement sta,int time);
	
	public void loadNode(Statement sta, String tablename);
    public void loadEdge(Statement sta, String tablename);
} 
