package kernel.basetrace;

import kernel.*;
import java.sql.*;
import java.util.ArrayList;


public class JxBaseTraceMetaSet
{   
	int[][]  m_MetaNodes=new int[10000][3];
	int[][]  m_MetaRelations=new int[9999][3];
	
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
					
				m_MetaNodes[i][0]=nodeId;
				m_MetaNodes[i][1]=nodeLocx;
				m_MetaNodes[i++][2]=nodeLocy;
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
				 int relationNodeFrom = Integer.parseInt(r.getString(2));
				 int relationNodeTo = Integer.parseInt(r.getString(3));
				 					 
			     m_MetaRelations[i][0]=relationId;
				 m_MetaRelations[i][1]=relationNodeFrom;
				 m_MetaRelations[i++][2]=relationNodeTo;
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
