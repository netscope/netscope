package extend.scalefree;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Statement;

import java.sql.ResultSet;

import java.util.*;

import java.text.*;

public class JxScaleFreeTrace {
	
    JxScaleFreeNodeCollection m_nodes=new JxScaleFreeNodeCollection();  //(用于保存点的结果) 
    
    ArrayList<JxScaleFreeNode> JoinInNetNode=new ArrayList<JxScaleFreeNode>(); // 已加入网络的节点（组成的链表）
	
    JxScaleFreeEdgeCollection m_edges=new JxScaleFreeEdgeCollection();  //(用于保存边的结果)
    
	JxScaleFreeNodeCollection m_nodesload=new JxScaleFreeNodeCollection(); 
	
	JxScaleFreeEdgeCollection m_edgesload=new JxScaleFreeEdgeCollection();
	
	ArrayList<Integer> m_array = new ArrayList<Integer>();
 	
	JxScaleFreeNode node= null; 
	
	JxScaleFreeEdge edge= null;
	
	Connection con=null;
	
	Statement sta=null;
	
	ResultSet res =null;
	
    Boolean evertra_node =false;  
	
	Boolean evertra_edge =false;
	
	Random random= new Random();
	
	public void generate( int nodecount ){ //产生拓扑
		
	    int i,x,y;   
		
        JxScaleFreeEdge edge=new JxScaleFreeEdge(); //边   
		
	    for (i=0; i< nodecount; i++){  //产生指定数量的点
			
			x=random.nextInt(100);
			
		    y=random.nextInt(100);
			
			m_nodes.add(new JxScaleFreeNode(i,x,y,30,100));//添加新节点（30-初始负载，100——容量）   
		}  
	    
	 // System.out.println(" m_nodesize is "+m_nodes.size());		
     // 创建边并将边加入到边集中       
		
	    for (i = 0; i< nodecount; i++){ //(将节点号为1-9999的节点依次加入网络)
		   
	    	JxScaleFreeNode cur_node = new JxScaleFreeNode(); 
	    	
			JxScaleFreeNode select_node = new JxScaleFreeNode();
			
			ArrayList<Integer> node_array =Randomrank(m_nodes.count());
		 
		    if(i==0){  //第一次直接将第一个节点加入网络
		       
		    	cur_node=m_nodes.get(node_array.get(i));
		    	
		    	JoinInNetNode.add(cur_node); 
		    	 
		    	cur_node.set_degree(cur_node.degree()+1); 
		    }
		    else {
		    	
			cur_node = m_nodes.get(node_array.get(i));
		    
		    JoinInNetNode.add(cur_node);    //当前点加入网络
		    
			cur_node.set_degree(cur_node.degree()+1);  //当前点的度加1
		  
			select_node = selectnodeto();    //选择从已加入网络的点中选择与之相连的节点
			
			JoinInNetNode.add(select_node);  //选中点加入网络
		
			select_node.set_degree(select_node.degree()+1);  //选中点的度加1
			
			edge = new JxScaleFreeEdge(i-1,cur_node.id(),select_node.id(), 10, 0); //新边(边号，起点，终点，带宽，权值)
      
            m_edges.add(edge);//(边号从0开始)
		   }    	  
		}
	}
    //选择边的末节点
	protected JxScaleFreeNode selectnodeto() {  
		
		int p = random.nextInt(JoinInNetNode.size()); //生成在0——列表长度之间的整数值
		
		return JoinInNetNode.get(p);  //返回选中节点
    }
  
