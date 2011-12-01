package kernel.basetrace;

import kernel.*;
import java.sql.*;
import java.util.ArrayList;


public class JxBaseTraceMetaSet
{   
	int[][]  m_MetaNodes=new int[10000][10];
	int[][]  m_MetaRelations=new int[9999][9];
	
	public Object m_owner;
	
	public JxBaseTraceMetaSet()
	{
		m_owner=null;
	}
	
	public JxBaseTraceMetaSet(Object owner)
	{
		m_owner=owner;	
	}
	
	public int[][] loadnodes(Statement sta, String tableName)
	{
	   try{
	         String selectMetaNode="select * from nodemeta"+tableName;
	         ResultSet r = sta.executeQuery(selectMetaNode);
	        
	         int i = 0;
	         while(r.next())
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
				
				m_MetaNodes[i][0]=nodeId;
				
				m_MetaNodes[i][1]=nodeLocx;
				m_MetaNodes[i][2]=nodeLocy;
				m_MetaNodes[i][3]=nodeLocz;
				
				m_MetaNodes[i][4]=length;
				m_MetaNodes[i][5]=capacity;
				
				m_MetaNodes[i][6]=stat_degreein;
				m_MetaNodes[i][7]=stat_degreeout;
				
				m_MetaNodes[i][8]=total_traffic;
				m_MetaNodes[i++][9]=total_lost;
	         } 
	         /**
	         for(int j=0;j<i;j++)
			 {
			    System.out.println("id"+m_MetaNodes[j][0]+"nodefrom"+m_MetaNodes[j][1]+"nodeto"+m_MetaNodes[j][2]);
			 }*/
         }catch(Exception e)
         {
    	     e.printStackTrace();
         }
	  return m_MetaNodes;
   }
	
	
	public int[][] loadrelations(Statement sta, String tableName)
	{
	  try{
	         String selectMetaRelation="select * from relationmeta"+tableName;
	         ResultSet r = sta.executeQuery(selectMetaRelation);
	        
	         int i = 0;	
	         
	         while(r.next())
	         {
			     int relationId = Integer.parseInt(r.getString(1));
				 int reltype = Integer.parseInt(r.getString(2));
				 
				 int begintime = Integer.parseInt(r.getString(3));
			     int endtime = Integer.parseInt(r.getString(1));
			     
				 int relationNodeFrom = Integer.parseInt(r.getString(2));
				 int relationNodeTo = Integer.parseInt(r.getString(3));
				 
			     int bandwidth = Integer.parseInt(r.getString(1));
			     
				 int stat_totaltraffic = Integer.parseInt(r.getString(2));
				 int stat_totallost = Integer.parseInt(r.getString(3));
				 					 
			     m_MetaRelations[i][0] = relationId;
				 m_MetaRelations[i][1] = reltype;
			    
				 m_MetaRelations[i][2] = begintime; 
				 m_MetaRelations[i][3] = endtime;
				 
				 m_MetaRelations[i][4] = relationNodeFrom;
				 m_MetaRelations[i][5] = relationNodeTo;
				 
				 m_MetaRelations[i][6] = bandwidth;
				 
				 m_MetaRelations[i][7] = stat_totaltraffic;
				 m_MetaRelations[i++][8] = stat_totallost;
	         } 
	         /**
	         for(int j=0;j<i;j++)
			 {
			    System.out.println("id"+m_MetaRelations[j][0]+"nodefrom"+m_MetaRelations[j][1]+"nodeto"+m_MetaRelations[j][2]);
			 }*/
         }catch(Exception e)
         {
   	         e.printStackTrace();
         }
	  return m_MetaRelations;
	}
	
	 public String  print2()
	 {
		 return "nothing";
	 }
}
