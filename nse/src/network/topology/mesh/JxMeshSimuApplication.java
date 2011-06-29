package network.topology.mesh;

import java.sql.Connection;
import java.util.Date; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JxMeshSimuApplication {
	public void opendatabase(String database) //打开数据库
	{
		Connection con = null;
		Statement sta = null;
		System.out.println("dbconnect.createnodetable() is ok");
		try {
			if (con != null)
				con.close();
			Class.forName("org.hsqldb.jdbc.JDBCDriver"); // 添加驱动 
            String url="";
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

	public void CloseDatabase() //关闭数据库
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

	public void CreateDataBase() //创建数据库
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

	public void CreateNodeTable() //创建节点表
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
	public void CreateEdgeTable() // 创建边表
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

	public boolean savetopology(JiNetWorkTopology topo) // 保存节点，边信息
	{   Connection con=null;
        Statement sta=null;
		int i, j = 0;
		try {
			for (i = 0; i < topo.node.length; i++) {
				String sql4 = "insert into Node_topology(int topo.node[i],int topo.node[i].loc_x ,int topo.node[i].loc_y ,int topo.node[i].tx_power )"; // 插入边表
																																						// }
				sta.executeUpdate(sql4);
			}
			for (j = 0; j < topo.edge.length; j++) {
				String sql5 = "insert into Edge_topology(int topo.edge[i],int topo.edge[i].nodefrom,int topo.edge[i].nodeto)";// 插入边表
				sta.executeUpdate(sql5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (true);
	}

	public boolean LoadTopology(JiNetWorkTopology topo) // 从数据库到内存
	{
		try { 
			Connection con=null;
            Statement sta=null;
			assert ((con != null) && !(con.isClosed())); // 非空且未关闭
			assert (sta != null); // 非空
			String sql6 = "select * from Node_topology";
			ResultSet rs1 = sta.executeQuery(sql6);
			while (rs1.next()) {
				int i = 0;
				int i1 = rs1.getInt(1);
				int i2 = rs1.getInt(2);
				int i3 = rs1.getInt(3);
				int i4 = rs1.getInt(4);

				topo.node[i++].node_id = i1;
				topo.node[i++].loc_x = i2;
				topo.node[i++].loc_y = i3;
				topo.node[i++].tx_power = i4;
			}
			String sql7 = "select * from Edge_topology";
			ResultSet rs2 = sta.executeQuery(sql7);
			while (rs2.next()) {
				int i = 0;
				int i1 = rs1.getInt(1);
				int i2 = rs1.getInt(2);
				int i3 = rs1.getInt(3);

				topo.edge[i++].edge_id = i1;
				topo.edge[i++].nodefrom = i2;
				topo.edge[i++].nodeto = i3;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return (true);
	}

	public boolean Trace(int cur_time, int node_id, int que_length) // 记录每一时刻结点的数据包数
	{
		return (true);
	}

	public boolean Trace(int cur_time, int edge_id, long packet_sum) // 记录每条边的包的流量
	{
		return (true);
	}

	public JiNetWorkTopology GenerateMeshTopology(int radis) // 建 一张mesh网
	
	{
		return null;

	}
}
