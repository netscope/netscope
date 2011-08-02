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

   //路径+数据库名+用法
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
		public JxBaseNode node;
	}
	
	public class JxBaseRelationTraceRecord
	{
		public int time;
		public JxBaseRelation relation;
	}

	public class JxBaseTraceNodeCollection{
		JxBaseTraceLoader m_owner = null;
		JxBaseTraceNodeCollection(JxBaseTraceLoader owner) {m_owner = owner;};
		public ArrayList<JxBaseTraceNode> loadmeta()
		{
			
		}
		public ArrayList<JxBaseTraceNodeRecord> loadtrace()
		{
			
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
			m_connection.close();
	}

	JxBaseNodeCollection nodes()
	{
		return m_nodes;
	}
	
	JxBaseNodeCollection relations()
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
	
	public ArrayList<JxBaseRelationTraceRecord> loadRelationTracee( int begintime, int endtime )
	{
		
	}
*/	
	void loadSnapShot( int time, NODE|RELATIONS )
	{
		
	}
	
	/** Returns an standard ResultSet object associate with an SQL SELECT clause.
	 * @param sql An SQL SELECT clause.
	 * @return
	 */
	public ResultSet select( String cmd )
	{
		Statement sta = m_connection.createStatement();
		return sta.execute(cmd);
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
	
	public ArrayList<JxBaseNode> loadnat( int time )
	{
		return null;
	}
	
	public ArrayList<JxBaseRelation> loadrat( int time )
	{
		return null;
	}
	
	public ArrayList<JxBaseNode> select( int begintime, int endtime, int filter )
	{
		return null;
	}	
*/
}
