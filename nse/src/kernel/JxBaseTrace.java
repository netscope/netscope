package kernel;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

/**
 * The most fundamental implementation of Trace object.
 */
public class JxBaseTrace implements JiBaseTrace {
    
	private Connection m_con = null;	
	private Statement m_sta = null;
	
	/** Owner of this object. It's usually the simulation engine object */
	private Object m_owner = null;
	
	/** Where the trace data files are placed */
	private String m_datadir = null;
	
	private static String m_curdbname = null;

	private String m_nodeMetaName = null;
	private String m_relationMetaName = null;
	private String m_nodeDataName = null;
	private String m_relationDataName = null;
	
	JxBaseNodeCollection  m_nodes = new JxBaseNodeCollection();
	JxBaseRelationCollection m_relations = new JxBaseRelationCollection();	
	 
	int m_relationType;
	public JxBaseTrace(){}
	
	public JxBaseTrace(Object owner) 
	{
		m_owner = owner;
		m_datadir ="c:/temp/";
		m_curdbname ="";
	}
	
	public JxBaseTrace(Object owner, String datadir)
	{
		m_owner = owner;
		m_datadir = datadir;
		m_curdbname ="";
	}
	
	public Object getOwner()
	{
		return m_owner;
	}
	
	public void setOwner(Object owner)
	{ 
		m_owner=owner;
	}
  
	public void open(String databasedir,String databasename)
	{
	  try{
		    Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		    
		    Properties props=new Properties(); 
		    props.put("user","user1");
		    props.put("password","user1");
		    
			m_con = DriverManager.getConnection("jdbc:derby:"+databasedir+ databasename 
					+ ";create=true",props);
			
			m_con.setAutoCommit(false);
			
			m_sta = m_con.createStatement();	
	     } 
	      catch (Exception e) 
	      {
			e.printStackTrace();
	      }
	}
	
	public void open()
	{
		  m_datadir="D:/temp/derbydata/";
		  m_curdbname = getCurrentTime();
	     
		  open( m_datadir,m_curdbname );
	      
	      this.nodeMetaTable( m_curdbname );
		  this.relationMetaTable( m_curdbname );  
		  this.nodeDataTable( m_curdbname );
		  this.relationDataTable( m_curdbname );
	}
	
