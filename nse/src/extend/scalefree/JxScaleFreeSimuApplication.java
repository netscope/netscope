/**
 * 
 */
package extend.scalefree;
import java.sql.Connection;
import java.util.*;
import kernel.JxBaseEvent;
import kernel.JxBaseEventQueue;
import kernel.JxBaseSimulator;
import java.sql.Statement;
/**
 * @author Allen
 *
 */
public class JxScaleFreeSimuApplication {

	ArrayList<JxScaleFreeNode> JoinInNetNode;  //已加入网络的节点（组成的链表）
	
	public static Random random = new Random();

	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //点集合
	
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //边集合
	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();   //保存结构
	
	Statement sta=null;
	
	Connection con=null;
		
	//初始化
	
	void init()  
	{

		String database=null;
       
	    generate( 10000 );                     
		
	}
	
	void generate( int nodecount ) //产生拓扑
	{		
		  int i, x, y;
		
		  JxScaleFreeEdge edge;   //边
		
		  JxScaleFreeNode node;   //接点
		
	    for (i=0; i< nodecount; i++){
			
			x = random.nextInt(100);
			
			y = random.nextInt(100);
			
			m_nodes.add( new JxScaleFreeNode( x, y, 100 ));//添加新节点（100——容量）
			
		}
	
           // 创建边并将边加入到边集中
		
		    node = m_nodes.get(0);   //第一个点(作为初始点)                
	       
		    JoinInNetNode.add(node); //加入到网络中
	        
		    node.set_degree(1);      
		
	    for (i = 1; i<m_nodes.count(); i++) //(将节点号为1-9999的节点依次加入网络)
		{   
			
	    	JxScaleFreeNode cur_node; 
	    	
			JxScaleFreeNode select_node;
		  
		    cur_node = m_nodes.get(i);       //当前节点
		  
			select_node =selectnodeto(i-1);  //选择与之相连的节点
            
			JoinInNetNode.add(cur_node);     //当前点加入网络
			
			cur_node.set_degree(cur_node.degree()+1);   //当前点的度加1
			
			JoinInNetNode.add(select_node);            //选中点加入网络
			
			select_node.set_degree(select_node.degree()+1);   //选中点的度加1
			
			edge = new JxScaleFreeEdge( i,cur_node.id(),select_node.id(), 10, 0 ); //新边(边号，起点，终点，带宽，权值)
			
			m_edges.add(edge);
		}
	}
	     
	 //选择边的末节点
	protected JxScaleFreeNode selectnodeto(int b) {  
	
		int p = random.nextInt(JoinInNetNode.size());//生成在0——列表长度之间的整数值
	   
		return JoinInNetNode.get(p); //返回选中点
	    
	}
	
	
	//每一时刻同一边上的相邻节点交换包（包的个数随机）
	void evolve()
	{
		int i;
		
		JxScaleFreeEdge edge;
		
		JxScaleFreeNode node1, node2;
		
		JxScaleFreeNode  sender=new JxScaleFreeNode();  
		
		JxScaleFreeNode  receiver=new JxScaleFreeNode(); 

		for (i=0; i<m_edges.count(); i++)
		{
			edge = m_edges.get_edge(i);
			
			node1=m_nodes.get_node(edge.nodefrom());  //(得到相应的点(起点id号))
			
			node2 = m_nodes.get_node(edge.nodeto());  //得到相应的点(终点id号)
			
			int r=random.nextInt(2);     //随机选择发送方向
			     
			if(r==0){
			         
			    	  sender=node1;
				      
			    	  receiver=node2;
			      }
		    if(r==1){
			    	  
			    	  sender=node2;
			    	  
			    	  receiver=node1;
			      }
			 
		   int packet_num=Minimum(sender.length(),(receiver.capacity()-receiver.length()),
				   edge.bandwidth());  //得到传递的包数量
		   
		   packet_num+=packet_num;
		   
		   edge.set_packetsum(packet_num); //记录边上包的流量
		   
		   sender.set_length(sender.length()-packet_num); 
		   
		   receiver.set_length(receiver.length()+packet_num);
		}
	}
	
