package kernel;

import java.util.Iterator;
import java.util.Random;

/**
 * JxBaseEngine
 * @author Allen
 * 
 *         The layer architecture of network net scope base network simulation:
 * 
 *         - Top Layer: Extended Layer (for user application) -
 *          Middle Layer:
 *         JxBaseEngine: The developer can create there own simulators by
 *         extending this class, or build their own. JxBaseNode,
 *         JxBaseNodeCollection, JxBaseRelation, JxBaseRelationCollection,
 *         JxBaseInteraction: They're the basic building blocks for a simulator.
 *         The JxBaseEngine is built on them. JiBaseNode, JiBaseNodeCollection,
 *         JiBaseRelation, JiBaseRelationCollection, JiBaseInteraction: Provides
 *         fundamental interface for future extension. 
 *         - Bottom Layer:
 *         JxFoundation: Provides the context and some commonly used components
 *         such as simulation time, random number generator, events, event queue
 *         and event dispatcher.
 * 
 *         reference - White paper develop mentor: Understanding Class.forName,
 *         Loading classes dynamically from within extensions,
 *         http://media.techtarget
 *         .com/tss/static/articles/content/dm_classForname/DynLoad.pdf -
 *         JAVA反射机制的学习, 2007, http://hejianjie.iteye.com/blog/136205; -
 *         读取XML动态创建Java类，并调用方法, 2010,
 *         http://blog.csdn.net/bjhecwq/article/details/5872960; - Java
 *         Class.forName(String className) and JDBC,
 *         http://cephas.net/blog/2005/
 *         07/31/java-classfornamestring-classname-and-jdbc/ - 深入了解Java
 *         ClassLoader、Bytecode 、ASM、cglib, http://www.iteye.com/topic/98178 -
 *         如何利用反射实现 动态生成一个对象（假如不存在类文件）？http://www.iteye.com/topic/7721
 */
    public class JxBaseEngine 
   {
	Object m_owner = null;
	
	String datadir=null;
 
	JxBaseFoundation m_base = JxBaseFoundation.getSingleInstance();

	/** Node collection. It contains all the nodes */
	private JiBaseNodeCollection m_nodes = null;

	/** Relation collection. It contains all the relations between nodes */
	private JiBaseRelationCollection m_relations = null;

	/**
	 * Define the interactive rule between nodes. It's actually associate with
	 * the relation object
	 */
	private JxBaseInteraction m_interaction = null;

	/** For trace output */
	private JxBaseTrace m_trace = null;

	public JxBaseEngine()
	{
		m_nodes = new JxBaseNodeCollection();
		m_relations = new JxBaseRelationCollection();
		m_interaction = new JxBaseInteraction();
		m_trace = new JxBaseTrace();
		m_owner = null;
	}

	/**
	 * @brief Initialize the whole engine for execution.
	 * 
	 * @attention The difference between the construction function of an object
	 *            and the open initialization function is that: the construction
	 *            function contains memory operations only and it should always
	 *            be success. While, in the open function, you can do with the
	 *            I/O initializations such as open an file for R/W or start a
	 *            network connection for TX/RX. These operation may failed.
	 * 
	 * @param None
	 * @return true indicate initialization success and false indicate failed.
	 *         This function should return true or else the later execute() will
	 *         stop.
	 */
	public boolean open(JiBaseNodeCollection nodes,JiBaseRelationCollection relations, JiBaseInteraction interaction, JiBaseTrace trace)
	{
		m_nodes = nodes;
		m_relations = relations;
		m_interaction = (JxBaseInteraction)interaction;
		m_trace = (JxBaseTrace)trace;

		m_nodes.setTrace(m_trace);
		m_relations.setTrace(m_trace);
		m_interaction.setTrace(m_trace);

		//打开数据库
		m_trace.open();

		//生成边
		m_nodes.generate(10000);
		m_relations.generate();
	    
		/**保存点-边*/
		m_trace.save(m_nodes);
		m_trace.save(m_relations);
                                     
		return true;
	}

	/**
	 * Initialize the base simulation engine with the default settings.
	 * 
	 * @param tracedir  The folder used for saving trace record files.
	 * @example if (open( "/temp/expr/" )) { do something here }
	 * @return
	 */
	public boolean open(String datadir)
	{
		/** 产生10000个节点  */
		JiBaseNodeCollection nodes = new JxBaseNodeCollection(this, 10000);
		Class nodesclass=nodes.getClass();
	    String nodesname=nodesclass.getName();
	        
		JiBaseRelationCollection relations = new JxBaseRelationCollection(this,nodes);
		Class relationsclass=nodes.getClass();
		String relationsname=relationsclass.getName();
         
		
		JiBaseInteraction interaction = new JxBaseInteraction(this);
	    Class interactionclass=nodes.getClass();
	    String interactionname=relationsclass.getName();
	    
		JiBaseTrace trace = new JxBaseTrace(this, datadir);
		Class traceclass=nodes.getClass();
		String tracename=relationsclass.getName();
		
		/**  */
		return open( nodesname,relationsname,interactionname, tracename );
	}

	/**  */
	boolean open(String nodesname, String relationsname, String interactionname,String tracename)
	{
		m_trace = null;
		m_nodes = null;
		m_relations = null;
		m_interaction = null;

		try {
			  m_trace = (JxBaseTrace) JxBaseFoundation.createObject(tracename);
			  m_nodes = (JiBaseNodeCollection) JxBaseFoundation.createObject(nodesname);
			
			  m_relations = (JiBaseRelationCollection) JxBaseFoundation.createObject(relationsname);
			  m_interaction = (JxBaseInteraction) JxBaseFoundation.createObject(interactionname);
		} catch (Exception e) 
		{
			 m_trace = null;
			 m_nodes = null;
			 m_relations = null;
			 m_interaction = null;
		}
		   m_nodes.setTrace(m_trace);
		   m_relations.setTrace(m_trace);
		   m_interaction.setTrace(m_trace);
		   
		   return true;
	}

	/**
	 * @brief Release resources allocated in open() function.
	 */
	public void close() 
	{
		m_trace.close();
	}
    
	/** 所有的边做一次相互作用  */
	public void step(int time) 
	{
		m_relations.randomize();
		
		JxBaseRelationCollection relations=(JxBaseRelationCollection)m_relations;
		Iterator<JiBaseRelation> it = relations.iterator();

		while (it.hasNext())
		{
			JiBaseRelation relation = (JiBaseRelation) it.next();
			m_interaction.interact(time, relation, m_trace);
		}
	}

	public void execute(int stepcount)
	{
		if (open(datadir)) 
		{
			for (int i = 0; i < stepcount; i++)
				step(i);
			    close();
		}
		else{
			  m_trace.open();
		     for (int i = 0; i < stepcount; i++)
			    step(i);
		        close();
		}
		System.runFinalization();
	}

	public Random getRandom() 
	{
		return JxBaseFoundation.random();
	}

	public JiBaseNodeCollection getNodes() 
	{
		return m_nodes;
	}
	public void setNodes(JiBaseNodeCollection nodes) 
	{
		m_nodes = nodes;
	}

	
	public JiBaseRelationCollection getRelations()
	{
		return m_relations;
	}
	public void setRelations(JiBaseRelationCollection relations) 
	{
		m_relations = relations;
	}

	public JiBaseInteraction getInteraction() 
	{
		return m_interaction;
	}
	public void setInteraction(JiBaseInteraction interaction) 
	{
		m_interaction = (JxBaseInteraction) interaction;
	}

	public JiBaseTrace getTrace()
	{
		return m_trace;
	}
	public void setTrace(JiBaseTrace trace) 
	{
		m_trace = (JxBaseTrace) trace;
	}

	/**
	 * Returns the node count in the simulated application. Actually you can get
	 * this information from the node collection object returned by getNodes().
	 * 
	 * @return Node count in the simulated application.
	 */
	int nodecount() 
	{
		return m_nodes.count();
	}

	/**
	 * Add an node object in the node set in the simulated application.
	 * 
	 * @param node
	 */
	public void addNode(JiBaseNode node) 
	{
		m_nodes.add(node);
		m_trace.save(node);
	}

	/**
	 * Add an relation object in the relation set in the simulated application.
	 * 
	 * @param relation
	 */
	public void addRelation(JiBaseRelation relation) 
	{
		m_relations.add(relation);
		m_trace.save(relation);
	}
    
	/**
	 * Load nodes and relation configurations from database saved before.
	 * 
	 * @param dbname
	 */
	public void load(JiBaseNodeCollection nodes)
	{
		
	}
	
	public void load(JiBaseRelationCollection relations)
	{
		
	}
	public void restore(String dbname) 
    {
		// load class name from database
		String traceclass, nodesclass, relationsclass, interactionclass;

		traceclass = "nse.kernel.JxBaseTrace";
		nodesclass = "nse.kernel.JxBaseNodesCollection";
		relationsclass = "nse.kernel.JxBaseRelationsCollection";
		interactionclass = "nse.kernel.JiBaseInteraction";

		JiBaseTrace trace = null;
		JiBaseNodeCollection nodes = null;
		JiBaseRelationCollection relations = null;
		JiBaseInteraction interaction = null;

	   try {
			trace = (JiBaseTrace) JxBaseFoundation.createObject(traceclass);
			nodes = (JiBaseNodeCollection) JxBaseFoundation.createObject(nodesclass);
			relations = (JiBaseRelationCollection) JxBaseFoundation.createObject(relationsclass);
			interaction = (JiBaseInteraction) JxBaseFoundation.createObject(interactionclass);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		  setTrace(trace);
		  setNodes(nodes);
		  setRelations(relations);
		  setInteraction(interaction);
		    
		  int time=0;
		  
		  trace.restore(time,dbname, nodes, relations);
    }

}
