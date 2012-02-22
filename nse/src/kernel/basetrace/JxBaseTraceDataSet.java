package kernel.basetrace;

import kernel.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class JxBaseTraceDataSet 
{
	Object m_owner = null;
   
	public JxBaseTraceDataSet()
    {
    	m_owner = null;
    }
	
	JxBaseTraceDataSet(Object owner)
	{
	    this.m_owner = owner;	
	}
	
	/** load information of the node and relation from trace */
	int[][] loadDataNodes(Statement sta,String tableName,int beginTime,int endTime)
	{
		int[][]  nodeDataSet = null;
		try{  
	            String selectTraceNode="select * from nodedata"+tableName+
	            		" where simtime between " + beginTime + " and "+endTime;
	           
	            ResultSet r = sta.executeQuery(selectTraceNode);

	            r.last();
	            int rowCount = r.getRow();
	            r.beforeFirst();
	            
	    		int columnCount = 6;
	    				
	    		nodeDataSet = new int [rowCount][columnCount];
	            
	            for(int i=0;r.next();i++)
	            {    
	               int  time = Integer.parseInt(r.getString(1));
	               int  id = Integer.parseInt( r.getString(2));
	               int  length = Integer.parseInt(r.getString(3));
	          
	               int  trafficIn = Integer.parseInt(r.getString(4));
	               int  trafficOut = Integer.parseInt(r.getString(5));
	               int  trafficLost = Integer.parseInt(r.getString(6));
	          
			       nodeDataSet[i][0] = time;
			  
			       nodeDataSet[i][1] = id;
			       nodeDataSet[i][2] = length;
			    
			       nodeDataSet[i][3] = trafficIn;
			       nodeDataSet[i][4] = trafficOut;
			       nodeDataSet[i][5] = trafficLost;
	           }
	               r.close();
	     }catch(Exception e)
	     {
	        e.printStackTrace();
	     }
		return nodeDataSet;
	}
	
	
	int[][] loadDataRelations(Statement sta,String tableName,int beginTime,int endTime)
	{ 
		int[][] relationSet=null;
		   try{              
				 String selectTraceRelation="select * from relationdata" +tableName+
						 " where simtime between " + beginTime + " and "+endTime;
                 
				 ResultSet r = sta.executeQuery(selectTraceRelation);
              
                 r.last();
 	             int rowCount = r.getRow();
 	             r.beforeFirst();
 	             
 	             int columnCount = 4;
 	            
 	             relationSet = new int[rowCount][columnCount];
               
 	    		 for(int i=0;r.next();i++)
                 {	 
	       	       int time =Integer.parseInt(r.getString(1));
	       	       int id = Integer.parseInt(r.getString(2));
	       	       int tra_send = Integer.parseInt(r.getString(3));
	       	       int tra_lost = Integer.parseInt(r.getString(4));     	
				
	       	       relationSet[i][0] = time;
	       	       relationSet[i][1] = id;
	       	       relationSet[i][2] = tra_send;
	       	       relationSet[i][3] = tra_lost;
                 }
 	    		 r.close();
            }catch(Exception e)
             {
	           e.printStackTrace();
             }
         return relationSet;
	}
	
	
	/** Load the snapshot of the network at the given time*/
	int[][]  loadNodeSnapShot(Statement sta,String tableName,int givenTime)
	{ 
		  int[][]  nodeDataSet =null;
	    
		  try{
               String selectNodeSnapShot = "select * from nodedata"+tableName+" where simtime = "+givenTime;
             
               ResultSet r = sta.executeQuery(selectNodeSnapShot);
            
	         //  r.last();
	         //  int rowCount = r.getRow();
	         //  r.beforeFirst();
              
               int columnCount = 6;
             
               nodeDataSet =new int [1024][columnCount];
              
               for(int i =0;r.next();i++)
               { 
                  int  id = Integer.parseInt(r.getString(2));
                  int  length = Integer.parseInt(r.getString(3));
         
                  int  trafficIn = Integer.parseInt(r.getString(4));
                  int  trafficOut = Integer.parseInt(r.getString(5));
                  int  trafficLost = Integer.parseInt(r.getString(6));
         
		          nodeDataSet[i][0] = givenTime;
		  
		          nodeDataSet[i][1] = id;
		          nodeDataSet[i][2] = length;
		    
		          nodeDataSet[i][3] = trafficIn;
		          nodeDataSet[i][4] = trafficOut;
		          nodeDataSet[i][5] = trafficLost;
               }
               r.close();
      }catch(Exception e)
       {
	       e.printStackTrace();
       }
       return nodeDataSet;
   }
	
	
	int[][] loadRelationSnapShot(Statement sta,String tablename,int givenTime)
	{
		int[][]  relationDataSet =null;
		
	    try{
	         String selectRelationSnapShot=" select * from relationdata"+tablename+" where simtime= "+givenTime;
	         ResultSet r = sta.executeQuery(selectRelationSnapShot);

	         r.last();
	         int rowCount = r.getRow();
	         r.beforeFirst();
	         
	         int columnCount=4;
	         relationDataSet = new int [rowCount][columnCount];
	        
	         for(int i =0;r.next();i++)
	         { 
     	       relationDataSet[i][0] = givenTime;
     	       relationDataSet[i][1] = Integer.parseInt(r.getString(2)); //id
     	       relationDataSet[i][2] = Integer.parseInt(r.getString(3)); //traffic send
     	       relationDataSet[i][3] = Integer.parseInt(r.getString(4)); //traffic lost
	         }
	         r.close();
         }catch(Exception e)
          {
   	         e.printStackTrace();
          }
       return relationDataSet;
	}
	/** load information of the node and relation from trace */
	int[][] loadDataNodes1(Statement sta,String tableName) //get the last experiment time queue length of node
	{
		int[][]  nodeDataSet = null;
		
		int nodeCount=10000;
		int[][] nodeLength=new int[nodeCount][2]; // the first dimension is queue length
		                                          // the second dimension is a binary number,1 means that the node is
	                                              // the node is already exist,0 means not
		try{  
	            String selectTraceNode="select * from nodedata"+tableName;
	           
	            ResultSet r = sta.executeQuery(selectTraceNode);

	            r.last();
	            int rowCount = r.getRow();
	            r.beforeFirst();
	            
	    		int columnCount = 6;
	    				
	    		nodeDataSet = new int [rowCount][columnCount];
	            
	            for(int i=0;r.next();i++)
	            {          
			       nodeDataSet[i][0] =  Integer.parseInt(r.getString(1)); //Time
			       nodeDataSet[i][1] =  Integer.parseInt(r.getString(2));//Id
	          
			       nodeDataSet[i][2] = Integer.parseInt(r.getString(3));//Length
			    
			       nodeDataSet[i][3] = Integer.parseInt(r.getString(4));//traffic In
			       nodeDataSet[i][4] = Integer.parseInt(r.getString(5));// trafficOut 
			       nodeDataSet[i][5] = Integer.parseInt(r.getString(6));//trafficLost 
			       
			    // System.out.println(nodeDataSet[i][0]+","+ nodeDataSet[i][1]+","+nodeDataSet[i][2]+
			    // ","+nodeDataSet[i][3]+","+nodeDataSet[i][4]+","+nodeDataSet[i][5]);
	            }
	           for(int j=rowCount-1;j>=0;j--) //get the length of the node
	           {
	        	  int nodeId = nodeDataSet[j][1];
	        	  
	              if(nodeLength[nodeId][1]==0)
	              {
	            	nodeLength[nodeId][0]=nodeDataSet[j][2];
	            	nodeLength[nodeId][1]=1;
	              }
	           }
	           r.close();
	     }catch(Exception e)
	     {
	        e.printStackTrace();
	     }
		return nodeLength;
	}
} 
