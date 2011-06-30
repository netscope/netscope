package extend.scalefree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JxScaleFreeTrace {

JxScaleFreeNodeCollection m_nodes=new JxScaleFreeNodeCollection();  //(用于保存点的结果) 
	
	JxScaleFreeEdgeCollection m_edges=new JxScaleFreeEdgeCollection();  //(用于保存边的结果)
    
	JxScaleFreeNodeCollection m_nodesload=new JxScaleFreeNodeCollection(); 
	
	JxScaleFreeEdgeCollection m_edgesload=new JxScaleFreeEdgeCollection();
	
	JxScaleFreeNode node= null; 
		
	JxScaleFreeEdge edge= null;
	
	
	ResultSet res =null;
	
	Boolean evertra_node=false;  //一定要做成成员变量
	
	boolean  evertra_edge=false;
	
   String tracenode_tablename=null;
	 
   String traceedge_tablename=null; 
	
   Connection con = null;

	Statement sta = null;

	// 打开数据库
	public Statement openDatabase(String database) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:mem:score", "sa", "");
			sta = con.createStatement();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return sta;
	}

	// 创建节点表并保存节点结构
	public void Save_NodeTopo(Statement sta) {

		JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();
		try {

			long sys_time = System.currentTimeMillis();

			String str_time = String.valueOf(sys_time);

			String node_tablename = "nodetable" + str_time;

			String create_node = "create table" + node_tablename
					+ "(NODEID INTERGER,LOC_X INTERGER,LOC_Y INTEGER)";

			sta.executeUpdate(create_node); // 创建节点结构表

			int i;

			JxScaleFreeNode node; // 未实例化？？？？

			for (i = 0; i < m_nodes.count(); i++) {

				node = m_nodes.get(i);

				String node_id = Integer.toString(node.id()); // 转换为字符串

				String loc_x = Integer.toString(node.x());

				String loc_y = Integer.toString(node.y());

				// （插入节点结构）

				String insert_nodetable = "Insert into" + node_tablename
						+ "(NODEID,LOC_X,LOC_Y) VALUES (" + node_id + ","
						+ loc_x + "," + loc_y + ")";

				sta.executeUpdate(insert_nodetable);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 创建边表并保存
	public void Save_EdgeTopo(Statement sta)

	{
		try {
			long sys_time = System.currentTimeMillis();

			String str_time = String.valueOf(sys_time);

			String edge_tablename = "edge table" + str_time;

			String create_edge = "create table" + edge_tablename
					+ "(EDGEID INTERGER,NODE_FROM INTERGER,NODE_TO INTEGER)";

			sta.executeUpdate(create_edge); // 创建边结构表

			int i;

			JxScaleFreeEdge edge = new JxScaleFreeEdge();

			JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();

			for (i = 0; i < m_edges.count(); i++) {

				edge = m_edges.get(i);

				String edge_id = Integer.toString(edge.id()); // 转换为字符串

				String node_from = Integer.toString(edge.nodefrom());

				String node_to = Integer.toString(edge.nodeto());

				String insert_edgetable = "Insert into" + edge_tablename
						+ "(EDGEID,NODE_FROM,NODE_TO) VALUES (" + edge_id + ","
						+ node_from + "," + node_to + ")";

				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void Load_Nodetopo(Statement sta, String tablename) { // 下载节点结构

		try {

			String select_nodetopo = "select * from " + tablename;

			ResultSet r = sta.executeQuery(select_nodetopo);

			int i = 0;

			JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();

			while (r.next()) {

				m_nodes.get(i).set_id(Integer.parseInt(r.getString(1))); // 得到节点
																			// 标号

				m_nodes.get(i).set_x((Integer.parseInt(r.getString(1)))); // 得到节点X坐标

				m_nodes.get(i).set_y((Integer.parseInt(r.getString(1)))); // 得到节点y坐标

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void Load_Edgetopo(Statement sta, String tablename) {// 下载边结构

		try {

			String select_edgetopo = "select * from " + tablename;

			ResultSet r = sta.executeQuery(select_edgetopo);

			int i = 0;

			JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();

			while (r.next()) {

				m_edges.get(i).set_id((Integer.parseInt(r.getString(1)))); // 得到节点
																			// 标号

				m_edges.get(i).set_nodefrom((Integer.parseInt(r.getString(1)))); // 得到节点X坐标

				m_edges.get(i).set_nodeto((Integer.parseInt(r.getString(1)))); // 得到节点y坐标

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trace_node(Statement sta, JxScaleFreeNodeCollection m_nodes,
			int time) {// 保存点数据

		try {
			// 可以考虑返回sta作为参数

			boolean tracenode_table = true;

			String tracenode_tablename = null; // 一定要赋初值！！！！

			if (tracenode_table == true) // 保证只建立一次节点数据表
			{
				long sys_time = System.currentTimeMillis();

				String str_time = String.valueOf(sys_time);

				tracenode_tablename = "trace node packet " + str_time; // 节点数据表名

				String create_node = "create table" + tracenode_tablename
						+ "(CUR_TIME INTERGER, NODEID INTERGER,LENGTH INTEGER)";

				sta.executeUpdate(create_node); // 创建节点结构表

				tracenode_table = false;
			}

			int i;

			JxScaleFreeNode node; // 未实例化？？？？

			String cur_time = String.valueOf(time);

			for (i = 0; i < m_nodes.count(); i++) {

				node = m_nodes.get(i);

				String node_id = Integer.toString(node.id()); // 转换为字符串(节点号)

				String length = Integer.toString(node.length()); // (包的长度)

				// （插入节点数据）

				String trace_node = "Insert into" + tracenode_tablename
						+ "(CUR_TIME,NODEID,LENGTH) VALUES (" + cur_time + ","
						+ node_id + "," + length + ")";

				sta.executeUpdate(trace_node);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void trace_edge(Statement sta, JxScaleFreeEdgeCollection m_edges,
			int time) { // 保存边数据

		try {

			boolean traceedge_table = true;

			String traceedge_tablename = null; // 一定要赋初值！！！！

			if (traceedge_table == true) // 保证只建立一次节点数据表
			{
				long sys_time = System.currentTimeMillis();

				String str_time = String.valueOf(sys_time);

				traceedge_tablename = "trace edge packet" + str_time; // 边数据表名

				String trace_edge = "create table"
						+ traceedge_tablename
						+ "(CUR_TIME INTERGER, EDGEID INTERGER,PACKETSUM INTEGER)";

				sta.executeUpdate(trace_edge); // 创建节点结构表

				traceedge_table = false;
			}

			int i;

			String cur_time = String.valueOf(time);

			JxScaleFreeEdge edge = new JxScaleFreeEdge();

			for (i = 0; i < m_edges.count(); i++) {

				edge = m_edges.get(i);

				String edge_id = Integer.toString(edge.id()); // 转换为字符串

				String packet_sum = Integer.toString(edge.get_packetsum());

				String insert_edgetable = "Insert into" + traceedge_tablename
						+ "(CUR_TIME,EDGEID,PACKETSUM) VALUES (" + cur_time
						+ "," + edge_id + "," + packet_sum + ")";

				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	//打开数据库
	public Statement Open_Database()
	{
		try{
			 Class.forName("org.hsqldb.jdbcDriver"); //添加驱动 

			 con= DriverManager.getConnection("jdbc:hsqldb:file:netscope;shutdown=true", "sa", ""); //建立名为netscope的数据库
			 
			 sta=con.createStatement();  //创建容器
	       } 
		     catch (SQLException e){
              
		      e.printStackTrace();
	       }
		     catch (ClassNotFoundException e){
              
		      e.printStackTrace();
           }     
		return sta;    //返回容器 
	}    	
    
	//创建节点表并保存节点结构  
	
    public void Save_NodeTopo()
    {   
    	try{
    			long sys_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(sys_time);  
     			
     			String node_topo = "nodetopo" + str_time;  
    			
    			String create_node = "create table" + node_topo + "(NODEID INTERGER,LOC_X INTERGER,LOC_Y INTEGER)";
    			
    			sta.executeUpdate(create_node);       //创建节点结构表         
    			
    			   for(int i=0;i<m_nodes.count();i++){
    				
    				    node= m_nodes.get(i);
    				
    			        String node_id=Integer.toString(node.id()); //转换为字符串
    			
    			        String loc_x= Integer.toString(node.x());    
    			  
    			        String loc_y= Integer.toString(node.y());     
    			   			       
    			        String insert_nodetable = "Insert into"+ node_topo +"(NODEID,LOC_X,LOC_Y) VALUES ("+node_id+","+loc_x+","+loc_y+")";  
    		
    			        sta.executeUpdate(insert_nodetable);	
    			        
    			        sta.close();
    			   }
		   } 
    	   catch (SQLException e){
               
           	e.printStackTrace();
           
           }
      }
  
     //创建边表并保存
    public void Save_EdgeTopo()
    {  
    	  try {
    			long sys_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(sys_time);  
     			
     			String edge_topo = "edgetopo" + str_time;  
    			
    			String create_edge = "create table" + edge_topo + "(EDGEID INTERGER,NODE_FROM INTERGER,NODE_TO INTEGER)";
    			
    			sta.executeUpdate(create_edge);       //创建边结构表      
    				
    			for(int i=0;i<m_edges.count();i++){	
    				
    			     edge=m_edges.get(i);	
    				
    			     String edge_id=Integer.toString(edge.id());  //转换为字符串
    			
    			     String node_from=Integer.toString(edge.nodefrom());    
    			
    			     String node_to=Integer.toString(edge.nodeto()); 
    			
    			     String insert_edgetable = "Insert into"+ edge_topo +"(EDGEID,NODE_FROM,NODE_TO) VALUES ("+ edge_id +"," + node_from + "," + node_to+ ")";  
    				
    			     sta.executeUpdate(insert_edgetable);	
    			     
    			     sta.close();
    			}
    			
    		}  catch (SQLException e){
                
            	e.printStackTrace();    
            }    
    	}	
	
    public void Load_Nodetopo(String node_tablename) {  //下载节点结构
   
   try{
    	
	   String select_nodetopo="select * from"+ node_tablename ; 
    		
       res =sta.executeQuery(select_nodetopo);
       
       int i=0;
    
       while(res.next()){
     
    	   m_nodesload.get(i++).set_id(Integer.parseInt(res.getString(1)));  //得到节点 标号
  
    	   m_nodesload.get(i++).set_x((Integer.parseInt(res.getString(2))));     //得到节点X坐标
    	   
    	   m_nodesload.get(i++).set_y((Integer.parseInt(res.getString(2))));     //得到节点y坐标
    	
      }
       res.close();
       
       sta.close();
       
      }catch(Exception e){
    	 
    	  e.printStackTrace();
      }	
	}
	
	public void Load_Edgetopo(String edge_tablename)  {//下载边结构

		try{
	    	
			   String select_edgetopo="select * from"+edge_tablename ; 
		    		
		       res =sta.executeQuery(select_edgetopo);
		       
		       int i=0;
		  
		       while(res.next()){

		    	   m_edgesload.get(i++).set_id((Integer.parseInt(res.getString(1)))); //得到节点 标号

		    	   m_edgesload.get(i++).set_nodefrom((Integer.parseInt(res.getString(2))));    //得到节点X坐标
		    	   
		    	   m_edgesload.get(i++).set_nodeto((Integer.parseInt(res.getString(3))));     //得到节点y坐标
		    	
		    	   res.close();
		    	   
		    	   sta.close();
		      }
		      }catch(Exception e){
		    	 
		    	  e.printStackTrace();
		    	  
		      }   
	}
	
	public void Trace_Node(int time){ //保存点数据( time: 运 行 次 数 )	   	
	try{
			  //可以考虑返回sta作为参数
			 
			 // String tracenode_tablename = null;  //一定要赋初值！
			 
			   //保证只建立一次节点数据表
			      if( evertra_node==false){  //保证每次节点表建立一次
			    	  
				        long sys_time = System.currentTimeMillis();
		 			
	 			        String str_time=String.valueOf(sys_time);  
	 			
	 			        tracenode_tablename = "trace node packet" + str_time; //节点数据表名
				
				        String create_node = "create table" +tracenode_tablename + "(CUR_TIME INTERGER, NODEID INTERGER,LENGTH INTEGER)";
				
				        sta.executeUpdate(create_node);  //创建节点结构表       
				        
			            evertra_node = true;
			      }     
			   
			   String cur_time= String.valueOf(time); 
			
			   for(int i=0;i<m_nodes.count();i++){				
				
				    node= m_nodes.get(i);
				
			        String node_id=Integer.toString(node.id()); //转换为字符串(节点号)
			
			        String length= Integer.toString(node.get_length()); //(包的长度)
			     
		          //（插入节点数据）
			       
			        String trace_node = "Insert into"+ tracenode_tablename +"(CUR_TIME,NODEID,LENGTH) VALUES ("+cur_time+","+node_id+","+length+")";  
		
			        sta.executeUpdate(trace_node);	  
			        
			        sta.close();
			   }
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
	} 
	
	public void Trace_Edge(int time){ //保存边数	
	try {
		
			if(evertra_node==false) { 
			
	       //保证只建立一次节点数据表
				        long sys_time = System.currentTimeMillis();
		 			
	 			        String str_time=String.valueOf(sys_time);  
	 			
	 			        traceedge_tablename = "trace edge packet" + str_time;   //边数据表名
				
				        String trace_edge = "create table" +traceedge_tablename + "(CUR_TIME INTERGER, EDGEID INTERGER,PACKETSUM INTEGER)";
				
				        sta.executeUpdate(trace_edge);//创建节点结构表 
				        
				        evertra_node=true;
			}
			
 			  
 			String cur_time= String.valueOf(time); 
 			
 			for(int i=0;i<m_edges.count();i++){	
 				
 			     edge=m_edges.get(i);	
 				
 			     String edge_id=Integer.toString(edge.id());  //转换为字符串
 			
 			     String packet_sum=Integer.toString(edge.get_packetsum());    
 			
 			     String insert_edgetable = "Insert into"+ traceedge_tablename +"(CUR_TIME,EDGEID,PACKETSUM) VALUES ("+cur_time+"," + edge_id+ "," + packet_sum+ ")";  
 				
 			     sta.executeUpdate(insert_edgetable);	
 			     
 			     sta.close();
 			}
 		} catch (Exception e) {
 			
 			e.printStackTrace();
 		}		
	}
	
	
    public void CloseDatabase(Connection c) //关闭数据库
	{   
	   try {
		       sta.close();
		       
		       con.close();  
				
			} catch (SQLException e) {
						
				con = null;
			}
		}
}
