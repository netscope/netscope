package kernel.basetrace;

import kernel.JxBaseNode;
import kernel.JxBaseRelation;
import java.sql.*;

public class JxBaseTraceMetaSet 
{
	
	public Object m_owner;
	public Statement m_statement=null;
	
	JxBaseNode []nodeSet=new JxBaseNode[100000];
	JxBaseRelation []relationSet=new JxBaseRelation[100000];
	
	JxBaseTraceMetaSet( Object owner )
	{
		m_owner=owner;
	}
	
	public JxBaseNode[] loadnode(String nodeTableName)
	{
	   try{
	         String selectMetaNode="select * from "+nodeTableName;
	         ResultSet r = m_statement.executeQuery(selectMetaNode);
	        
	         int i = 0;
	         
	         while(r.next())
	         {
			     int nodeId = Integer.parseInt(r.getString(1));
				 int nodeLocx = Integer.parseInt(r.getString(2));
				 int nodeLocy = Integer.parseInt(r.getString(3));
					
				 nodeSet[i++].setId(nodeId);
				 nodeSet[i++].setX(nodeLocx);
				 nodeSet[i++].setY(nodeLocy);
	         }
      }catch(Exception e)
      {
    	  e.printStackTrace();
      }
      return nodeSet;
   }
	
	public JxBaseRelation[] loadrelation(String relationTableName)
	{
	  try{
	         String selectMetaRelation="select * from "+relationTableName;
	         ResultSet r = m_statement.executeQuery(selectMetaRelation);
	        
	         int i = 0;
	         
	         while(r.next())
	         {
			     int relationId = Integer.parseInt(r.getString(1));
				 int relationLocx = Integer.parseInt(r.getString(2));
				 int relationLocy = Integer.parseInt(r.getString(3));
					
				 nodeSet[i++].setId(relationId);
				 nodeSet[i++].setX(relationLocx);
				 nodeSet[i++].setY(relationLocy);
	         }
         }catch(Exception e)
          {
   	         e.printStackTrace();
          }
       return relationSet;
	}
}
