/**
 * 
 */
package extend.scalefree;

import java.util.*;
import java.sql.Statement;
import java.sql.Connection;

public class JxScaleFreeSimuApplication {

	ArrayList<JxScaleFreeNode> JoinInNetNode;  //已加入网络的节点（组成的链表）
	
	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //点集合
	
	JxScaleFreeTopology  topo =new JxScaleFreeTopology(); 
	
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //边集合	
	
	JxScaleFreeTopology m_topo= new JxScaleFreeTopology();
	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();   //保存结构	
	
	public static Random random = new Random();
	
	Statement sta=null;	
	
	Connection con=null;
		
	String str;
	 
	int packet_num;
	//初始化
	public JxScaleFreeSimuApplication(){
		
		 m_trace = new JxScaleFreeTrace(); //保存结构
		    
   	     m_edges = new JxScaleFreeEdgeCollection(); //边集合(创建，保存)
   	     
   	     m_nodes = new JxScaleFreeNodeCollection(); //点集合(创建，保存)	
   	     
   		 random = new Random();
			
		 str = null;
	 
	}	
	
    public	void trace(int time) {
	
	    m_trace.Trace_Node( time );   //time为试验次数
	    
	    m_trace.Trace_Edge( time ); 
		
	}
    
    public void load(){   //数据库——内存
		
		m_trace.Load_Edgetopo(str);
		
		m_trace.Load_Nodetopo(str);
	}
	
	public void exit(){
		
		m_trace.CloseDatabase();
		
	}
	public void init() {  //初始化（产生拓扑结构）
	
		String database=null;   //初始化
		
		sta=m_trace.openDatabase( database );  //打开数据库
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //保存节点结构
		
	    topo.generate( 10 );     
		    
	    topo.save(); 	
	}

	public void run(int duration){  //运行
		
		for (int time=0; time<duration; time++){ 

		    m_trace.evolve();

			trace(time);  //保存实验结果		
		}	
	}

	public static void main(String[] args) {
		
		JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication();
		 
		app.init();
		
		app.run(10);
		
		System.out.println("success !");
	}
}