	//每一时刻同一边上的相邻节点交换包（包的个数随机）   
	public void evolve(){
	try{
			JxScaleFreeEdge edge;
			
			JxScaleFreeNode sender, receiver;
			
			int packet_num=0;  //记录边上的包流量
			
			ArrayList<Integer> edge_array=Randomrank(m_edges.count());

			for (int i=0; i<m_edges.count(); i++){
				
				edge = m_edges.get_edge(edge_array.get(i));  //随机得到一条边
				
				sender = m_nodes.get_node(edge.nodefrom());  //(得到相应的点(起点id号)
				
				receiver = m_nodes.get_node(edge.nodeto());  //得到相应的点(终点id号)
				
				int r = random.nextInt(2); //随机选择发送方向
	     		     
				      if(r==0){  //从节点1-节点2
				    	  
				    	packet_num=Minimum(sender.get_length(),(receiver.get_capacity()-receiver.get_length()),edge.get_bandwidth());
				    	  
					    sender.set_length(sender.get_length()-packet_num); 
					    
					    receiver.set_length(receiver.get_length()+packet_num);
				      }
			         if(r==1){  //从节点2-节点1
			        	 
			    	     packet_num=Minimum(receiver.get_length(),(sender.get_capacity()-sender.get_length()),edge.get_bandwidth());
					    
					     sender.set_length(sender.get_length()+packet_num);
			    	     
			    	     receiver.set_length(receiver.get_length()-packet_num); 
				      }
			         
			         packet_num+=packet_num;
					   
					 edge.set_packetsum(packet_num); //记录边上包的流量    
	        }
	    } catch(Exception e){
			    	
			e.printStackTrace();
			
		  }	   
	 }
	//求三个值中的最小值
	public int  Minimum(int sender_length,int receiver_capacity,int band_width) { //发送包的个数要小于这三个值		
		
		   int mini=0;
		
		   int minimum=sender_length;
		
		   if(receiver_capacity<minimum)
		
		    minimum=receiver_capacity;
		
		    if(band_width<minimum)
		
		    minimum=band_width;
	 
		    mini=random.nextInt(minimum);
	 
	  return  mini;  
   }
	//产生1-n个随机排列不重复的数字
    public ArrayList<Integer> Randomrank(int nodecount){
   
	 boolean[] bool =new boolean [nodecount];
      
	 int i;
	 
	 int num=0;
	 
	 if(m_array.size()>0) { //每次重新生成时，要保证m_array中是空的。
		 
		 m_array.clear();
	 }
	 
	 for(i=0;i<nodecount;i++){ 
		 
     do{
		 
	    num = random.nextInt(nodecount); 
	  	
	 }while(bool[num]);
	 
	    m_array.add(num);
	  
	    bool[num]=true;
    } 
	 return m_array;
  }
	