	/** Free resources allocated to this object. */
	public void close()
	{		
	  try{	
		   m_sta.close();
		   m_con.close();
		 } 
		   catch (SQLException e) 
		 {
		   m_sta = null;
	       m_con = null;
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
	
	public void nodeMetaTable(String tablename)
	{
		m_nodeMetaName = "nodemeta"+ tablename; 
		
		String createNode = "create table " + m_nodeMetaName
				+ " (nodeid int not null primary key,loc_x int default 0,loc_y int default 0" +
				",loc_z int default 0,length int default 0,capacity int default 0" +
				",stat_degreein int default 0,stat_degreeout int default 0,stat_totaltraffic int default 0" +
				",stat_totallost int default 0)";
		System.out.println(m_nodeMetaName);
		try{
		    m_sta.executeUpdate(createNode); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void relationMetaTable(String tablename)
	{
		m_relationMetaName = "relationmeta"+ tablename;
		System.out.println(m_relationMetaName);
		
		String createRelation = "create table " + m_relationMetaName
				+ " (relationid int not null primary key,reltype smallint default 0, begintime int default 0," +
				"endtime int default 0,nodefrom int  default 0,nodeto int default 0" +
				",bandwidth int default 0,stat_totaltraffic int default 0,stat_totallost int default 0)";
		try{
		    m_sta.executeUpdate(createRelation); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void nodeDataTable(String tablename)
	{
		m_nodeDataName =  "nodedata"+tablename;
		System.out.println(m_nodeDataName);
		
		String traceNode = "create table " + m_nodeDataName
				+ " (simtime int not null, nodeid int not null,quelength int default 0," +"traffic_in int default 0, traffic_out int  default 0," +
				"traffic_lost int default 0,constraint nodedataid primary key(simtime,nodeid))";
		try{
		    m_sta.executeUpdate(traceNode); 
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	public void relationDataTable(String tablename)
	{
		m_relationDataName = "relationdata"+tablename;
		System.out.println(m_relationDataName);
		
	    String traceEdge = "create table " + m_relationDataName
	    		+ " (simtime int not null, relationid int not null,traffic_send int default 0," +
				"traffic_lost int  default 0,constraint relationDataId primary key(simtime,relationid))";
	  try{
		  m_sta.executeUpdate(traceEdge);
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
	    String loc_z=Integer.toString(currentNode.getZ());
	  	
	    String length=Integer.toString(currentNode.getLength());
	  	String capacity=Integer.toString(currentNode.getCapacity());
	  	
	  	String stat_degreein=Integer.toString(currentNode.getDegreeIn());
	  	String stat_degreeout=Integer.toString(currentNode.getDegreeOut());
	  	
	  	String stat_totaltraffic=Integer.toString(currentNode.getTotalTraffic());
	  	String stat_totallost=Integer.toString(currentNode.getTotalLost());
	  	
	    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y,loc_z,length," +
	      "capacity,stat_degreein,stat_degreeout,stat_totaltraffic,stat_totallost)" +
	    		"values ("+nodeId+","+loc_x+","+loc_y+","+loc_z+","+length+","+capacity+","
	    		+stat_degreein+","+stat_degreeout+","+stat_totaltraffic+","+stat_totallost+")";
	    try{
	         m_sta.executeUpdate(traceNode);
	    }catch(Exception e)
	    {
	    	 e.printStackTrace();
	    }
	}
	/** save nodes */
	public void save(JxBaseNodeCollection nodes) 
	{
	    Iterator<JiBaseNode> itNodes=nodes.iterator();
	   
	    while(itNodes.hasNext())
	    {
	    	JiBaseNode currentNode= itNodes.next();
	    	String nodeId=Integer.toString(currentNode.getId());
		
	    	String loc_x=Integer.toString(currentNode.getX());
		  	String loc_y=Integer.toString(currentNode.getY());	
		  	String loc_z=Integer.toString(currentNode.getZ());
		  	
		  	String length=Integer.toString(currentNode.getLength());
		  	
		  	String capacity=Integer.toString(currentNode.getCapacity());
		  	
		  	String stat_degreein=Integer.toString(currentNode.getDegreeIn());
		  	String stat_degreeout=Integer.toString(currentNode.getDegreeOut());
		  	
		  	String stat_totaltraffic=Integer.toString(currentNode.getTotalTraffic());
		  	String stat_totallost=Integer.toString(currentNode.getTotalLost());
		  	
		    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y,loc_z,length," +
		    		"capacity,stat_degreein,stat_degreeout,stat_totaltraffic,stat_totallost" +") " 
		    		+"values ("+nodeId+","+loc_x+","+loc_y+","+loc_z+","+length+","+capacity+
		    		","+stat_degreein+","+stat_degreeout+","+stat_totaltraffic+","+stat_totallost+")";       
		     try{
	              m_sta.executeUpdate(traceNode);
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
	    	JiBaseNode currentNode= itNodes.next();
	    	String nodeId=Integer.toString(currentNode.getId());
		  	
	    	String loc_x=Integer.toString(currentNode.getX());
		  	String loc_y=Integer.toString(currentNode.getY());	
		  	String loc_z=Integer.toString(currentNode.getZ());
		  	
		  	String length=Integer.toString(currentNode.getLength());
		  	String capacity=Integer.toString(currentNode.getCapacity());
		  	
		  	String stat_degreein=Integer.toString(currentNode.getDegreeIn());
		  	String stat_degreeout=Integer.toString(currentNode.getDegreeOut());
		  	
		  	String stat_totaltraffic=Integer.toString(currentNode.getTotalTraffic());
		  	String stat_totallost=Integer.toString(currentNode.getTotalLost());
		  	
		    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y,loc_z,length," +
		    		"capacity,stat_degreein,stat_degreeout,stat_totaltraffic,stat_totallost" +") " 
		    		+"values ("+nodeId+","+loc_x+","+loc_y+","+loc_z+","+length+","+capacity+
		    		","+stat_degreein+","+stat_degreeout+","+stat_totaltraffic+","+stat_totallost+")";       
		     try{
	               m_sta.executeUpdate(traceNode);
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
	 	String reltype=this.relationType(currentRelation.getType());
	 	
	 	String begintime=Integer.toString(currentRelation.getBeginTime());
	 	String endtime=Integer.toString(currentRelation.getEndtime());
	 	
	 	String bandwidth=Integer.toString(currentRelation.getBandwidth());
	 	
	 	String stat_totaltraffic=Integer.toString(currentRelation.getTotaltraffic());
	 	String stat_totallost=Integer.toString(currentRelation.getTotallost());
	  	
	 	String nodeFrom=Integer.toString(currentRelation.getNodeFrom().getId());
	  	String nodeTo=Integer.toString(currentRelation.getNodeTo().getId());
	  	
	    String saveRelation="insert into "+m_relationMetaName+" (relationid,reltype,begintime,endtime,nodefrom,nodeto,"+
	    		"bandwidth,stat_totaltraffic,stat_totallost)"+" values ("+relationId+","+reltype+","+begintime+","
	    		+endtime+","+nodeFrom+","+nodeTo+","+bandwidth+","+stat_totaltraffic+","+stat_totallost+")";
	        try{
	             m_sta.executeUpdate(saveRelation);
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
			    JxBaseRelation currentRelation=(JxBaseRelation)itRelations.next();
		    	
			    String relationId=Integer.toString(currentRelation.getId());
			  
			 	String reltype=this.relationType(currentRelation.getType());
			 	
			 	String begintime=Integer.toString(currentRelation.getBeginTime());
			 	String endtime=Integer.toString(currentRelation.getEndtime());
			 	
			    String nodeFrom=Integer.toString(currentRelation.getNodeFrom().getId());
			  	String nodeTo=Integer.toString(currentRelation.getNodeTo().getId());	
		    
			 	
			 	String bandwidth=Integer.toString(currentRelation.getBandwidth());
			 	
			 	String stat_totaltraffic=Integer.toString(currentRelation.getTotaltraffic());
			 	String stat_totallost=Integer.toString(currentRelation.getTotallost());
			  	
			 	String saveRelation="insert into "+m_relationMetaName+" (relationid,reltype,begintime,endtime,nodefrom,nodeto,"+
			    		"bandwidth,stat_totaltraffic,stat_totallost)"+" values ("+relationId+","+reltype+","+begintime+","
			    		+endtime+","+nodeFrom+","+nodeTo+","+bandwidth+","+stat_totaltraffic+","+stat_totallost+")";
	             try{
                       m_sta.executeUpdate(saveRelation);
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
         JxBaseRelationCollection relationSet =(JxBaseRelationCollection)relations; 
		 Iterator<JiBaseRelation> itRelations=relationSet.iterator();
		
		    while(itRelations.hasNext())
		    {
		    	JxBaseRelation currentRelation=(JxBaseRelation)itRelations.next();
		    
		    	String relationId=Integer.toString(currentRelation.getId());
		    	
		    	String reltype=this.relationType(currentRelation.getType());
			  	
			 	String begintime=Integer.toString(currentRelation.getBeginTime());
			 	String endtime=Integer.toString(currentRelation.getEndtime());
			 	
		    	String nodeFrom=Integer.toString(currentRelation.getNodeFrom().getId());
			  	String nodeTo=Integer.toString(currentRelation.getNodeTo().getId());	
		      
			 	String bandwidth=Integer.toString(currentRelation.getBandwidth());
			 	
			 	String stat_totaltraffic=Integer.toString(currentRelation.getTotaltraffic());
			 	String stat_totallost=Integer.toString(currentRelation.getTotallost());
			  	
			    String traceRelation="insert into "+m_relationMetaName+" (relationid,reltype,begintime,endtime,nodefrom,nodeto,reltype," +
			    		",bandwidth,stat_totaltraffic,stat_totallost)" +"values ("+relationId+","+reltype+"," +
			    		begintime+","+endtime+","+nodeFrom+","+nodeTo+","+bandwidth+","+stat_totaltraffic+","+stat_totallost+")";
			      try{
		                m_sta.executeUpdate(traceRelation);
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
		     String selectNode = "select * from " + m_nodeMetaName;
	         ResultSet r = m_sta.executeQuery(selectNode);

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
		try{
			  String select_edge = "select * from " + m_relationMetaName;
			  ResultSet r = m_sta.executeQuery(select_edge);

			 while (r.next())
			 {
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
		} catch (Exception e)
		  {
			e.printStackTrace();
		  }
	}

	/** save the information of node*/
	public void trace(int time, JiBaseNode node)
	{
	  	String currentTime=Integer.toString(time);
	  	JxBaseNode currentNode=(JxBaseNode)node;
	    
	 	String nodeId=Integer.toString(currentNode.getId());
	  	String queLength=Integer.toString(currentNode.getValue());
	  	
	  	String traffic_in=Integer.toString(currentNode.getTrafficIn());
	  	String traffic_out=Integer.toString(currentNode.getTrafficOut());
	  	String traffic_lost=Integer.toString(currentNode.getTrafficLost());
	  	
		
	    String traceNode="insert into  "+m_nodeDataName+" (simtime,nodeid,quelength,traffic_in,traffic_out,traffic_lost) " +
	    		"values ("+currentTime+","+nodeId+","+queLength+"," +traffic_in+","+traffic_out+","+traffic_lost+")";
	    try{
	         m_sta.executeUpdate(traceNode);
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
		
	  	String trafficSend=Integer.toString(currentRelation.getTrafficSend());
	  	String trafficLost=Integer.toString(currentRelation.getTrafficLost());
	  	
	    String tracerelation="insert into "+m_relationDataName+" (simtime,relationId,traffic_send,traffic_lost)  values " +
	    		"("+currentTime+","+relationId+","+trafficSend+","+trafficLost+")";
	    try{
	    	
	        m_sta.executeUpdate(tracerelation);
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
	// can add lastsnapshot time, default time 06
    public void restore(int time, String datadir, JiBaseNodeCollection nodes, JiBaseRelationCollection relations ) 
	{		
		open( datadir,m_curdbname);
		load( time,nodes );
	    load( time,relations );
	}

	public String getCurrentTime() 
	{
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String cur_time = sdf.format(date);
		return cur_time;
	}
	
	public  String relationType(JxBaseRelation.JiRelationType relationType)
	{
	   switch (relationType)
	   {
	     case BI_DIRECTION_RELATION:
              m_relationType =  0;       
	     case SINGLE_DIRECTIOIN_RELATION:
	    	  m_relationType =  1;
	     case BROADCAST_RELATION:
	    	  m_relationType =  2;
	     case GROUP_RELATION:
	    	  m_relationType =  3;
	   }
	   return Integer.toString(m_relationType);
	}
	
	public static String getName()
	{
		return m_curdbname;
	}
	
	public Statement getStatement()
	{
		return m_sta;
	}
	
	public String getNodeMetaName()
	{
	   return m_nodeMetaName; 
	}
	
	public String getNodeDataName()
	{
	   return m_nodeDataName;
	}
	
	
	public String getRelationMetaName()
	{
	   return m_relationMetaName;
	}
	
	public String getRelationDataName()
	{
	   return m_relationDataName;
	}
}
