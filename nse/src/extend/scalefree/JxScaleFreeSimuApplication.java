/**
 * 
 */
package extend.scalefree;

import kernel.basetrace.*;
import java.util.*;
import kernel.*;

  public class JxScaleFreeSimuApplication 
  { 
	    
	    JxScaleFreeRelationCollection  m_relations =  new JxScaleFreeRelationCollection();
		JxScaleFreeNodeCollection  m_nodes = new JxScaleFreeNodeCollection()  ;
        
	    
		JxScaleFreeRelation  m_relation = new JxScaleFreeRelation();
		JxScaleFreeNode  m_node = new JxScaleFreeNode();
		JxScaleFreeTrace  m_trace=new JxScaleFreeTrace(); 
		JxScaleFreeInteraction m_interaction=new JxScaleFreeInteraction();
		
		
		//JxBaseTraceLoader m_traceLoader = new JxBaseTraceLoader();
	    JxBaseEngine  m_engine = new JxBaseEngine();
	   
		JxScaleFreeSimuApplication()
		{
		   m_engine.setInteraction(new JxBaseInteraction(m_engine));
		   m_engine.setTrace(new JxBaseTrace(m_engine));    
		}
	
		public void open()
		{
	       m_trace.open(); 
		}
			
		public void close()
		{
		   m_trace.close();	
		}
	  
	    public void run(int expertime)
	    {           
		     for(int i=0;i<expertime;i++)
		     {
		    	 m_interaction.interact(time, relation, trace);
		     }	    	   
	    }
	    
	    public void generateNodes(int count)
	    {
	    	m_nodes.generate(count);
	    }
	    
	    public void generateRelations(int count)
	    {
	    	m_relations.generate(count);
	    }
	    
        public static void main(String []args)
        {	
    	    JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication(); 	    
    	    
    	    app.open();
    	    app.generateNodes(10);
    	    app.generateRelations(9);
    	    app.run(10);
    	    app.close();
    	    
    	   // app.m_traceLoader.loadnodes();
    	   // app.m_traceLoader.loadrelations(); 	   	   
    	    System.out.println("sucess!");
	    }     

		public  JxBaseNodeCollection getNodes()
		{
			return m_nodes;
		}
		
		public JxBaseRelationCollection getRelations()
		{
			return m_relations;
		}
	
    	void test1()
    	{   
    		JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication();
    		
    		app.m_nodes.generate(10);
    		app.m_relations.generate(9);
    		
    		app.m_interaction.interact(time, relation, trace)
    		
    		JxBaseEngine engine = new JxBaseEngine();
    		
    		engine.setNodes(new JxBaseNodeCollection(engine, 10)); 
    		engine.setRelations(new JxBaseRelationCollection(engine, engine.getNodes()));
    		engine.setInteraction(new JxBaseInteraction(engine));
    		engine.setTrace(new JxBaseTrace(engine, "/temp/expr/20110722-124512-01"));
    		
    		engine.execute(10000);
    	} 
    	/**
    	 * Reload the initial parameters from saved network trace.
    	 */
    	void test2() 
    	{   
    		JxBaseEngine engine = new JxBaseEngine();
            
    		engine.setTrace( new JxBaseTrace(engine, "/temp/expr/"));
    		
    		engine.load( engine.getNodes() );
    		engine.load( engine.getRelations() );
    		engine.setInteraction( new JxBaseInteraction(engine) );
    		engine.execute( 100000 );
           
    		/** restore */
    		engine.restore( "/temp/expr/20110722-124512-01" );
    	}
    	

    	void test3()
    	{
    		String traceclass, nodesclass, relationsclass, interactionclass;
    		
    		traceclass = "nse.kernel.JxBaseTrace";
    		nodesclass = "nse.kernel.JxBaseNodesCollection";
    		relationsclass = "nse.kernel.JxBaseRelationsCollection";
    		interactionclass = "nse.kernel.JiBaseInteraction";
    		
    		JxBaseEngine engine = new JxBaseEngine();
    	 // engine.open( nodesclass, relationsclass, interactionclass, traceclass );
    		engine.execute(10000);
    	}
    	
 }
	

