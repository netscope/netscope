 package kernel;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

//import nse.ns.wireless.JxNetworkSimulator;
//import nse.ns.wireless.JxRadioModel;

public class JxBaseSimulator {

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
	private JxBaseEventQueue2 eventQueue = new JxBaseEventQueue2();
	
	/** The time of the last event using the given resolution */
	long lastEventTime = 0;
	
	/**
	 * The nodes of the Simulator are linked together in a single linked list. 
	 * This points to the first, and then {@link JxNode#nextNode} to the next. 
	 */
	public JxBaseNode2 firstNode = null;
	/**
	 * Display to show the simulation progress. 
	 */
	JiBaseTrace m_trace = null;

	public JxBaseSimulator() {
		super();  //超类？？？
	}

	/**
	 * 
	 * @return Returns the time of the last event in 1/ONE_SECOND
	 */
	public long getSimulationTime() { //最后一个事件的时间
		return lastEventTime;
	}

	/**
	 * 
	 * @return Returns the time of the last event in milliseconds.
	 */
	public long getSimulationTimeInMillisec() {     //？？
		return (long)(1000 * lastEventTime / (double)JxBaseSimulator.ONE_SECOND);
	}

	/**
	 * Adds an event to the event queue.
	 *  
	 * @param e the event to be added to the queue
	 */
	public void addEvent(JxBaseEvent2 e) {
		getEventQueue().add( e );        
	}

	/**
	 * Processes and executes the next event. 
	 */
	public void step() {
		JxBaseEvent2 event = (JxBaseEvent2)getEventQueue().getAndRemoveFirst();
		if( event != null ){
			lastEventTime = event.time;//事件的时间
			event.execute();  //???
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
	        JxBaseEvent2 event = (JxBaseEvent2)getEventQueue().getAndRemoveFirst();
	        if( event != null ){
	            lastEventTime = event.time;
	            event.execute(); //
	        }
	        else
	            break;
	    }
	}

	/**
	 * This function runs the simulation with the display.
	 * The user of the simulator must first add all the nodes used in the 
	 * experiment!
	 * 带显示的仿真
	 */
	public void runWithTrace(JiBaseTrace trace) {
		//JiDisplayTrace trace = new JxBaseTrace( this, maxCoordinate );
		trace.show();    //显示    
		while( true ){
			step(100);
			trace.update();
		}
	}

	/**
	 * This function runs the simulation with the display and in real time.
	 * The user of the simulator must first add all the nodes used in the 
	 * experiment!
	 */
	/* old version -->
	public void runWithTraceInRealTime(JiBaseTrace trace) {  
		Thread t = new Thread(){
			public void run(){   
				JxBaseTrace trace = new JxDisplayTrace( this, maxCoordinate );
				trace.show();
	            
				long initDiff = System.currentTimeMillis() - getSimulationTimeInMillisec();
				while( true ){                
					step(100);
					trace.update();
					long diff = System.currentTimeMillis() - getSimulationTimeInMillisec();
					if( diff < initDiff ){
						try{
							sleep(initDiff-diff);
						}
						catch(Exception e){
						}
					}
				}
				
			}            
		};
		t.start();
	}
	--> */

	/**
	 * This function runs the simulation with the display and in real time.
	 * The user of the simulator must first add all the nodes used in the 
	 * experiment!
	 */
	public void runWithTraceInRealTime(JiBaseTrace trace) {  
		m_trace = trace;
		
		Thread t = new Thread(){
			public void run(){   
				//JxBaseTrace trace = new JxBaseTrace( this, maxCoordinate );
				m_trace.show();
	            
				long initDiff = System.currentTimeMillis() - getSimulationTimeInMillisec();
				while( true ){                
					step(100);
					m_trace.update();
					long diff = System.currentTimeMillis() - getSimulationTimeInMillisec();
					if( diff < initDiff ){
						try{
							sleep(initDiff-diff);
						}
						catch(Exception e){
						}
					}
				}
				
			}            
		};
		t.start();
	}

	/**
	 * Called by the {@link JxBaseTrace} whenever it is repainted, it calls the 
	 * {@link JxNode#trace} method on every nodes in its Node list.
	 * 
	 * @param disp the Display on which to draw Nodes
	 */
	public void trace(JiBaseTrace trace) {
		JxBaseNode2 tempNode = firstNode;
		while (tempNode != null){
			tempNode.trace(trace);
			tempNode = tempNode.nextNode;
		}
	}

	/**
	 * Adds a single Node to the simulator.
	 * 
	 * @param app the Node to be added
	 */
	protected void addNode(JxBaseNode2 node) {
		
		node.nextNode = firstNode;
		firstNode = node;
	}

	/**
	 * Adds a List of nodes to the experiment. 
	 * WARNING: Please call {@link JxRadioModel#updateNeighborhoods} after you 
	 * added all nodes to the system.
	 * 
	 * @param nodes the list of nodes to be added 
	 */
	public void addNodes(List nodes) {
		Iterator nodeIterator = nodes.iterator();
		while (nodeIterator.hasNext()){
			addNode((JxBaseNode2)nodeIterator.next()); 
		}
	}

	/**
	 * Clears the List of nodes.
	 */
	public void clear() {
		firstNode = null;
	}

	/**
	 * Get the event queue
	 * @return eventQueue in the simulator.
	 */
	public JxBaseEventQueue2 getEventQueue() {
		return eventQueue;
	}
	
	public JxBaseNode2 firstNode(){   
		return firstNode;
	}
}
