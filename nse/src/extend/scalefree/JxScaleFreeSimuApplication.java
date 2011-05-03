/**
 * 
 */
package extend.scalefree;
import java.util.*;
import java.util.Random;
import kernel.JxBaseEvent;
import kernel.JxBaseEventQueue;
import kernel.JxBaseSimulator;
/**
 * @author Allen
 *
 */
public class JxScaleFreeSimuApplication {

	ArrayList<JxScaleFreeNode> JoinInNetNode ;  //已加入网络的节点（组成的链表）
	/** 
	 * This is a static reference to a Random instance.
	 * This makes experiments repeatable, all you have to do is to set
	 * the seed of this Random class. 
	 */
	public static Random random = new Random();
	
	/**
	 * This defines the time resolution. Every time and time interval
	 * in the simulator is represented in this resolution. This rate
	 * corresponds to the 38.4 kbps speed, but maybe a little friendlier.
	 */
	public static final int ONE_SECOND = 40000;
	
	/** Holds the events */
	private JxBaseEventQueue eventQueue = new JxBaseEventQueue();
	
	/** The time of the last event using the given resolution */
	long lastEventTime = 0; 
	
	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //点集合
	
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //边集合
	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();
	
	void init()  //初始化
	{
     // m_trace.open("database"); //打开数据库
		generate( 10000 );
	}
	
	void generate( int nodecount )   //产生拓扑
	{		
		int i, x, y;
		JxScaleFreeEdge edge;   //边
		JxScaleFreeNode node;   //接点
		
		
		for (i=0; i<10000; i++){
			x = random.nextInt(100);
			y = random.nextInt(100);
			m_nodes.add( new JxScaleFreeNode( x, y, 100 ));//添加新节点（100——容量）			
		}
	
        // Create edge and add them into the edge collection.
		
		    node = m_nodes.get(0);  //第一个点(作为初始点)                
	        JoinInNetNode.add(node);   
	        node.setDegree(1);      //（实时更新接点的度）
		
	    for (i = 1; i<m_nodes.count(); i++)//(将节点号为1-9999的节点依次加入网络)
		{   
			
	    	JxScaleFreeNode cur_node=new JxScaleFreeNode();
			JxScaleFreeNode select_node=new JxScaleFreeNode();
		  
			
		    cur_node = m_nodes.get(i);       //当前节点
		  
			select_node =selectnodeto(i-1);  //选择下一节点
            
			JoinInNetNode.add(cur_node);     //当前点加入网络
			cur_node.setDegree(cur_node.degree()+1);//当前点的度加1
			
			JoinInNetNode.add(select_node); //选中点加入网络
			select_node.setDegree(select_node.degree()+1);//选中点的度加1
			
			edge = new JxScaleFreeEdge( cur_node.get_nodeid(),select_node.get_nodeid(), 10, 0 ); //新边(起点，终点，带宽，权值)
			m_edges.add(edge);
		}
	}
	
	protected JxScaleFreeNode selectnodeto(int b) { //a=0,b=i-1,p
		
		int i; 
		int p = random.nextInt(JoinInNetNode.size());//生成在0——列表长度之间的整数值
	   
		return JoinInNetNode.get(p); //返回选中点
	    
	}
	
	
	
	void evolve()
	{
		int i;
		JxScaleFreeNode node1, node2;
		
		for (i=0; i<m_nodes.count(); i++)
		{
			node1 = m_nodes.get(i);
			node2 = select_neighbor( node1 );
			
			// move some packets from node1 to node2
			
			// trace
		}
	}
	
	
	void load()
	{
		// load edges;
		// load nodes
	}
	
	void save()
	{
		// save edges;
		// save nodes		
	}
 
	void run( int duration )  //运行
	{
		for (int t=0; t<duration; t++)
		{
			evolve();
		}
	}

	/**
	 * @return Returns the time of the last event in 1/ONE_SECOND
	 */
	public long getSimulationTime() { 
		return lastEventTime;
	}

	/**
	 * @return Returns the time of the last event in milliseconds.
	 */
	public long getSimulationTimeInMillisec() {     
		return (long)(1000 * lastEventTime / (double)JxBaseSimulator.ONE_SECOND);
	}

	/**
	 * Adds an event to the event queue.
	 *  
	 * @param e the event to be added to the queue
	 */
	public void addEvent(JxBaseEvent e) {
		getEventQueue().add( e );        
	}

	/**
	 * Processes and executes the next event. 
	 */
	public void step() {
		JxBaseEvent event = (JxBaseEvent)getEventQueue().getAndRemoveFirst();
		if( event != null ){
			lastEventTime = event.time;
			//event.execute();
			trace();
		}
	}

	/**
	 * Processes and executes the next "n" event.
	 * 
	 * @param n the number of events to be processed
	 */
	public void step(int n) {
		for( int i=0; i<n; ++i )
			step();        
	}

	/**
	 * Runs the simulation for a given amount of time.
	 * 
	 * @param timeInSec the time in seconds until the simulation is to run
	 */
	public void run(double timeInSec) {         
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

	void trace()
	{
		//save this step into trace file
	}

	/**
	 * Clears the List of nodes.
	 */
	public void clear() {
		m_nodes.clear();
		m_edges.clear();
	}

	/**
	 * Get the event queue
	 * @return eventQueue in the simulator.
	 */
	public JxBaseEventQueue getEventQueue() { //得到事件队列
		return eventQueue;
	}	
}

