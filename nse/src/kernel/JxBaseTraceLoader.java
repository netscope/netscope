package kernel;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The JxBaseTraceLoader class is used for other modules to load data from traced files.
 *  
 * Hierchical architecture:
 * 
 *  	App: MATLAB Based
 *  	Middle: Trace Loader
 *  	Low: Database
 *  
 * Q: How to use a JxBaseTraceLoader?
 * R: 
 * 	1 Start MATLAB;
 * 	2 import the jar file including JxBaseTraceLoader;
 * 	3 Create an JxBaseTraceLoader object in matlab. Assume this object name is "trace";
 *  4 Load data from trace database
 *  
 *  	trace.open( database );
 *  	nodes = trace.loadnodes()
 *  	relations = trace.loadrelations();
 *  	node_records = trace.selectnode( t1, t2 );
 *   	relation_records = trace.selectrelation( t1, t2 );
 *  	snapshot = trace.loadsnapshot(t);
 *  
 *  5 Analyze above data 
 *  6 Output report 
 *  
 * @author Allen
 *
 */
public class JxBaseTraceLoader {

	public class JxBaseTraceNode extends JxBaseNode {};
	public class JxBaseTraceRelation extends JxBaseRelation {};
	
	
	public class JxBaseTraceNodeRecord
	{
		public int time;
		public int quelength;
		public JxBaseNode node;
	}
	
	public class JxBaseRelationTraceRecord
	{
		public int time;
		public int queLength; 
		public JxBaseRelation relation;
	}
    
	public class JxBaseTraceNodeCollection
	{
		
		JxBaseTraceLoader m_owner = null;
		
		JxBaseTraceNodeCollection(JxBaseTraceLoader owner)
		{
			m_owner = owner;
		};
		
		public ArrayList<JxBaseTraceNode> loadmeta()
		{
			return null;
		}
		
		public ArrayList<JxBaseTraceNodeRecord> loadtrace()
		{
			return null;
		}
	}
	
	protected String m_datadir = "d:/data/netscope/";
	protected String m_dbname = null;
	java.sql.Connection m_connection = null;
	
	JxBaseNodeCollection m_nodes;
	JxBaseRelationCollection m_relations;
	//protected JxNodeTrace m_nodetrace;
	
	JxBaseTraceLoader()
	{
		m_nodes = new JxBaseNodeCollection(this);
		m_relations = new JxBaseRelationCollection(this);
	}
	
	/**
	 * Open an trace dataset for reading. 
	 * 
	 * @param datadir
	 * @param dbname
	 */
	void open(String datadir, String dbname) 
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			m_connection = DriverManager.getConnection("jdbc:hsqldb:file:" + datadir
					+ ";shutdown=true", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			m_connection = null;
		}
	}
	
	/**
	 * Close an trace dataset opened before.
	 */
	void close()
	{
		if (m_connection != null)	
	   
		try{
			 m_connection.close();
		   }
		    catch(Exception e)
		   {
		    	
		   }
	}

	JxBaseNodeCollection nodes()
	{
		return m_nodes;
	}
	
	JxBaseRelationCollection relations()
	{
		return m_relations;
	}
/*	
	public ArrayList<JxBaseNode> loadNodeMeta( )
	{
		return null;		
	}
	
	public ArrayList<JxBaseRelation> loadRelationMeta()
	{
		return null;
	}
	
	public ArrayList<JxBaseNodeTraceRecord> loadNodeTrace( int begintime, int endtime )
	{
		
	}
	
	public ArrayList<JxBaseRelationTraceRecord> loadRelationTrace( int begintime, int endtime )
	{
		
	}
*/	
	void loadSnapShot( int time, JiBaseNode Node )
	{
		
	}
	

	void loadSnapShot( int time, JiBaseRelation Relation )
	{
		
	}
	
	/** Returns an standard ResultSet object associate with an SQL SELECT clause.
	 * @param sql An SQL SELECT clause.
	 * @returndd
	 */
	public ResultSet select( String cmd )
	{
	  ResultSet r=null;
	  try{
		    Statement sta = m_connection.createStatement();
		    r=sta.executeQuery(cmd);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
		
		return  r ;
	}

	
	/** 
	 * Load meta nodes data from database into an JiBaseNodeCollection object  
	 * 
	 * @param nodes An JiBaseNodeCollection object containing the nodes loaded.
	 */
	public void loadmeta( JiBaseNodeCollection nodes )
	{
		
	}
	
	public void loadmeta( JiBaseRelationCollection relations )
	{
		
	}
/*
	public ArrayList<JxNodeTraceRecord> loadntrace( int begintime, int endtime )
	{
		return null;
	}
	
	public ArrayList<JxRelationTraceRecord> loadrtrace( int begintime, int endtime )
	{	
		return null;
	}
	/**load the node topology information at a experiment */
	public ArrayList<JxBaseNode> loadnat( int time )
	{
		return null;
	}
    /**load the relation topology information at a experiment*/
	public ArrayList<JxBaseRelation> loadrat( int time )
	{
		return null;
	}
	
	public ArrayList<JxBaseNode> select( int begintime, int endtime, int filter )
	{
		return null;
	}	

}
