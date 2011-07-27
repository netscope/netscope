package kernel;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

   //数据库名+文件名的用法
/**
 * The JxBaseTraceLoader class is used for other modules to load data from traced files.
 *  
 * @author Allen
 *
 */
public class JxBaseTraceLoader {
	
	public class JxNodeTraceItem{
		public int time;
		public JxBaseNode node;
	}
	
	public class JxNodeTrace{
		
		JxNodeTrace( Object owner );
		public loadmeta();
		public loadtrace();
		public selectnmode();
		public selecttrace();
		
	}
	
	protected String m_datadir;
	protected String m_dbname;
	protected JxNodeTrace m_nodetrace;
	
	JxBaseTraceLoader()
	{
		
	}
	
	/**
	 * Open an trace dataset for reading. 
	 * 
	 * @param datadir
	 * @param dbname
	 */
	void open( String datadir, String dbname )
	{
		
	}
	
	/**
	 * Close an trace dataset opened before.
	 */
	void close()
	{
		
	}
	
	/** Returns an standard ResultSet object associate with an SQL SELECT clause.
	 * @param sql An SQL SELECT clause.
	 * @return
	 */
	ResultSet select( String sql )
	{
		
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

	public ArrayList<JxNodeTraceItem> loadntrace( int begintime, int endtime )
	{
		
	}
	
	public ArrayList<JxRelationTraceItem> loadrtrace( int begintime, int endtime )
	{
		
	}
	
	public ArrayList<JxBaseNode> loadnat( int time )
	{
		
	}
	
	public ArrayList<JxBaseRelation> loadrat( int time )
	{
		
	}
	
	public ArrayList<JxBaseNode> select( int begintime, int endtime, int filter )
	{
		
	}	

}
