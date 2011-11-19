package kernel.basetrace;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The JxBaseTraceLoader class is used for other modules to load data from traced files.
 * Hierarchical architecture:
 * 
 *  	Applicaton: MATLAB Based
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
 */
public class JxBaseTraceLoader 
{
	protected JxBaseTraceMetaSet m_metaset = new JxBaseTraceMetaSet();  
	protected JxBaseTraceDataSet m_dataset = new JxBaseTraceDataSet();		
	
	protected Connection m_connection = null;
	protected Statement  m_statement = null;
		
	protected String m_datadir = "D:/temp/exper/";
	protected String m_tableName = "20110930_112113"; 
		
	/**
	 * Open an trace data set for reading. 
	 * 
	 * @param datadir
	 * @param dbname
	 */
	
    public void opendatabase(String databasedir,String databasename)
	{
	  try {
			 Class.forName("org.hsqldb.jdbcDriver");
			 m_connection = DriverManager.getConnection("jdbc:hsqldb:file:"+databasedir+ databasename + ";shutdown=true", "sa", "");
			 m_statement = m_connection.createStatement();
	      } 
	      catch (Exception e) 
	      {
			e.printStackTrace();
	      }
	}
    
	public void open()
	{
       opendatabase(m_datadir,m_tableName);
	}
    
	/**
	 * Close an trace data set opened before.
	 */
	public void close()
	{ 
	    try{
		     if (m_connection != null)	
			 m_connection.close();
		
		     if (m_statement!=null)
		     m_statement.close();	
		   }catch(Exception e)
		    {	
		       e.printStackTrace();
		    }
	}
	
	public int[][] loadMetaNodes()
	{   
	   int [][] metaNodes = new int[10000][3];
	   int [][] metaNodes2= new int[10][3];
	   metaNodes = m_metaset.loadnodes(m_statement,m_tableName); 	 	
	   
	   for(int i=0;i<10;i++)
	   {
		  for(int j=0;j<3;j++) 
		  {
			metaNodes2[i][j]=metaNodes[i][j]; 
		  }
	   }
	   return metaNodes2;
	}
	
	public void loadDataNodes()
	{	
       int beginTime = 1;
       int endTime = 2;
       m_dataset.loadNode(m_statement,m_tableName, beginTime, endTime);
	}
	
	public void loadDataRelations()
	{
		int beginTime = 1;
		int endTime = 2;
		m_dataset.loadRelation(m_statement, m_tableName, beginTime, endTime);
	}
	
	public int[][] loadMetaRelations()
	{
	   int [][]metaRelations=new int[9999][3];
	   metaRelations=m_metaset.loadrelations(m_statement,m_tableName);
	   return metaRelations;
	}
 
	public void loadNodeSnapShot()
	{
		int givenTime = 5;
		m_dataset.loadNodeSnapShot(m_statement,m_tableName,givenTime);
	}
	
	public void loadRelationSnapShot()
	{
		int givenTime = 5;
		m_dataset.loadRelationSnapShot(m_statement,m_tableName,givenTime);
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
		return  r;
    }
	
	public static void main(String args[])
	{
		JxBaseTraceLoader loader=new JxBaseTraceLoader();
		
		loader.open();
	    loader.loadMetaNodes();
		loader.loadMetaRelations();
	    loader.close();
	}
}
