package kernel.basetrace;

import kernel.*;
import kernel.JxBaseNode;
import kernel.JxBaseRelation;
import java.sql.*;

public class JxBaseTraceMetaSet 
{   
	JxBaseNodeCollection m_nodes = new JxBaseNodeCollection();
	JxBaseRelationCollection m_relations = new JxBaseRelationCollection();
	
	public Object m_owner;
	
	public JxBaseTraceMetaSet()
	{
		m_owner=null;
	}
	
	public JxBaseTraceMetaSet( Object owner )
	{
		m_owner=owner;	
	}
	
	public void loadnodes(Statement sta, String tableName)
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
					
				 JxBaseNode node=new JxBaseNode();
				 
				 node.setId(nodeId);
				 node.setX(nodeLocx);
				 node.setY(nodeLocy);
				 
				 m_nodes.add(node);
	         } 
	         for(i=0;i<m_nodes.size();i++)
	         {
	        	 System.out.println(m_nodes.get(i));
	         }
         }catch(Exception e)
         {
    	     e.printStackTrace();
         }
   }
	
	
	public void  loadrelations(Statement sta, String tableName)
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
				 					 
				 JxBaseRelation relation = new JxBaseRelation();
				 
				 relation.setId(relationId);
				 relation.setNodeFrom(m_nodes.get(relationNodeFrom));
				 relation.setNodeTo(m_nodes.get(relationNodeTo));
				
				 m_relations.add(relation);
	         }
	         for(int j=0;j<m_relations.size();j++)
	         {
	        	 System.out.println(m_relations.get(j));
	         }      
         }catch(Exception e)
         {
   	         e.printStackTrace();
         }
	}
}
