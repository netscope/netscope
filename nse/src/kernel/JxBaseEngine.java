package kernel;

import java.util.Random;

public class JxBaseEngine {

	/** 
	 * This is a static reference to a Random instance.
	 * This makes experiments repeatable, all you have to do is to set
	 * the seed of this Random class. 
	 */
	public static Random m_random = new Random();

	/** Node collection. It contains all the nodes */
	private JxBaseNodeCollection m_nodes = null;

	/** Relation collection. It contains all the relations between nodes */
	private JxBaseRelationCollection m_relations = null;
	
	/** Define the interactive rule between nodes. It's actually associate with the relation object */	
	private JxBaseInteraction m_interaction = null;
	
	/** For trace output */
	private JxBaseTrace m_trace = null;

	public JxBaseEngine()
	{
		m_nodes = new JxBaseNodeCollection();
		m_relations = new JxBaseRelationCollection();
		m_interaction = null;
		m_trace = null;		
    }
	
	/**
	 * @brief Initialize the whole engine for execution.
	 *  
	 * @attention
	 * The difference between the construction function of an object and the open 
	 * initialization function is that: the construction function contains memory 
	 * operations only and it should always be success. While, in the open function, 
	 * you can do with the I/O initializations such as open an file for R/W or start 
	 * a network connection for TX/RX. These operation may failed.
	 * 
	 * @param None
	 * @return true indicate initialization success and false indicate failed. This
	 * 		function should return true or else the later execute() will stop. 
	 */
	public boolean open( JiBaseInteraction interaction, JiBaseTrace trace )
	{
		m_interaction = (JxBaseInteraction) interaction;
		m_trace = (JxBaseTrace) trace;
		
		m_trace.open();
		
	/*	m_nodes.setTrace(trace);
		m_relations.setTrace(trace);
		m_interaction.setTrace(trace);
    */	
		
		m_relations.generate();
		
		m_trace.save( m_nodes );
		m_trace.save( m_relations );
		
		return true;
	}
	
	/**
	 * @brief Release resources allocated in open() function.  
	 */
	public void close()
	{
		m_trace.close();
	}
	
	public void step()
	{
		m_relations.randomize();
		
		// todo should be randomized sequence                                                                                                                        
		Iterator it = m_relations.iterator();
		while (it.hasNext())
		{
			JiBaseRelation relation = (JiBaseRelation)it.next();
	
			// The relation object keeps a list of nodes. They interact together
			// according to the regulation defined in the interaction object.
			
			m_interaction.interact( relation, m_trace );
		}
	}
	
	public void execute( int stepcount, JiBaseTrace trace )
	{
		JiBaseInteraction interaction = new JxBaseInteraction();
		
		if (open(interaction, trace))
		{
			for (int i=0; i<stepcount; i++)
				step();
			
			    close();
		}
		
		// So We call system.runFinalization() in the engine to force
		// the JVM to call finalize() of each revoked objects.
		System.runFinalization();
	}	
	
	public Random random()
	{
		return m_random;
	}
  
	JxBaseNodeCollection getNodes()
	{
		return m_nodes;
	}
	
	JxBaseRelationCollection getRelations()
	{
		return m_relations;
	}
	
	JiBaseInteraction getInteraction()
	{
		return m_interaction;
	}
	
	JiBaseTrace getTrace()
	{
		return m_trace;
	}
	
	/** 
	 * Returns the node count in the simulated application. Actually you can get
	 * this information from the node collection object returned by getNodes().
	 * 
	 * @param None
	 * @return Node count in the simulated application.
	 */
	int nodecount()
	{
		return m_nodes.size();
	}
	
	/**
	 * Add an node object in the node set in the simulated application.
	 * @param node
	 */
	public void addNode( JiBaseNode node )
	{
		m_nodes.add( node );
		m_trace.trace( node );
	}

	/**
	 * Add an relation object in the relation set in the simulated application.
	 * @param relation
	 */
	public void addRelation( JiBaseRelation relation )
	{
		m_relations.add( relation );
		m_trace.trace( relation );
	}
}
