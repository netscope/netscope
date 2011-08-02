package kernel;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The most fundamental implementation of Trace object.
 */
public class JxBaseTrace implements JiBaseTrace {
    
	private Connection con = null;
	
	private Statement sta = null;
	
	/** Owner of this object. It's usually the simulation engine object */
	private Object m_owner = null;
	
	/** Where the trace data files are placed */
	private String m_datadir = null;
	
	/** Current database name */ 
	private String m_curdbname = null;
	JxBaseNodeCollection  nodes = new JxBaseNodeCollection();
	JxBaseRelationCollection relations = new JxBaseRelationCollection();
	
	
	JxBaseTrace(){};	
	JxBaseTrace(Object owner) 
	{
		m_owner = owner;
		m_datadir = "c:/temp/";
		m_curdbname = "";
	}
	JxBaseTrace(Object owner, String datadir)
	{
		m_owner = owner;
		m_datadir = datadir;
		m_curdbname = "";
	}
	
	
	public Object getOwner()
	{
		return m_owner;
	};
	public void setOwner(Object owner)
	{ 
		m_owner=owner;
	};

   /**打开数据库*/
	public void open(String datadir)
	{
	  try {
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:"
					+ datadir + ";shutdown=true", "sa", "");
			sta = con.createStatement();
	     } 
	      catch (Exception e) 
	     {
			e.printStackTrace();
			
	     }
	}
	
	public void open()
	{
	   if (m_datadir == null)
		  m_datadir = getNextDatabaseDir();
	   open(m_datadir);
	}

	/** Free resources allocated to this object. */
	public void close()
	{		
		try{
			 sta.close();
			 con.close();
		   } 
		    catch (SQLException e) 
		   {
				con = null;
		   } 		
	}
	
	/*
	 * finalize() will be called by JVM automatically. But JVM cannot guarantee
	 * when to call it. So We call system.runFinalization() in the engine to force
	 * the JVM to call finalize() of each revoked objects.
	 * 
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize()
	{
		close();
	}
	
	/**
	 * Save a single node into the database.
	 * 
	 * @param node
	 */
	public void save( JiBaseNode node )
	{
		
	}
	/** save nodes */
	public void save( JxBaseNodeCollection nodes ) 
	{
		
	}
	/**
	 * Save the collection of all nodes into database.
	 *
	 * @param nodes The collection of all nodes.
	 */
	public void save( JiBaseNodeCollection nodes ) 
	{
		/*		
		try {
			String str_time = getNextDatabaseName();
			String node_tablename = "nodetable" + str_time;
			String create_node = "create table " + node_tablename
					+ "(NODEID INTEGER,LOC_X INTEGER,LOC_Y INTEGER)";
			sta.executeUpdate(create_node); // 创建节点结构表

			for (int i = 0; i < nodeSet.count(); i++) {
				node = nodeSet.get(i);
				String node_id = Integer.toString(node.getId()); // 转换为字符串
				String loc_x = Integer.toString(node.getLocx());
				String loc_y = Integer.toString(node.getLocy());

				String insert_nodetable = "Insert into " + node_tablename
						+ "(NODEID,LOC_X,LOC_Y) VALUES (" + node_id + ","
						+ loc_x + "," + loc_y + ")";
				sta.executeUpdate(insert_nodetable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
*/		
	}
	/**
	 * Save a single relation object into the database.
	 */
	public void save( JiBaseRelation relation )
	{
		
	}
	/** save relations */
	public void save( JxBaseRelationCollection relations )
	{
		
	}
	/**
	 * Save the relation collection object into the database.
	 * @param relations
	 */
	public void save( JiBaseRelationCollection relations )
	{
	  try {
			String str_time = getNextDatabaseDir();
			String edge_tablename = "edgetable" + str_time;
			String create_edge = "create table " + edge_tablename
					+ "(EDGEID integer,NODE_FROM integer,NODE_TO INTEGER)";

			sta.executeUpdate(create_edge); // 创建边结构
			for (int i = 0; i < relations.count(); i++) {

				JiBaseRelation relation = relations.get(i);

				String relation_id = Integer.toString(relation.getId()); // 转换为字符串
				String node_from = Integer.toString(relation.getNodeFrom().getId());
				String node_to = Integer.toString(relation.getNodeTo().getId());

				String insert_edgetable = "Insert into " + edge_tablename
						+ "(EDGEID,NODE_FROM,NODE_TO) VALUES (" + relation_id
						+ "," + node_from + "," + node_to + ")";

				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/** 
	 * Load meta nodes data from database into an JiBaseNodeCollection object  
	 * 
	 * @param nodes An JiBaseNodeCollection object containing the nodes loaded.
	 */
	
	
	public void load(JiBaseNodeCollection nodes)
	{
	     	
	}
	public void load(JiBaseRelationCollection relations)
	{
	  	
	}

	/**单个节点保存*/
	public void trace( JiBaseNode node )
	{
		
	}	
	public void trace( JiBaseRelation relation )
	{
		
	}
	
	/**
	 * Take a snapshot at the whole network. All the current status of the network 
	 * such as node queue length and relation transmission traffic will be saved
	 * into trace data files.
	 * 
	 * This function is usually called when the simulation start.
	 * 
	 * Q: What's an snapshot?
	 * R: ...
	 *  
	 * @param nodes
	 * @param relations
	 */
	public void snapshot( JiBaseNodeCollection nodes, JiBaseRelationCollection relations )
	{
		
	}
	
	@Override
	/** 作用 ？？*/
	// can add lastsnapshot time, default time 0
    public void restore( String datadir, JiBaseNodeCollection nodes, JiBaseRelationCollection relations ) 
	{		
		open( datadir );
		load( nodes );
	    load( relations );
	}

	
	public String getNextDatabaseDir() 
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd_hhmmss");// 设置日期格式(数据表格式有要求)
		String cur_time = sdf.format(date);
		return cur_time;
	}

}