	//打开数据库
	public Statement Open_Database()
	{
		try{
			 Class.forName("org.hsqldb.jdbcDriver"); //添加驱动 

			 con= DriverManager.getConnection("jdbc:hsqldb:file:netscope2;shutdown=true", "sa", ""); //建立名为netscope的数据库
			 
			 sta=con.createStatement();  //创建容器
			 
		     System.out.println("con is ok");
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
    public void Save_NodeTopo(int m_nodeslength)
    {   
    	try{	        
     			   String node_topo = "node_topo" +currenttime();  //(数据表名中不能有空格)
    			
    			   String str1 ="create table " + node_topo + "(NODEID INTEGER,LOC_X INTEGER,LOC_Y INTEGER)";
    		
    		       sta.executeUpdate(str1);         //创建节点结构表         
    			
    			   for(int i=0;i<m_nodeslength;i++){
    				
    				    node= m_nodes.get(i);
    				
    			        String node_id=Integer.toString(node.id()); //转换为字符串
    			
    			        String loc_x= Integer.toString(node.x());    
    			  
    			        String loc_y= Integer.toString(node.y());     
    			   			       
    			        String insert_nodetable = "Insert into  people(NODEID,LOC_X,LOC_Y) VALUES ("+node_id+","+loc_x+","+loc_y+")";  
    		
    			        sta.executeUpdate(insert_nodetable);	
    			        
    			        sta.close();
    			        
    			        System.out.println("savenode success!");
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
     			String edge_topo = "edge_topo" + currenttime();  
    			
    			String str2 = "create table "+ edge_topo + "(EDGEID INTEGER,NODE_FROM INTEGER,NODE_TO INTEGER)";
    			
    			sta.executeUpdate(str2);       //创建边结构表      
    				
    			for(int i=0;i<m_edges.count();i++){	
    				
    			     edge=m_edges.get(i);	
    				
    			     String edge_id=Integer.toString(edge.id());  //转换为字符串
    			
    			     String node_from=Integer.toString(edge.nodefrom());    
    			
    			     String node_to=Integer.toString(edge.nodeto()); 
    			
    			     String insert_edgetable = "Insert into " +edge_topo +"(EDGEID,NODE_FROM,NODE_TO) VALUES ("+ edge_id +"," + node_from + "," + node_to+ ")";  
    				     
    			     sta.executeUpdate(insert_edgetable); 
    			     
    			}
    			   System.out.println("saveedge success!"); 
    			
    			   sta.close();
    		}  catch (SQLException e){
                
            	e.printStackTrace();    
            }    
    	}	
	
    public void Load_Nodetopo(String node_tablename) {  //下载节点结构 
    try{
    	
	   String select_nodetopo="select * from "+ node_tablename ; 
    		
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
	    	
			   String select_edgetopo="select * from "+edge_tablename; 
		    		
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
		         String tracenode_table ="tra_node"+currenttime(); //节点数据表名	      
		
		           if( evertra_node==false){  //保证每次节点表建立一次 
	 			
				        String str3 = "create table " + tracenode_table + "(EXP_TIME INTEGER,NODEID INTEGER,LENGTH INTEGER)";
				
				        sta.executeUpdate(str3);  //创建节点结构表 //
				        
			            evertra_node = true;
			      }     
			   
			   String exp_time= String.valueOf(time); 
			
			   for(int i=0;i<m_nodes.count();i++){				
				
				    node= m_nodes.get(i);
				
			        String node_id=Integer.toString(node.id()); //转换为字符串(节点号)
			
			        String length= Integer.toString(node.get_length()); //(包的长度)
			     
		          //（插入节点数据）
			       
			        String trace_node = "Insert into " + tracenode_table  + "(EXP_TIME,NODEID,LENGTH) VALUES ("+exp_time+","+node_id+","+length+")";  
		
			        sta.executeUpdate(trace_node);	  
			        
			        sta.close();
			        
			        System.out.println("tracenode success!");
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
				       
	 			       String traceedge_table = "tra_edge"+ currenttime(); //边数据表名
				
				        String str4= "create table " + traceedge_table+"(EXP_TIME INTEGER, EDGEID INTEGER,PACKETSUM INTEGER)";
				
				        sta.executeUpdate(str4);//创建节点结构表   //
				        
				        evertra_node=true;
			}
 			  
 			String exp_time= String.valueOf(time); //试验次数
 			
 			for(int i=0;i<m_edges.count();i++){	
 				
 			     edge=m_edges.get(i);	
 				
 			     String edge_id=Integer.toString(edge.id());  //转换为字符串
 			
 			     String packet_sum=Integer.toString(edge.get_packetsum());    
 			
 			     String insert_edgetable = "Insert into trace_edge(EXP_TIME,EDGEID,PACKETSUM) VALUES ("+exp_time+"," +edge_id+ "," + packet_sum+ ")";  
 				
 			     sta.executeUpdate(insert_edgetable);	
 			     
 			     sta.close();
 			     
 			    System.out.println("trace edge success!");
 			}
 		} catch (Exception e) {
 			
 			e.printStackTrace();
 		}		
	}
	
    public void CloseDatabase() //关闭数据库
	{   
	   try {
		       sta.close();
		       
		       con.close();  
				
			} catch (SQLException e) {
						
			   con = null;
			}
		}
  
    public String currenttime() {
    	
    	 Date date = new Date();
    	
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_hh_mm");//设置日期格式(数据表格式有要求)
    	 
    	 String cur_time =sdf.format(date);
    	 
    	 return cur_time;
    	 
    }
		
}
