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
	
	public String m_nodeMetaName=null;
	public String m_relationMetaName=null;
	public String m_nodeDataName=null;
	public String m_relationDataName=null;
	
	JxBaseNodeCollection  m_nodes = new JxBaseNodeCollection();
	JxBaseRelationCollection m_relations = new JxBaseRelationCollection();
	
	
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
	
	public void nodeMetaTable()
	{
		m_nodeMetaName = getNextDatabaseDir(); 
		String createNode = "create table " + m_nodeMetaName
				+ "(CUR_TIME integer, NODEID integer,LENGTH integer)";
		try{
		    sta.executeUpdate(createNode); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void relationMetaTable()
	{
		m_relationMetaName= getNextDatabaseDir();
		
		String trace_edge = "create table " + m_relationMetaName
		+"(CUR_TIME integer, EDGEID integer,PACKET integer)";
		
		try{
		    sta.executeUpdate(trace_edge); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void nodeDataTable()
	{
		m_nodeDataName =  getNextDatabaseDir();
		String trace_edge = "create table " + m_nodeDataName
				+ "(CUR_TIME integer, EDGEID integer,PACKETSUM integer)";

		try{
		    sta.executeUpdate(trace_edge); 
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	public void relationDataTable()
	{
		m_relationDataName = getNextDatabaseDir();
		
	    String trace_edge = "create table " + m_relationDataName
			+ "(CUR_TIME integer, EDGEID integer,PACKETSUM integer)";
	  try{
		  sta.executeUpdate(trace_edge);
	    }
	    catch(Exception e)
	    {
		  e.printStackTrace();
	    }	
    }
	
	/**
	 * Save a single node into the database.
	 * 
	 * @param node
	 */
	public void save( JiBaseNode node )
	{
		String currentTime=Integer.toString(time);
	    JxBaseNode currentNode=(JxBaseNode)node;
	 	String nodeId=Integer.toString(currentNode.getId());
	  	String length=Integer.toString(currentNode.getValue());
		
	    String traceNode="insert into "+m_nodeDataName+" (time,nodeid,length) " +
	    		"values ("+currentTime+","+nodeId+","+length+")";
	    try{
	         sta.executeUpdate(traceNode);
	    }catch(Exception e)
	    {
	    	 e.printStackTrace();
	    }
	}
	/** save nodes */
	public void save( JxBaseNodeCollection nodes ) 
	{
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
		String currentTime=Integer.toString(time);
	    JxBaseRelation currentRelation=(JxBaseRelation)relation;
	 	String relationId=Integer.toString(currentRelation.getId());
	  	String packet=Integer.toString(currentRelation.getPacket());
		
	    String traceNode="insert into "+m_relationDataName+" (time,nodeid,length) values ("+currentTime+","+relationId+","+packet+")";
	    try{
	    sta.executeUpdate(traceNode);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
	}
	/** save relations */
	public void save( JxBaseRelationCollection relations )
	{
		String currentTime=Integer.toString(time);
	    JxBaseRelation currentRelation=(JxBaseRelation)relation;
	 	String relationId=Integer.toString(currentRelation.getId());
	  	String packet=Integer.toString(currentRelation.getPacket());
		
	    String traceNode="insert into "+m_relationDataName+" (time,nodeid,length) values ("+currentTime+","+relationId+","+packet+")";
	    try{
	       sta.executeUpdate(traceNode);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
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

			sta.executeUpdate(create_edge);
			for (int i = 0; i < relations.count(); i++) {

				JiBaseRelation relation = relations.get(i);

				String relation_id = Integer.toString(relation.getId()); 
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
	
	
	public void load(int time,JiBaseNodeCollection nodes)
	{
	     	
	}
	public void load(int time,JiBaseRelationCollection relations)
	{
	  	
	}

	/** save the information of node*/
	public void trace(int time, JiBaseNode node)
	{
	  	String currentTime=Integer.toString(time);
	    JxBaseNode currentNode=(JxBaseNode)node;
	 	String nodeId=Integer.toString(currentNode.getId());
	  	String length=Integer.toString(currentNode.getValue());
		
	    String traceNode="insert into "+m_nodeDataName+" (time,nodeid,length) " +
	    		"values ("+currentTime+","+nodeId+","+length+")";
	    try{
	         sta.executeUpdate(traceNode);
	    }catch(Exception e)
	    {
	    	 e.printStackTrace();
	    }
	}	
	public void trace( int time,JiBaseRelation relation)
	{
	  	String currentTime=Integer.toString(time);
	    JxBaseRelation currentRelation=(JxBaseRelation)relation;
	 	String relationId=Integer.toString(currentRelation.getId());
	  	String packet=Integer.toString(currentRelation.getPacket());
		
	    String traceNode="insert into "+m_relationDataName+" (time,nodeid,length) values ("+currentTime+","+relationId+","+packet+")";
	    try{
	    sta.executeUpdate(traceNode);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
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
	public void snapshot( int time,String tableName)
	{
		Iterator<JiBaseNode> itNode=m_nodes.iterator();
		Iterator<JiBaseRelation> itRelation = m_relations.iterator();
		
	    while(itNode.hasNext())
	    {
	      JiBaseNode node=(JiBaseNode)itNode.next();	
	      this.trace(time, node);
	    }
		
		while(itRelation.hasNext())
		{
		  JiBaseRelation relation =(JiBaseRelation)itRelation.next();
		  this.trace(time, relation);
		}
	}
	
	@Override
	/** */
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