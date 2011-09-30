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
	
	/**二维数组，第一维为试验次数，第二维为节点标号
	 * 
	 * */
	JxBaseNode [][]nodeSet = new JxBaseNode[1000][1000];
	JxBaseRelation [][]relationSet = new JxBaseRelation[1000][1000];
	
	JxBaseNodeCollection nodeCollection = new JxBaseNodeCollection();
	JxBaseRelationCollection relationCollection = new JxBaseRelationCollection();
	
	public void init()
	{
	  for(int i=0;i<1000;i++)
	  {
		  for(int j=0;j<1000;j++)
		  {
			  nodeSet[i][j] = new JxBaseNode();
			  relationSet[i][j] = new JxBaseRelation();
		  }
	  }
	}

	/** load information of the node and relation from trace */
	JxBaseNode[][] loadNode(Statement sta,String tableName,int beginTime,int endTime)
	{
	 try{    
		   while(beginTime<endTime)
		   {	  
	          String selectTraceNode="select * from "+tableName+" where time="+beginTime;
	          ResultSet r = sta.executeQuery(selectTraceNode);

	          String nodeId = r.getString(2);
	          String nodeLength = r.getString(3);
	        
	          int  id = Integer.parseInt(nodeId);
	          int  length = Integer.parseInt(nodeLength);
					
			  nodeSet[beginTime][id].setId(id);
			  nodeSet[beginTime][id].setValue(length);
			  
			  beginTime++;
	       }
	     }catch(Exception e)
	     {
	        e.printStackTrace();
	     }
	      return nodeSet;
	}
	
	JxBaseRelation[][] loadRelation(Statement sta,String tableName,int beginTime,int endTime)
	{ 
		try{
			 while(beginTime<endTime)
			 { 	
                String selectTraceRelation="select * from "+tableName+" where time="+beginTime;
                ResultSet r = sta.executeQuery(selectTraceRelation);
       
	       	    String relationId = r.getString(2);
	       	    String relationPacket =r.getString(3);
	       	   
	       	    int id = Integer.parseInt(relationId);
	       	    int packet = Integer.parseInt(relationPacket);
					
	       	    
				relationSet[beginTime][id].setId(id);
				relationSet[beginTime][id].setPacket(packet);
				
				beginTime++;
             }
            }catch(Exception e)
             {
	           e.printStackTrace();
             }
              return relationSet;
	}
	
	
	/** Load the snapshot of the network at the given time*/
	JxBaseNodeCollection loadNodeSnapShot(Statement sta,String tableName,int givenTime)
	{
	 try{
          String selectNodeSnapShot="select * from "+tableName+" where time = "+givenTime;
          ResultSet r = sta.executeQuery(selectNodeSnapShot);
       
          int i = 0;
        
          while(r.next())
          {  
			 int id = Integer.parseInt(r.getString(2));
			 int length = Integer.parseInt(r.getString(3));
    	     
			 nodeCollection.get(i++).setId(id);
			 nodeCollection.get(i++).setValue(length);	
          }
      }catch(Exception e)
       {
	       e.printStackTrace();
       }
       return nodeCollection;
   }
	
	
	JxBaseRelationCollection loadRelationSnapShot(Statement sta,String tablename,int giventime)
	{
	  try{
	         String selectRelationSnapShot=" select * from "+tablename+" where time= "+giventime;
	         ResultSet r = sta.executeQuery(selectRelationSnapShot);
	        
	         int i = 0;
	         
	         while(r.next())
	         {
				int id = Integer.parseInt(r.getString(2));
				int packet= Integer.parseInt(r.getString(3));
					 
			    relationCollection.get(i).setId(id);
				relationCollection.get(i).setPacket(packet);
	         }
         }catch(Exception e)
         {
   	         e.printStackTrace();
         }
       return relationCollection;
	}
} 
