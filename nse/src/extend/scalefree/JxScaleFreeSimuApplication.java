/**
 * 
 */
package extend.scalefree;
import java.sql.Connection;
import java.util.*;
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

	 String str;
	 
	int packet_num ;
	//初始化
	public JxScaleFreeSimuApplication(){
		  
   	     JoinInNetNode = new ArrayList<JxScaleFreeNode>();  //已加入网络的节点（组成的链表）
		
		 m_nodes = new JxScaleFreeNodeCollection(); //点集合(创建，保存)
	  
		 m_edges = new JxScaleFreeEdgeCollection(); //边集合(创建，保存)
		 
		 m_trace = new JxScaleFreeTrace(); //保存结构
		 
		 random = new Random();
		   
	     sta = null;
		
	     con = null;
			
		 str = null;
		 
	}
	
	public void generate( int nodecount ){ //产生拓扑
			
		  int i,x,y;   
		
		  JxScaleFreeEdge edge=new JxScaleFreeEdge();   //边   
		
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
		 
		    if(i==0){  //第一次直接将第一个节点加入网络
		       
		    	cur_node=m_nodes.get(randomrank(nodecount).get(0));
		    	
		    	JoinInNetNode.add(cur_node); 
		    	 
		    	cur_node.set_degree(cur_node.degree()+1); 
		    }
		    else {
		    	
			cur_node = m_nodes.get(randomrank(nodecount).get(i));//((所有节点重新随机排列一遍)返回其中的第i个值
		    
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
	
	//生成含有0-n个连续不重复数的数列	
   public  ArrayList<Integer>  randomrank(int n){  
		
	   ArrayList<Integer>  RandomList = new ArrayList<Integer>(); //保存1-n个随机排列的数
	   
	   boolean [] exist=new boolean[n];
	  
	   int number;
	   
	   for(int i=0;i<n;i++){	   
		
	  do{
			
	      number =random.nextInt(n);
		
		} while(exist[number]); 
		   
	      exist[number]=true;
	   
		  RandomList.add(number);
	   }
	   
	   return  RandomList;
	}
   
	//每一时刻同一边上的相邻节点交换包（包的个数随机）   
	public void evolve(){
    try{
		JxScaleFreeEdge edge;
		
		JxScaleFreeNode sender, receiver;
		
		int packet_num=0;  //记录边上的包流量

		for (int i=0; i<m_edges.count(); i++){
			
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
	 
	  return  mini;  
 }
    public	void trace(int time) {
	
	    m_trace.Trace_Node( time ); //time为试验次数
	    
	    m_trace.Trace_Edge( time ); 
		
	}
    
    public void load(){ //数据库——内存

		// load edges;
		
		m_trace.Load_Edgetopo(str);
		
		// load nodes
		
		m_trace.Load_Nodetopo(str);
	}
	 
	public void save(){//内存——数据库
	  
		m_trace.Save_NodeTopo();    //保存节点结构
		
		m_trace.Save_EdgeTopo();    //保存边结构
	}
	
	public void init()  //初始化（产生拓扑结构）
	{
		String database=null;   //初始化
		/* todo
		con=m_trace.openDatabase( database );  //打开数据库
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //保存节点结构
		*/
	      
	  generate( 10 );     	    
	  save(); 
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
		
		JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication();
		 
		app.init();
		
		app.run(10);
	}
}