	//求三个值中的最小值
	public int  Minimum(int sender_length,int receiver_capacity,int band_with){ //发送包的个数要小于这三个值
		
		int minimum=sender_length;
		
		if(receiver_capacity<minimum)
		
			minimum=receiver_capacity;
		
		if(band_with<minimum)
		
			minimum=band_with;
		
		minimum=random.nextInt(minimum);  //可能有问题（？？）
		
		return minimum;
	}
	
	void trace()
	{
	//save this step into trace file(保存的日志文件中)
		
	}
	
	void load()  //数据库——内存
	{
		// load edges;
		m_trace.Load_Edgetopo();
		// load nodes
		m_trace.Load_Nodetopo();
	}
	
	void save()  //内存——数据库
	{
		String database=null;   //初始化
		
		con=m_trace.Open_Database( database );  //打开数据库
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //保存节点结构
			
		m_trace.Save_EdgeTopo(con,m_edges);    //保存边结构
	}
 
	void run( int duration )  //运行
	{
		
		
		for (int t=0; t<duration; t++)
		{
			evolve();
		}
	}
	

	/**
	 * This defines the time resolution. Every time and time interval
	 * in the simulator is represented in this resolution. This rate
	 * corresponds to the 38.4 kbps speed, but maybe a little friendlier.
	 */
	//public static final int ONE_SECOND = 40000;
	
	/** Holds the events */
    //private JxBaseEventQueue eventQueue = new JxBaseEventQueue();
	
	/** The time of the last event using the given resolution */
	//long lastEventTime = 0; 
	/**
	 * @return Returns the time of the last event in 1/ONE_SECOND
	 */
	/**public long getSimulationTime() { 
		return lastEventTime;
	}*/

	/**
	 * @return Returns the time of the last event in milliseconds.
	 */
	/*public long getSimulationTimeInMillisec() {     
		return (long)(1000 * lastEventTime / (double)JxBaseSimulator.ONE_SECOND);
	}*/

	/**
	 * Adds an event to the event queue.
	 *  
	 * @param e the event to be added to the queue
	 */
	/**public void addEvent(JxBaseEvent e) {
		getEventQueue().add( e );        
	}*/

	/**
	 * Processes and executes the next event. 
	 */
	/**public void step() {
		JxBaseEvent event = (JxBaseEvent)getEventQueue().getAndRemoveFirst();
		if( event != null ){
			lastEventTime = event.time;
			//event.execute();
			trace();
		}
	}*/

	/**
	 * Processes and executes the next "n" event.
	 * 
	 * @param n the number of events to be processed
	 */
	/**public void step(int n) {
		for( int i=0; i<n; ++i )
			step();        
	}*/

	/**
	 * Runs the simulation for a given amount of time.
	 * 
	 * @param timeInSec the time in seconds until the simulation is to run
	 */
	/**public void run(double timeInSec) {         
		long tmax = lastEventTime + (int)(JxBaseSimulator.ONE_SECOND * timeInSec);                 
		while( lastEventTime < tmax )
	    {
	        JxBaseEvent event = (JxBaseEvent)getEventQueue().getAndRemoveFirst();
	        if( event != null ){
	            lastEventTime = event.time;
	            
	            
	            //event.execute(); 
	        }
	        else
	            break;
	    }
	}
*/
	

	/**
	 * Clears the List of nodes.
	 */
	/*public void clear() {
		m_nodes.clear();
		m_edges.clear();
	}*/

	/**
	 * Get the event queue
	 * @return eventQueue in the simulator.
	 */
	/*public JxBaseEventQueue getEventQueue() { //得到事件队列
		return eventQueue;
	}*/	
	
	
}

