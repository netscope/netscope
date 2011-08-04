package kernel.basetrace;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;


import kernel.JiBaseNodeCollection;
import kernel.JiBaseRelationCollection;
import kernel.JiBaseRelation;
import kernel.JxBaseNode;
import kernel.JxBaseNodeCollection;
import kernel.JiBaseRelation;
import kernel.JxBaseRelationCollection;

/**
 * The JxBaseTraceLoader class is used for other modules to load data from traced files.
 *  
 * Hierarchical architecture:
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

	protected String m_datadir = "d:/data/netscope/";
	protected String m_dbname = null;
	
	Connection m_connection = null;
	Statement m_statement=null;

	JxBaseTraceMetaSet m_metaset;  
	JxBaseTraceDataSet m_dataset;
	
	JxBaseTraceLoader()
	{
		m_metaset = new JxBaseTraceMetaSet(this);
		m_dataset = new JxBaseTraceDataSet(this);
	}
	
	/**
	 * Open an trace data set for reading. 
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
			m_statement=m_connection.createStatement();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			m_connection = null;
		}
	}
	
	/**
	 * Close an trace data set opened before.
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

	JxBaseTraceMetaSet metaset()
	{
		return m_metaset;
	}
	
	JxBaseTraceDataSet dataset()
	{
		return m_dataset;
	}
	
	/** Returns an standard ResultSet object associate with an SQL SELECT clause.
	 * @param sql An SQL SELECT clause.
	 * @return
	 */
	public ResultSet select( String cmd )
	{
	  ResultSet r=null;
	  try{
		    Statement sta = m_connection.createStatement();
		    r=sta.executeQuery(cmd);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }	
		return  r ;
	}
}
