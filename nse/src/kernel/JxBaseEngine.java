package kernel;

import java.util.Iterator;
import java.util.Random;

/**
 * JxBaseEngine
 * @author Allen
 *
 * reference
 * - White paper developmentor: Understanding Class.forName, Loading classes dynamically
 *   from within extensions, 
 *   http://media.techtarget.com/tss/static/articles/content/dm_classForname/DynLoad.pdf
 * - JAVA反射机制的学习, 2007, http://hejianjie.iteye.com/blog/136205;
 * - 读取XML动态创建Java类，并调用方法, 2010, http://blog.csdn.net/bjhecwq/article/details/5872960;
 * - Java Class.forName(String className) and JDBC, 
 *   http://cephas.net/blog/2005/07/31/java-classfornamestring-classname-and-jdbc/
 * - 深入了解Java ClassLoader、Bytecode 、ASM、cglib, http://www.iteye.com/topic/98178
 * - 如何利用反射实现 动态生成一个对象（假如不存在类文件）？http://www.iteye.com/topic/7721  
 */
public class JxBaseEngine {
	
	Object m_owner = null;

	/** 
	 * This is a static reference to a Random instance.
	 * This makes experiments repeatable, all you have to do is to set
	 * the seed of this Random class. 
	 */
	public static Random m_random = new Random();

	/** Node collection. It contains all the nodes */
	private JiBaseNodeCollection m_nodes = null;

	/** Relation collection. It contains all the relations between nodes */
	private JiBaseRelationCollection m_relations = null;
	
	/** Define the interactive rule between nodes. It's actually assicitate with the relation object */	
	private JiBaseInteraction m_interaction = null;
	
	/** For trace output */
	private JiBaseTrace m_trace = null;

	public JxBaseEngine()
	{
		m_owner = null;
		m_nodes = null;
		m_relations = null;
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
	public boolean open( JiBaseNodeCollection nodes, JiBaseRelationCollection relations,  
		JiBaseInteraction interaction, JiBaseTrace trace )
	{
		m_nodes = nodes;
		m_relations = relations;
		m_interaction = interaction;
		m_trace = trace;
		
		trace.open();
		m_nodes.setTrace(trace);
		m_relations.setTrace(trace);
		m_interaction.setTrace(trace);
		
		m_relations.generate();
		
		m_trace.save( m_nodes );
		m_trace.save( m_relations );
		
		return true;
	}
	
	/**
	 * Initialize the base simulation engine with the default settings.
	 * 
	 * @param tracedir The folder used for saving trace record files.
	 * @return
	 * 
	 * @example
	 * 		if (open( "/temp/expr/" ))
	 * 		{
	 * 			do something here
	 * 		}
	 */
	public boolean open( String datadir )
	{
/*		
		// load class name from database 
		String traceclass, nodesclass, relationsclass, interactionclass;
		
		traceclass = "nse.kernel.JxBaseTrace";
		nodesclass = "nse.kernel.JxBaseNodesCollection";
		relationsclass = "nse.kernel.JxBaseRelationsCollection";
		interactionclass = "nse.kernel.JiBaseInteraction";
		
		JiBaseTrace trace = (JiBaseTrace)createObject( traceclass );
		JiBaseNodeCollection nodes = (JiBaseNodeCollection)createObject( nodesclass );
		JiBaseRelationCollection relations = (JiBaseRelationCollection)createObject( relationsclass );
		JiBaseInteraction = (JiBaseInteraction)createObject( interactionclass );
*/
		
		JiBaseNodeCollection nodes = new JxBaseNodeCollection(this, 10000);
		JiBaseRelationCollection relations = new JxBaseRelationCollection(this, nodes);
		JiBaseInteraction interaction = new JxBaseInteraction(this);
		JiBaseTrace trace = new JxBaseTrace(this, datadir);
				
		return this.open( nodes, relations, interaction, trace );
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
		Iterator<JiBaseRelation> it = m_relations.iterator();
		while (it.hasNext())
		{
			JiBaseRelation relation = (JiBaseRelation )it.next();
	
			// The relation object keeps a list of nodes. They interact together
			// according to the regulation defined in the interaction object.
			
			m_interaction.interact( relation, m_trace );
		}
	}
	
	public void execute( int stepcount )
	{
		m_trace.open();
		for (int i=0; i<stepcount; i++)
			step();
		m_trace.close();
		
		// So We call system.runFinalization() in the engine to force
		// the JVM to call finalize() of each revoked objects.
		System.runFinalization();
	}	
	
	public Random random()
	{
		return m_random;
	}
  
	public JiBaseNodeCollection getNodes()
	{
		return m_nodes;
	}
	
	public void setNodes( JiBaseNodeCollection nodes )
	{
		m_nodes = nodes;
	}
	
	public JiBaseRelationCollection getRelations()
	{
		return m_relations;
	}
	
	public void setRelations( JiBaseRelationCollection relations )
	{
		m_relations = relations;
	}
	
	public JiBaseInteraction getInteraction()
	{
		return m_interaction;
	}
	
	public void setInteraction( JiBaseInteraction interaction )
	{
		m_interaction = interaction;
	}
	
	public JiBaseTrace getTrace()
	{
		return m_trace;
	}
	
	public void setTrace( JiBaseTrace trace )
	{
		m_trace = trace;
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
	
	/**
	 * Load nodes and relation configurations from database saved before.
	 * 
	 * @param dbname
	 */
	public void restore( String dbname )
	{
		// load class name from database 
		String traceclass, nodesclass, relationsclass, interactionclass;
		
		traceclass = "nse.kernel.JxBaseTrace";
		nodesclass = "nse.kernel.JxBaseNodesCollection";
		relationsclass = "nse.kernel.JxBaseRelationsCollection";
		interactionclass = "nse.kernel.JiBaseInteraction";
		
		JiBaseTrace trace = (JiBaseTrace)createObject( traceclass );
		JiBaseNodeCollection nodes = (JiBaseNodeCollection)createObject( nodesclass );
		JiBaseRelationCollection relations = (JiBaseRelationCollection)createObject( relationsclass );
		JiBaseInteraction = (JiBaseInteraction)createObject( interactionclass );
		
		this.setTrace( trace );
		this.setNodes( nodes );
		this.setRelations( relations );
		this.setInteraction( interaction );

		trace.restore( dbname, nodes, relations );
	}

	/**
	 * Creates an object from class name.
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * 
	 * @example
	 * 	Class.forName("jdbc.DriverXYZ");
	 * 	Connection con = DriverManager.getConnection(url, "myLogin", "myPassword");
	 */
	@SuppressWarnings("rawtypes")
	public Object createObject(String className)  
		throws ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		Class c = Class.forName(className);  
		return c.newInstance();  
	}  	
}
