/**
 * 
 */
package extend.scalefree;
import java.util.*;
/**
 * @author Allen
 * 
 */
public class JxScaleFreeSimuApplication {
<<<<<<< HEAD
=======

<<<<<<< HEAD
	ArrayList<JxScaleFreeNode> JoinInNetNode;  //已加入网络的节点（组成的链表）
	
	public static Random random = new Random();

	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //点集合
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //边集合	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();   //保存结构	
	Statement sta=null;	
	Connection con=null;
=======
	ArrayList<JxScaleFreeNode> JoinInNetNode;
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
	 
	JxScaleFreeNodeCollection m_nodes;   
	
	JxScaleFreeEdgeCollection m_edges; 
	
	JxScaleFreeTrace m_trace; 
	
<<<<<<< HEAD
	Random random;
=======
	 Random random ;
	   
     Statement sta ;
		
	 Connection con ;
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
		
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
	
	    m_trace.Trace_Node( time ); //time为试验次数
	    
	    m_trace.Trace_Edge( time ); 
		
	}
    
    public void load(){   //数据库——内存
		
		m_trace.Load_Edgetopo(str);
		
		m_trace.Load_Nodetopo(str);
	}

	public void init(){  //初始化（产生拓扑结构）
	      
      m_trace.generate( 10 );   
	   
	  m_trace.Open_Database();    //打开数据库
		
	  m_trace.Save_NodeTopo(m_nodes.count());    //保存节点结构
		
	  m_trace.Save_EdgeTopo();    //保存边结构
	  		
	}
	
<<<<<<< HEAD
	public void exit(){
		
		m_trace.CloseDatabase();
		
=======
	public void init()  //初始化（产生拓扑结构）
	{
<<<<<<< HEAD
		String database=null;   //初始化
		
		con=m_trace.openDatabase( database );  //打开数据库
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //保存节点结构
=======
	      
	  generate( 10 );     
		    
	  save(); 
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
			
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
	}
<<<<<<< HEAD
 
	void run( int duration )  //运行
	{
		for (int t=0; t<duration; t++)
		{
=======

	public void run(int duration){  //运行
		
		for (int time=0; time<duration; time++){ 
		
<<<<<<< HEAD
		    m_trace.evolve();
=======
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
			evolve();
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
			
			trace(time);  //保存实验结果		
		}	
	}
<<<<<<< HEAD


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
=======
  
	public static void main(String[] args) {
		
		JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication();
		 
		app.init();
		
		app.run(10);
<<<<<<< HEAD
		
		System.out.println("success !");
=======
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42

	}
}

