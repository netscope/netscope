package kernel.basetrace;

import kernel.*;
import java.sql.*;
import java.util.ArrayList;


public class JxBaseTraceMetaSet
{   
	public Object m_owner;
	
	public JxBaseTraceMetaSet()
	{
		m_owner=null;
	}
	
	public JxBaseTraceMetaSet(Object owner)
	{
		m_owner=owner;	
	}
	
	public int[][] loadMetaNodes(Statement sta, String tableName)
	{
	   int[][]  nodeMetaSet = null;
	     
	     try{
	            String selectMetaNode="select * from nodemeta"+tableName;
	         
	            ResultSet r = sta.executeQuery(selectMetaNode);
	            
	            r.last();
	            int rowCount = r.getRow();
	            r.beforeFirst();
	            
	    		int columnCount = 10;
	    				
	    		nodeMetaSet = new int [rowCount][columnCount];
	            
	            for(int i=0;r.next();i++)
	            { 
			       int nodeId = Integer.parseInt(r.getString(1));	
			    
				   int nodeLocx = Integer.parseInt(r.getString(2));
				   int nodeLocy = Integer.parseInt(r.getString(3));
				   int nodeLocz = Integer.parseInt(r.getString(4));
					
				   int length = Integer.parseInt(r.getString(5));
				   int capacity = Integer.parseInt(r.getString(6));
				
				   int stat_degreein = Integer.parseInt(r.getString(7)); 
				   int stat_degreeout = Integer.parseInt(r.getString(8));
				
				   int total_traffic = Integer.parseInt(r.getString(9));
				   int total_lost = Integer.parseInt(r.getString(10));
					
				   nodeMetaSet[i][0]=nodeId;
					
				   nodeMetaSet[i][1]=nodeLocx;
				   nodeMetaSet[i][2]=nodeLocy;
				   nodeMetaSet[i][3]=nodeLocz;
					
				   nodeMetaSet[i][4]=length;
				   nodeMetaSet[i][5]=capacity;
					
				   nodeMetaSet[i][6]=stat_degreein;
				   nodeMetaSet[i][7]=stat_degreeout;
					
				   nodeMetaSet[i][8]=total_traffic;
				   nodeMetaSet[i][9]=total_lost;
	          } 
           }catch(Exception e)
           {
    	     e.printStackTrace();
           }
	   return nodeMetaSet;
    }
	
	
	public int[][] loadMetaRelations(Statement sta, String tableName)
	{
	  int[][] relationMetaSet=null;
	  
	  try{
	         String selectMetaRelation="select * from relationmeta"+tableName;
	         
	         ResultSet r = sta.executeQuery(selectMetaRelation);
          
             r.last();
	         int rowCount = r.getRow();
	         r.beforeFirst();
	             
	         int columnCount = 9;
	            
	         relationMetaSet = new int[rowCount][columnCount];
           
	    	 for(int i=0;r.next();i++)
             {
			     int relationId = Integer.parseInt(r.getString(1));
				 int reltype = Integer.parseInt(r.getString(2));
				 
				 int begintime = Integer.parseInt(r.getString(3));
			     int endtime = Integer.parseInt(r.getString(4));
			     
				 int relationNodeFrom = Integer.parseInt(r.getString(5));
				 int relationNodeTo = Integer.parseInt(r.getString(6));
				 
			     int bandwidth = Integer.parseInt(r.getString(7));
			     
				 int stat_totaltraffic = Integer.parseInt(r.getString(8));
				 int stat_totallost = Integer.parseInt(r.getString(9));
				 					 
			     relationMetaSet[i][0] = relationId;
				 relationMetaSet[i][1] = reltype;
			    
				 relationMetaSet[i][2] = begintime; 
				 relationMetaSet[i][3] = endtime;
				 
				 relationMetaSet[i][4] = relationNodeFrom;
				 relationMetaSet[i][5] = relationNodeTo;
				 
				 relationMetaSet[i][6] = bandwidth;
				 
				 relationMetaSet[i][7] = stat_totaltraffic;
				 relationMetaSet[i][8] = stat_totallost;
	         }
         }catch(Exception e)
         {
   	         e.printStackTrace();
         }
	  return relationMetaSet;
	}
	
	 public String  print2()
	 {
		 return "nothing";
	 }
}
