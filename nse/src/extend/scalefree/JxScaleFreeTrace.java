package extend.scalefree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
import java.text.*;

public class JxScaleFreeTrace {
	
    JxScaleFreeNodeCollection m_nodes=new JxScaleFreeNodeCollection();  
	
	JxScaleFreeEdgeCollection m_edges=new JxScaleFreeEdgeCollection();  
	
    ArrayList<JxScaleFreeNode> JoinInNetNode=new ArrayList<JxScaleFreeNode>(); 

	JxScaleFreeNodeCollection m_nodesload=new JxScaleFreeNodeCollection(); 
	
	JxScaleFreeEdgeCollection m_edgesload=new JxScaleFreeEdgeCollection();
	
	JxScaleFreeNode node= null; 
		
	JxScaleFreeEdge edge= null;

	ResultSet res =null;
	
	Boolean evertra_node=false; 
	
	boolean  evertra_edge=false;
	
   String tracenode_tablename=null;
	 
   String traceedge_tablename=null; 
	
   Connection con = null;

	Statement sta = null;

	ArrayList<Integer> m_array = new ArrayList<Integer>();
 	
	Random random= new Random();
	
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

	public void Save_NodeTopo(Connection con,JxScaleFreeNodeCollection m_nodes1) {

		JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();
		try {

			long sys_time = System.currentTimeMillis();

			String str_time = String.valueOf(sys_time);

			String node_tablename = "nodetable" + str_time;

			String create_node = "create table" + node_tablename
					+ "(NODEID INTERGER,LOC_X INTERGER,LOC_Y INTEGER)";

			sta.executeUpdate(create_node);

			int i;

			JxScaleFreeNode node; //

			for (i = 0; i < m_nodes.count(); i++) {

				node = m_nodes.get(i);

				String node_id = Integer.toString(node.id()); 

				String loc_x = Integer.toString(node.x());

				String loc_y = Integer.toString(node.y());

				String insert_nodetable = "Insert into" + node_tablename
						+ "(NODEID,LOC_X,LOC_Y) VALUES (" + node_id + ","
						+ loc_x + "," + loc_y + ")";

				sta.executeUpdate(insert_nodetable);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	
	public void Save_EdgeTopo(Statement sta)
	{
		try {
			long sys_time = System.currentTimeMillis();

			String str_time = String.valueOf(sys_time);

			String edge_tablename = "edge table" + str_time;

			String create_edge = "create table" + edge_tablename
					+ "(EDGEID INTERGER,NODE_FROM INTERGER,NODE_TO INTEGER)";

			sta.executeUpdate(create_edge); 
			int i;

			JxScaleFreeEdge edge = new JxScaleFreeEdge();

			JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();

			for (i = 0; i < m_edges.count(); i++) {

				edge = m_edges.get(i);

				String edge_id = Integer.toString(edge.id()); 

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

	public void Load_Nodetopo(Statement sta, String tablename) { 

		try {

			String select_nodetopo = "select * from " + tablename;

			ResultSet r = sta.executeQuery(select_nodetopo);

			int i = 0;

			JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();

			while (r.next()) {

				m_nodes.get(i).set_id(Integer.parseInt(r.getString(1))); 
																			

				m_nodes.get(i).set_x((Integer.parseInt(r.getString(1))));

				m_nodes.get(i).set_y((Integer.parseInt(r.getString(1)))); 
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void Load_Edgetopo(Statement sta, String tablename) {

		try {

			String select_edgetopo = "select * from " + tablename;

			ResultSet r = sta.executeQuery(select_edgetopo);

			int i = 0;

			JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();

			while (r.next()) {

				m_edges.get(i).set_id((Integer.parseInt(r.getString(1)))); 
																			

				m_edges.get(i).set_nodefrom((Integer.parseInt(r.getString(1)))); 

				m_edges.get(i).set_nodeto((Integer.parseInt(r.getString(1)))); 

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trace_node(Statement sta, JxScaleFreeNodeCollection m_nodes,
			int time) {

		try {

			boolean tracenode_table = true;

			String tracenode_tablename = null; 
			if (tracenode_table == true)
			{
				long sys_time = System.currentTimeMillis();

				String str_time = String.valueOf(sys_time);

				tracenode_tablename = "trace node packet " + str_time; 

				String create_node = "create table" + tracenode_tablename
						+ "(CUR_TIME INTERGER, NODEID INTERGER,LENGTH INTEGER)";

				sta.executeUpdate(create_node); 

				tracenode_table = false;
			}

			int i;

			JxScaleFreeNode node;

			String cur_time = String.valueOf(time);

			for (i = 0; i < m_nodes.count(); i++) {

				node = m_nodes.get(i);

				String node_id = Integer.toString(node.id());

				String length = Integer.toString(node.get_length()); 

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
			int time) { 

		try {

			boolean traceedge_table = true;

			String traceedge_tablename = null; 

			if (traceedge_table == true) 
			{
				long sys_time = System.currentTimeMillis();

				String str_time = String.valueOf(sys_time);

				traceedge_tablename = "trace edge packet" + str_time;

				String trace_edge = "create table"
						+ traceedge_tablename
						+ "(CUR_TIME INTERGER, EDGEID INTERGER,PACKETSUM INTEGER)";

				sta.executeUpdate(trace_edge); 

				traceedge_table = false;
			}

			int i;

			String cur_time = String.valueOf(time);

			JxScaleFreeEdge edge = new JxScaleFreeEdge();

			for (i = 0; i < m_edges.count(); i++) {

				edge = m_edges.get(i);

				String edge_id = Integer.toString(edge.id()); 

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


	protected JxScaleFreeNode selectnodeto() {  
		
		int p = random.nextInt(JoinInNetNode.size()); 
		
		return JoinInNetNode.get(p);  
    }
  
	
	public void evolve(){
	try{
			JxScaleFreeEdge edge;
			
			JxScaleFreeNode sender, receiver;
			
			int packet_num=0; 
			
			ArrayList<Integer> edge_array=Randomrank(m_edges.count());

			for (int i=0; i<m_edges.count(); i++){
				
				edge = m_edges.get_edge(edge_array.get(i));  
				
				sender = m_nodes.get_node(edge.nodefrom());  
				
				receiver = m_nodes.get_node(edge.nodeto());  
				
				int r = random.nextInt(2);
	     		     
				      if(r==0){  
				    	  
				    	packet_num=Minimum(sender.get_length(),(receiver.get_capacity()-receiver.get_length()),edge.get_bandwidth());
				    	  
					    sender.set_length(sender.get_length()-packet_num); 
					    
					    receiver.set_length(receiver.get_length()+packet_num);
				      }
			         if(r==1){ 
			        	 
			    	     packet_num=Minimum(receiver.get_length(),(sender.get_capacity()-sender.get_length()),edge.get_bandwidth());
					    
					     sender.set_length(sender.get_length()+packet_num);
			    	     
			    	     receiver.set_length(receiver.get_length()-packet_num); 
				      }
			         
			         packet_num+=packet_num;
					   
					 edge.set_packetsum(packet_num); 
	        }
	    } catch(Exception e){
			    	
			e.printStackTrace();
			
		  }	   
	 }
	
	public int  Minimum(int sender_length,int receiver_capacity,int band_width) { 	
		
		   int mini=0;
		
		   int minimum=sender_length;
		
		   if(receiver_capacity<minimum)
		
		    minimum=receiver_capacity;
		
		    if(band_width<minimum)
		
		    minimum=band_width;
	 
		    mini=random.nextInt(minimum);
	 
	  return  mini;  
   }
	
    public ArrayList<Integer> Randomrank(int nodecount){
   
	 boolean[] bool =new boolean [nodecount];
      
	 int i;
	 
	 int num=0;
	 
	 if(m_array.size()>0) { 
		 
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

	
	public Statement Open_Database()
	{
		try{
			 Class.forName("org.hsqldb.jdbcDriver"); 

			 con= DriverManager.getConnection("jdbc:hsqldb:file:netscope2;shutdown=true", "sa", "");
			 
			 sta=con.createStatement();  
			 
		     System.out.println("con is ok");
	       } 
		     catch (SQLException e){
              
		      e.printStackTrace();
	       }
		     catch (ClassNotFoundException e){
              
		      e.printStackTrace();
           }     
		return sta;    
	}    	

    public void Save_NodeTopo(int m_nodeslength)
    {   
    	try{	        
     			   String node_topo = "node_topo" +currenttime();
    			
    			   String str1 ="create table " + node_topo + "(NODEID INTEGER,LOC_X INTEGER,LOC_Y INTEGER)";
    		
    		       sta.executeUpdate(str1);        
    			
    			   for(int i=0;i<m_nodeslength;i++){
    				
    				    node= m_nodes.get(i);
    				
    			        String node_id=Integer.toString(node.id()); 
    			
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
   
    public void Save_EdgeTopo()
    {  
          try { 
     			String edge_topo = "edge_topo" + currenttime();  
    			
    			String str2 = "create table "+ edge_topo + "(EDGEID INTEGER,NODE_FROM INTEGER,NODE_TO INTEGER)";
    			
    			sta.executeUpdate(str2);    
    			
    			for(int i=0;i<m_edges.count();i++){	
    				
    			     edge=m_edges.get(i);	
    				
    			     String edge_id=Integer.toString(edge.id());  
    			
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
	
    public void Load_Nodetopo(String node_tablename) {  
    try{
    	
	   String select_nodetopo="select * from "+ node_tablename ; 
    		
       res =sta.executeQuery(select_nodetopo);
       
       int i=0;
    
       while(res.next()){
     
    	   m_nodesload.get(i++).set_id(Integer.parseInt(res.getString(1)));
  
    	   m_nodesload.get(i++).set_x((Integer.parseInt(res.getString(2))));     
    	   
    	   m_nodesload.get(i++).set_y((Integer.parseInt(res.getString(2))));    
    	
        }
       res.close();
       
       sta.close();
       
      }catch(Exception e){
    	 
    	  e.printStackTrace();
      }	
	}
	
	public void Load_Edgetopo(String edge_tablename)  {
	try{
	    	
			   String select_edgetopo="select * from "+edge_tablename; 
		    		
		       res =sta.executeQuery(select_edgetopo);
		       
		       int i=0;
		  
		       while(res.next()){

		    	   m_edgesload.get(i++).set_id((Integer.parseInt(res.getString(1)))); 

		    	   m_edgesload.get(i++).set_nodefrom((Integer.parseInt(res.getString(2))));    
		    	   
		    	   m_edgesload.get(i++).set_nodeto((Integer.parseInt(res.getString(3))));    
		    	
		    	   res.close();
		    	   
		    	   sta.close();
		      }
		      }catch(Exception e){
		    	 
		    	  e.printStackTrace();
		    	  
		      }   
	}
	
	public void Trace_Node(int time){   	
	try{
		         String tracenode_table ="tra_node"+currenttime();     
		
		           if( evertra_node==false){  
	 			
				        String str3 = "create table " + tracenode_table + "(EXP_TIME INTEGER,NODEID INTEGER,LENGTH INTEGER)";
				
				        sta.executeUpdate(str3); 
				        
			            evertra_node = true;
			      }     
			   
			   String exp_time= String.valueOf(time); 
			
			   for(int i=0;i<m_nodes.count();i++){				
				
				    node= m_nodes.get(i);
				
			        String node_id=Integer.toString(node.id()); 
			
			        String length= Integer.toString(node.get_length()); 
			    
			       
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
	
	public void Trace_Edge(int time){
	try {
		
			if(evertra_node==false) { 
				       
	 			       String traceedge_table = "tra_edge"+ currenttime();
				
				        String str4= "create table " + traceedge_table+"(EXP_TIME INTEGER, EDGEID INTEGER,PACKETSUM INTEGER)";
				
				        sta.executeUpdate(str4);
				        
				        evertra_node=true;
			}
 			  
 			String exp_time= String.valueOf(time);
 			
 			for(int i=0;i<m_edges.count();i++){	
 				
 			     edge=m_edges.get(i);	
 				
 			     String edge_id=Integer.toString(edge.id()); 
 			
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
	
    public void CloseDatabase(){   
	 try {
		       sta.close();
		       con.close();  
		} catch (SQLException e) {
			  con = null;
		  }
   }

    public String currenttime() {	
    	 Date date = new Date();
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_hh_mm");
    	 String cur_time =sdf.format(date);
    	 return cur_time;	 
    }
}
