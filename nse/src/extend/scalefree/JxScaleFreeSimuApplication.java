/**
 * 
 */
package extend.scalefree;

import kernel.basetrace.*;
import java.util.*;
import kernel.*;

  public class JxScaleFreeSimuApplication 
  {  
	    
	    JxBaseEngine  m_engine = new JxBaseEngine();

	    JxBaseTraceLoader m_traceLoader = new JxBaseTraceLoader();
	    
	    JxBaseRelationCollection  m_relations =  new JxBaseRelationCollection();
		JxBaseNodeCollection  m_nodes = new JxBaseNodeCollection();
		
		
		ArrayList<JiBaseNode>  leftnodes=new ArrayList();
		ArrayList<JiBaseNode>  addednodes=new ArrayList();

		Random m_random = m_engine.getRandom();
	    
		JiBaseNode m_nodeFrom = new JxBaseNode();
		JiBaseNode m_nodeTo = new JxBaseNode();
	
	    JxBaseNodeCollection  m_leftnodes = new JxBaseNodeCollection();
		JxBaseNodeCollection  m_addednodes = new JxBaseNodeCollection();
		
		JxScaleFreeSimuApplication()
		{
		   m_engine.setInteraction(new JxBaseInteraction(m_engine));
		   m_engine.setTrace(new JxBaseTrace(m_engine));    
		}
		
		public void init()
		{
		  	
		}
		
		public void open()
		{
	       m_engine.open(); 
		}
			
		public void close()
		{
		   m_engine.close();	
		}
	 
	    
	    JiBaseNode selectnodeto() 
	    {    	
			int p = m_random.nextInt(m_addednodes.count()); 
			return m_addednodes.get(p);  
	    }
	     
	    public void run()
	    {           
		     m_engine.execute(2);		    	   
	    }
	    
        public static void main(String []args)
        {	
    	    JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication(); 	    
    	    
    	    app.init();
    	    app.open();
            
    	   // app.generateNodes(10);
    	    //app.generateRelations(9);
    	    
    	    app.run();

    	    
    	    app.m_traceLoader.loadnodes();
    	    app.m_traceLoader.loadrelations(); 	   		

    	    app.close();
    	   
    	    System.out.println("sucess!");
	    }     
        
    	void test1()
    	{
    		JxBaseEngine engine = new JxBaseEngine();
    		
    		engine.setNodes(new JxBaseNodeCollection(engine, 10)); 
    		engine.setRelations(new JxBaseRelationCollection(engine, engine.getNodes()));
    		engine.setInteraction(new JxBaseInteraction(engine));
    		engine.setTrace(new JxBaseTrace(engine, "/temp/expr/20110722-124512-01"));
    		
    		engine.execute(10000);
    	}        
 }
	

