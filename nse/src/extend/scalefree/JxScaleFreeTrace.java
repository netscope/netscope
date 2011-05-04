package extend.scalefree;

import java.sql.Connection;
import java.util.Date; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JxScaleFreeTrace {

    //打开数据库
	public void opendatabase(){
		
		Connection con = null;
		Statement sta = null;
		//System.out.println("dbconnect.createnodetable() is ok");
		try {
			if (con != null)
				con.close();
			Class.forName("org.hsqldb.jdbc.JDBCDriver"); // 添加驱动 

			con = DriverManager.getConnection(url, "sa", "");
			sta = con.createStatement();
		} catch (SQLException e) {
			con = null;
			sta = null;
		} catch (ClassNotFoundException e) {
			con = null;
			sta = null;
		}
	}
	
	//关闭数据库
    public void CloseDatabase() 
    	{
    	    Connection con = null;
    		Statement sta = null;
    		try {
    			if (sta != null) {
    				sta.executeUpdate("SHUTDOWN"); // SHUTDOWN用法 
    				sta.close();
    			}
    			if (con != null) {
    				con.close();
    			}
    		} catch (SQLException e) {
    			sta = null;
    			con = null;
    		}
    	}
    	
    
    //创建数据库
    	public void CreateDataBase() 
    	{  
    		Connection con=null;
            Statement sta=null;
    		try {
    			assert ((con != null) && !(con.isClosed())); // 非空且未关闭
    			assert (sta != null); // 非空
    			Date   date=new   Date(); 
    			String   newdatabase=date.toString(); 
    			String sql1 = "create   database"+newdatabase;
    			sta.executeUpdate(sql1);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
 
     //创建节点表
    	public void CreateNodeTable()
    	{   
    		Connection con=null;
            Statement sta=null;
    		try{
    			assert ((con!= null) && !(con.isClosed())); //非空且未关闭
    			assert (sta != null); //非空
    			String sql2 = "create table Node_topology((int node_id,int loc_x,int loc_y,int tx.power)";// 创建节点表
    			sta.executeUpdate(sql2);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    
     // 创建边表
    	public void CreateEdgeTable() 
    	{  
    		Connection con=null;
            Statement sta=null;
    		try {
    			assert ((con != null) && !(con.isClosed())); // 非空且未关闭
    			assert (sta != null); // 非空
    			String sql3 = "create table Edge_topology((int edge_id,int node_from,int node_to)";// 创建边表
    			sta.executeUpdate(sql3);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
	
	
	public void save_nodetopo()  //保存点结构
	{
	   	
	}
	public void save_edgetopo()  //保存边结构
	{
	 	
	}
	public void trace_node()     //记录点包长度(数据分析)
	{
		 
	}
	public void trace_edge()     //记录边发送包的数量(数据分析)
	{
		 
	}
	
	public void load_nodetopo()  //下载节点结构
	{
		
	}
	public void load_edgetopo()  //下载边结构
	{
	  	
	}
	
}
