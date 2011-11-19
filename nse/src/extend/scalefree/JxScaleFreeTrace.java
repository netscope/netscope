package extend.scalefree;

import java.util.Iterator;
import java.sql.*;
import kernel.*;

public class JxScaleFreeTrace extends JxBaseTrace 
{
    Statement  m_sta;
    
    String m_nodeMetaName = super.getNodeMetaName();
    String m_nodeDataName = super.getNodeDataName();
    
	String m_relationMetaName = super.getRelationMetaName();
	String m_relationDataName = super.getRelationDataName();
   
	
	public JxScaleFreeTrace()
	{
		super();
	}
	
	public JxScaleFreeTrace(Object owner)
	{
		super(owner);
	}
	
	public JxScaleFreeTrace(Object owner, String datadir)
	{
		super(owner,datadir);
	}

	
	public void save( JiBaseNode node )
	{
	    JxBaseNode currentNode=(JxBaseNode)node;
	    
	 	String nodeId=Integer.toString(currentNode.getId());
	  	
	 	String loc_x=Integer.toString(currentNode.getX());
	  	String loc_y=Integer.toString(currentNode.getY());
	   // String loc_z=Integer.toString(currentNode.getZ());
	  	
	   //String length=Integer.toString(currentNode.getLength());
	  	String capacity=Integer.toString(currentNode.getCapacity());
	  	
	  //String stat_degreein=Integer.toString(currentNode.getDegreeIn());
	  //String stat_degreeout=Integer.toString(currentNode.getDegreeOut());
	  	
	  //String stat_totaltraffic=Integer.toString(currentNode.getTotalTraffic());
	  //String stat_totallost=Integer.toString(currentNode.getTotalLost());
	  	
	    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y," +
	    		"capacity)" +"values ("+nodeId+","+loc_x+","+loc_y+","+capacity+",)";
	    try{
	         m_sta.executeUpdate(traceNode);
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
	    	JiBaseNode currentNode= itNodes.next();
	    	String nodeId=Integer.toString(currentNode.getId());
		
	    	String loc_x=Integer.toString(currentNode.getX());
		  	String loc_y=Integer.toString(currentNode.getY());	
		   //	String loc_z=Integer.toString(currentNode.getZ());
		  	
		  //    String length=Integer.toString(currentNode.getLength());
		  	
		  	String capacity=Integer.toString(currentNode.getCapacity());
		  	
		  //	String stat_degreein=Integer.toString(currentNode.getDegreeIn());
		  //	String stat_degreeout=Integer.toString(currentNode.getDegreeOut());
		  	
		  //	String stat_totaltraffic=Integer.toString(currentNode.getTotalTraffic());
		 // 	String stat_totallost=Integer.toString(currentNode.getTotalLost());
		  	
		    String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y,length,capacity)" 
		    		+"values ("+nodeId+","+loc_x+","+loc_y+","+capacity+")";       
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
		  	//String loc_z=Integer.toString(currentNode.getZ());
		  	
		  	//String length=Integer.toString(currentNode.getLength());
		  	String capacity=Integer.toString(currentNode.getCapacity());
		  	
		  	//String stat_degreein=Integer.toString(currentNode.getDegreeIn());
		  	//String stat_degreeout=Integer.toString(currentNode.getDegreeOut());
		  	
		  	//String stat_totaltraffic=Integer.toString(currentNode.getTotalTraffic());
		  	//String stat_totallost=Integer.toString(currentNode.getTotalLost());
		  	
		  	String traceNode="insert into "+m_nodeMetaName+" (nodeid,loc_x,loc_y,length,capacity)" 
		    		+"values ("+nodeId+","+loc_x+","+loc_y+","+capacity+")";       
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
	 	
	 	//String begintime=Integer.toString(currentRelation.getBeginTime());
	 	//String endtime=Integer.toString(currentRelation.getEndtime());
	 	
	 	String bandwidth=Integer.toString(currentRelation.getBandwidth());
	 	
	 	//String stat_totaltraffic=Integer.toString(currentRelation.getTotaltraffic());
	 	//String stat_totallost=Integer.toString(currentRelation.getTotallost());
	  	
	 	String nodeFrom=Integer.toString(currentRelation.getNodeFrom().getId());
	  	String nodeTo=Integer.toString(currentRelation.getNodeTo().getId());
	  	
	    String saveRelation="insert into "+m_relationMetaName+"(relationid,reltype,nodefrom,nodeto,"+
	    		"bandwidth)"+" values ("+relationId+","+reltype+","+nodeFrom+","+nodeTo+","+bandwidth+")";
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
			 	
			 	//String begintime=Integer.toString(currentRelation.getBeginTime());
			 	//String endtime=Integer.toString(currentRelation.getEndtime());
			 	
			    String nodeFrom=Integer.toString(currentRelation.getNodeFrom().getId());
			  	String nodeTo=Integer.toString(currentRelation.getNodeTo().getId());	
		    
			 	
			 	String bandwidth=Integer.toString(currentRelation.getBandwidth());
			 	
			 	//String stat_totaltraffic=Integer.toString(currentRelation.getTotaltraffic());
			 	//String stat_totallost=Integer.toString(currentRelation.getTotallost());
			 	
			 	String saveRelation="insert into "+m_relationMetaName+"(relationid,reltype,nodefrom,nodeto,"+
			    		"bandwidth)"+" values ("+relationId+","+reltype+","+nodeFrom+","+nodeTo+","+bandwidth+")";
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
			  	
			 	//String begintime=Integer.toString(currentRelation.getBeginTime());
			 	//String endtime=Integer.toString(currentRelation.getEndtime());
			 	
		    	String nodeFrom=Integer.toString(currentRelation.getNodeFrom().getId());
			  	String nodeTo=Integer.toString(currentRelation.getNodeTo().getId());	
		      
			 	String bandwidth=Integer.toString(currentRelation.getBandwidth());
			 	
			 	//String stat_totaltraffic=Integer.toString(currentRelation.getTotaltraffic());
			 	//String stat_totallost=Integer.toString(currentRelation.getTotallost());
			  	
			 	String traceRelation="insert into "+m_relationMetaName+"(relationid,reltype,nodefrom,nodeto,"+
			    		"bandwidth)"+" values ("+relationId+","+reltype+","+nodeFrom+","+nodeTo+","+bandwidth+")";
			      try{
		                m_sta.executeUpdate(traceRelation);
		             } 
			         catch(Exception e)
		             {
		    	       e.printStackTrace();
		             }
		    } 
	}
}

