/**
 * 
 */
package extend.scalefree;

import java.util.Random;

import kernel.JxBaseEvent;
import kernel.JxBaseEventQueue;
import kernel.JxBaseSimulator;

/**
 * @author Allen
 *
 */
public class JxScaleFreeSimuApplication {
	
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
	
	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();
	
	void init()
	{
		// m_trace.open("database");
		generate( 10000 );
	}
	
	void generate( int nodecount )
	{		
		int i, x, y;
		JxScaleFreeEdge e;
		JxScaleFreeNode node;
		
		// Create nodes and add them into the node collection. 
		for (i=0; i<10000; i++)
		{
			x = random.nextInt(100);
			y = random.nextInt(100);
			m_nodes.add( new JxScaleFreeNode( x, y, 100 ) );			
		}
	
		// Create edge and add them into the edge collection.
		
		node = m_nodes.get(0);
		node.setDegree(1);
		for (i = 1; i<m_nodes.count(); i++)
		{
			cur = m_nodes.get(i);
			p = random.nextFloat( 1.0 );
			selected = select(0, i-1, p);
			e = new JxScaleFreeEdge( cur, selected, 10, 0 );
		}
	}
	
	void evolve()
	{
		int i;
		JxScaleFreeNode node1, node2;
		
		for (i=0; i<m_nodes.count(); i++)
		{
			node1 = m_nodes.get(i);
			node2 = select_neighbot( node1 );
			
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

	void run( int duration )
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
	public JxBaseEventQueue getEventQueue() {
		return eventQueue;
	}
	
		
}
