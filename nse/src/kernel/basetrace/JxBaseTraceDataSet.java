package kernel.basetrace;

import kernel.JxBaseNode;

import java.sql.ResultSet;
import java.sql.Statement;
import kernel.JxBaseRelation;

public class JxBaseTraceDataSet {
    
	Object owner=null;
	
	public Statement m_statement=null;
	
	JxBaseNode []nodeSet=new JxBaseNode[10000];
	JxBaseRelation []relationSet=new JxBaseRelation[10000];
	
	JxBaseTraceDataSet(Object owner)
	{
	  this.owner=owner;	
	}
	
	/** load information of the node and relation from trace */
	JxBaseNode[] loadNode(String tablename,int begintime,int endtime)
	{
		   try{
		         String selectTraceNode="select * from "+tablename+" where time>="+begintime+"and time<="+endtime;
		         ResultSet r = m_statement.executeQuery(selectTraceNode);
		        
		         int i = 0;
		         
		         while(r.next())
		         {	 
		        	 String time=r.getString(1);
		        	 String nodeId=r.getString(2);
		        	 String str=time+nodeId;
		        	 int  id=Integer.parseInt(str);
		        	
					 int length = Integer.parseInt(r.getString(3));
						
					 nodeSet[i++].setId(id);
					 nodeSet[i++].setValue(length);
		         }
	      }catch(Exception e)
	      {
	    	  e.printStackTrace();
	      }
	      return nodeSet;
	};
	JxBaseRelation[] loadRelation(String tablename,int begintime,int endtime)
	{ try{
        String selectTraceRelation="select * from "+tablename+" where time>="+begintime+"and time<="+endtime;
        ResultSet r = m_statement.executeQuery(selectTraceRelation);
       
        int i = 0;
        
        while(r.next())
        {
        	 String time=r.getString(1);
       	     String nodeId=r.getString(2);
       	     String str=time+nodeId;
       	     int  id=Integer.parseInt(str);
		     
			 int packet= Integer.parseInt(r.getString(3));
				
			 nodeSet[i++].setId(id);
			 nodeSet[i++].setValue(packet);
        }
    }catch(Exception e)
     {
	         e.printStackTrace();
     }
  return relationSet;
	};
	
	
	/** Load the snapshot of the network at the given time*/
	JxBaseNode[] loadNodeSnapShot(String tablename,int giventime)
	{
	  try{
          String selectNodeSnapShot="select * from "+tablename+" where time= "+giventime;
          ResultSet r = m_statement.executeQuery(selectNodeSnapShot);
       
          int i = 0;
        
         while(r.next())
         {  
			 int nodeId = Integer.parseInt(r.getString(2));
			 int length = Integer.parseInt(r.getString(3));
    	     
			 nodeSet[i++].setId(nodeId);
			 nodeSet[i++].setValue(length);	
         }
      }catch(Exception e)
       {
	       e.printStackTrace();
       }
       return nodeSet;
};
	JxBaseRelation[] loadRelationSnapShot(String tablename,int giventime)
	{
	  try{
	         String selectRelationSnapShot="select * from "+tablename+"where time="+giventime;
	         ResultSet r = m_statement.executeQuery(selectRelationSnapShot);
	        
	         int i = 0;
	         
	         while(r.next())
	         {
				 int relationId = Integer.parseInt(r.getString(2));
				 int packet= Integer.parseInt(r.getString(3));
					 
				 relationSet[i++].setId(relationId);
				 relationSet[i++].setPacket(packet);
	         }
         }catch(Exception e)
          {
   	         e.printStackTrace();
          }
       return relationSet;
	};
}
