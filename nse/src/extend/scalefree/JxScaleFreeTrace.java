package extend.scalefree;

import java.sql.Connection;
import java.util.Date; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JxScaleFreeTrace {
    
	Connection con=null;
	
	Statement node_sta=null;
	
	Statement edge_sta=null;
    
	//打开数据库
	public Connection Open_Database(String database)
	{
		try{
			 Class.forName("org.hsqldb.jdbc.JDBCDriver");  //添加驱动 

			 con= DriverManager.getConnection("jdbc:hsqldb:mem:score", "sa", "");
	       } 
		     catch (SQLException e){
              
		       e.printStackTrace();
	       }
		     catch (ClassNotFoundException e){
              
		    	 e.printStackTrace();
           }     
		return con;     
	}

    	
     //创建节点表并保存节点结构
    
      public void Save_NodeTopo(Connection con,JxScaleFreeNodeCollection m_nodes)
      {   
    	   try{
    			 node_sta=con.createStatement();
    	    		
	                     // assert ((con!= null) && !(con.isClosed())); //非空且未关闭
    	     	         // assert (node_sta != null); //非空
    			
    			long cur_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(cur_time);  
     			
     			String node_tablename = "nodetable" + str_time;  
    			
    			String create_node = "create table" + node_tablename + "(NODEID INTERGER,LOC_X INTERGER,LOC_Y INTEGER)";
    			
    			node_sta.executeUpdate(create_node);       //创建节点结构表         
    			
    			   int i;
    			
    			   JxScaleFreeNode node;       //未实例化？？？？
    			
    			   for(i=0;i<m_nodes.count();i++){
    				
    				    node= m_nodes.get(i);
    				
    			        String node_id=Integer.toString(node.get_nodeid()); //转换为字符串
    			
    			        String loc_x= Integer.toString(node.get_x());    
    			  
    			        String loc_y= Integer.toString(node.get_y());     
    			
    		          //（插入节点结构）
    			       
    			        String insert_nodetable = "Insert into"+ node_tablename +"(NODEID,LOC_X,LOC_Y) VALUES ("+node_id+","+loc_x+","+loc_y+")";  
    		
    			        node_sta.executeUpdate(insert_nodetable);	        
    		    }
    			 
    			   node_sta.close();
			        
			       con.close();
    		} 
    		catch (Exception e) {
    			
    			e.printStackTrace();
    		}
    	
    	}
    
     //创建边表并保存
    	
    	public void Save_EdgeTopo(Connection con,JxScaleFreeEdgeCollection m_edges) 
    	{  
    	  try {
    			edge_sta=con.createStatement();
    	     /**		
    			assert ((con != null) && !(con.isClosed())); // 非空且未关闭
    			assert (sta != null); // 非空
    	     */

    			long cur_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(cur_time);  
     			
     			String edge_tablename = "edgetable" + str_time;  
    			
    			String create_edge = "create table" + edge_tablename + "(EDGEID INTERGER,NODE_FROM INTERGER,NODE_TO INTEGER)";
    			
    			node_sta.executeUpdate(create_edge);       //创建边结构表      
    			
    			int i;
    			
    			JxScaleFreeEdge edge= new JxScaleFreeEdge();
    			
    			for(i=0;i<m_edges.count();i++){	
    				
    			     edge=m_edges.get(i);	
    				
    			     String edge_id=Integer.toString(edge.get_edgeid());  //转换为字符串
    			
    			     String node_from=Integer.toString(edge.get_nodefrom());    
    			
    			     String node_to=Integer.toString(edge.get_nodeto()); 
    			
    			     String insert_edgetable = "Insert into"+ edge_tablename +"(EDGEID,NODE_FROM,NODE_TO) VALUES ("+ edge_id +"," + node_from + "," + node_to+ ")";  
    				
    			     edge_sta.executeUpdate(insert_edgetable);	
    			}
    			 
    			edge_sta.close();
    		
    			con.close();
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    		}
    	}	
	
	public void load_nodetopo()  //下载节点结构
	{
		
	}
	public void load_edgetopo()  //下载边结构
	{
	  	
	}
	
}
