package kernel.basetrace;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import kernel.*;


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
 * 	3 Create an JxBaseTraceLoader object in MATLAB. Assume this object name is "trace";
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
public class JxBaseTraceLoader 
{
	protected JxBaseTraceMetaSet m_metaset = new JxBaseTraceMetaSet();  
	protected JxBaseTraceDataSet m_dataset = new JxBaseTraceDataSet();
	//protected JxBaseTrace trace=new JxBaseTrace(); 		
	
	protected Connection m_connection = null;
	protected Statement m_statement = null;
		
	protected String m_datadir = "d:/data/netscope/";
	
	@SuppressWarnings("static-access")
	protected String m_tablename=null; 
	
	/**
	 * Open an trace data set for reading. 
	 * 
	 * @param datadir
	 * @param dbname
	 */
	
    public Statement opendatabase(String databasedir,String databasename)
	{
	  try {
			 Class.forName("org.hsqldb.jdbcDriver");
			 
			 m_connection = DriverManager.getConnection("jdbc:hsqldb:file:"+databasedir+ databasename + ";shutdown=true", "sa", "");
			 m_statement= m_connection.createStatement();
	      } 
	      catch (Exception e) 
	      {
			e.printStackTrace();
	      }
	      return  m_statement;
	}
    
	public void open()
	{
		  m_datadir="D:/temp/exper/";
		  opendatabase(m_datadir,m_tablename);
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
		     e.printStackTrace();
		   }
	}
	
	public void loadnodes()
	{
	   m_statement = JxBaseTrace.getStatement();
	   m_tablename = JxBaseTrace.getName();
	   
	   m_metaset.loadnodes(m_statement,m_tablename); 	 		
	}
	
	public void loadRelations()
	{
	   m_metaset.loadrelations(m_statement,m_tablename);
	}
  
	public JxBaseTraceMetaSet metaSet()
	{
		return m_metaset;
	}
	
	public JxBaseTraceDataSet dataSet()
	{
		return m_dataset;
	}
	
	public String DatabaseDir() 
	{
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String cur_time = sdf.format(date);
		return cur_time;
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
