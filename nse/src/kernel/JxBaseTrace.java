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
	public JxBaseTrace(Object owner) 
	{
		m_owner = owner;
		m_datadir = "c:/temp/";
		m_curdbname = "";
	}
	public JxBaseTrace(Object owner, String datadir)
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
	    JxBaseNode currentNode=(JxBaseNode)node;
	 	String nodeId=Integer.toString(currentNode.getId());
	  	String loc_x=Integer.toString(currentNode.getX());
	  	String loc_y=Integer.toString(currentNode.getY());
		
	    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y) " +
	    		"values ("+nodeId+","+loc_x+","+loc_y+")";
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
	    Iterator<JiBaseNode> itNodes=nodes.iterator();
	   
	    while(itNodes.hasNext())
	    {
	    	String nodeId=Integer.toString(itNodes.next().getId());
		  	String loc_x=Integer.toString(itNodes.next().getX());
		  	String loc_y=Integer.toString(itNodes.next().getY());	
	    
		    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y) " +
	    		              "values ("+nodeId+","+loc_x+","+loc_y+")";
	               
		    
		           try{
	                     sta.executeUpdate(traceNode);
	                  } 
		               catch(Exception e)
	                   {
	    	             e.printStackTrace();
	                   }
	    }    
	}
	/**
	 * Save the collection of all nodes into database.
	 *
	 * @param nodes The collection of all nodes.
	 */
	public void save( JiBaseNodeCollection nodes ) 
	{
		JxBaseNodeCollection nodeSet=(JxBaseNodeCollection)nodes;
	    Iterator<JiBaseNode> itNodes=nodeSet.iterator();
	    
	    while(itNodes.hasNext())
	    {
	    	String nodeId=Integer.toString(itNodes.next().getId());
		  	String loc_x=Integer.toString(itNodes.next().getX());
		  	String loc_y=Integer.toString(itNodes.next().getY());	
	    
		  	
		    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y) " +
	    		              "values ("+nodeId+","+loc_x+","+loc_y+")";
	                   
		           try{
	                     sta.executeUpdate(traceNode);
	                  } 
		               catch(Exception e)
	                   {
	    	             e.printStackTrace();
	                   }
	    }  
	}
		
	/**
	 * Save a single relation object into the database.
	 */
	public void save( JiBaseRelation relation )
	{
	    JxBaseRelation currentRelation=(JxBaseRelation)relation;
	    
	 	String relationId=Integer.toString(currentRelation.getId());
	  	String nodeFrom=Integer.toString(currentRelation.getNodeFrom().getId());
	  	String nodeTo=Integer.toString(currentRelation.getNodeTo().getId());
	  	
	    String saveRelation="insert into "+m_relationDataName+" (id,nodefrom,nodeto)" +
	    		         " values ("+relationId+","+nodeFrom+","+nodeTo+")";
	        try{
	             sta.executeUpdate(saveRelation);
	        }catch(Exception e)
	         {
	    	     e.printStackTrace();
	         }
	}
	/** save relations */
	public void save( JxBaseRelationCollection relations )
	{
		 Iterator<JiBaseRelation> itRelations=relations.iterator();
		
		 while(itRelations.hasNext())
		    {
		    	String relationId=Integer.toString(itRelations.next().getId());
			  	String nodeFrom=Integer.toString(itRelations.next().getNodeFrom().getId());
			  	String nodeTo=Integer.toString(itRelations.next().getNodeTo().getId());	
		    
			    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y) " +
		    		              "values ("+relationId+","+ nodeFrom+","+ nodeTo+")";
		               
			    
			           try{
		                     sta.executeUpdate(traceNode);
		                  } 
			               catch(Exception e)
		                   {
		    	             e.printStackTrace();
		                   }
		    }
	}
	/**
	 * Save the relation collection object into the database.
	 * @param relations
	 */
	public void save( JiBaseRelationCollection relations )
	{
         JxBaseRelationCollection currentRelation =(JxBaseRelationCollection)relations; 
		 Iterator<JiBaseRelation> itRelations=currentRelation.iterator();
		
		    while(itRelations.hasNext())
		    {
		    	String relationId=Integer.toString(itRelations.next().getId());
			  	String nodeFrom=Integer.toString(itRelations.next().getNodeFrom().getId());
			  	String nodeTo=Integer.toString(itRelations.next().getNodeTo().getId());	
		    
			    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y) " +
		    		              "values ("+relationId+","+ nodeFrom+","+ nodeTo+")";
		               
			    
			      try{
		                sta.executeUpdate(traceNode);
		             } 
			         catch(Exception e)
		             {
		    	       e.printStackTrace();
		             }
		    }
	}
	
	/** 
	 * Load meta nodes data from database into an JiBaseNodeCollection object  
	 * 
	 * @param nodes An JiBaseNodeCollection object containing the nodes loaded.
	 */
	
	public void load(int time,JiBaseNodeCollection nodes)
	{
	  try{
		     String select_node = "select * from " + m_nodeMetaName;
	         ResultSet r = sta.executeQuery(select_node);

	         while (r.next()) 
	         {
		         int i = 0;
		         JxBaseNode node = (JxBaseNode)nodes.get(i++);

		         int nodeId   =  Integer.parseInt(r.getString(1));
		         int nodeLocx =  Integer.parseInt(r.getString(2));
		         int nodeLocy =  Integer.parseInt(r.getString(3));

		         node.setId(nodeId);
		         node.setX(nodeLocx);
		         node.setY(nodeLocy);
	         }
	     }catch(Exception e)
	      {
	    	 e.printStackTrace();
	      }
    }

	     
	public void load(int time,JiBaseRelationCollection relations)
	{
		try {
			  String select_edge = "select * from " + m_relationDataName;
			  ResultSet r = sta.executeQuery(select_edge);

			 while (r.next()) {

				int i = 0;
				JiBaseRelation relation = m_relations.get(i);

				int relationId = Integer.parseInt(r.getString(1));
				int nodeFromId = Integer.parseInt(r.getString(2));
				int nodeToId = Integer.parseInt(r.getString(3));

				JiBaseNode nodeFrom=m_nodes.search(nodeFromId);
				JiBaseNode nodeTo=m_nodes.search(nodeToId);
				
				relation.setId(relationId);
				relation.setNodeFrom(nodeFrom);
				relation.setNodeTo(nodeTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
	    String traceNode="insert into "+m_relationDataName+" (time,nodeid,length) values " +
	    		"("+currentTime+","+relationId+","+packet+")";
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
    public void restore(int time, String datadir, JiBaseNodeCollection nodes, JiBaseRelationCollection relations ) 
	{		
		open( datadir );
		load( time,nodes );
	    load( time,relations );
	}

	public String getNextDatabaseDir() 
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd_hhmmss");// 设置日期格式(数据表格式有要求)
		String cur_time = sdf.format(date);
		return cur_time;
	}

}