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

	Statement sta=null;	
	Connection con=null;

	 String str;

	
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
			

			edge = m_edges.get_edge(randomrank(m_edges.count()).get(i)); //随机得到一条边
			
			sender = m_nodes.search(edge.nodefrom()); 
			
			receiver = m_nodes.search(edge.nodeto()); 
			
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

		 str = null;
>>>>>>> 5132d5ba41ba3e385b1f5813952847a36767b779
	 
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
	

	public void init()  //初始化（产生拓扑结构）
	{
		String database=null;   //初始化
		/* todo
		con=m_trace.openDatabase( database );  //打开数据库
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //保存节点结构
		*/
	      
	  generate( 10 );     	    
	  save(); 

		String database=null;   //初始化
		
		sta=m_trace.openDatabase( database );  //打开数据库
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //保存节点结构
		
	    topo.generate( 10 );     
		    
	    topo.save(); 	

	}

	public void run(int duration)
	{  //运行
		
		for (int time=0; time<duration; time++){ 

			evolve();
			trace(time);  //保存实验结果		
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
  
	public static void main2(String[] args) {


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

