/**
 * 
 */
package extend.scalefree;

import kernel.basetrace.*;
import java.util.*;
import kernel.*;

  public class JxScaleFreeApplication 
  {  
	    
	    JxBaseApplication  m_engine = new JxBaseApplication();

	    public JxBaseTraceLoader m_traceLoader = new JxBaseTraceLoader();
	    
	    JxBaseRelationCollection  m_relations =  new JxBaseRelationCollection();
		JxBaseNodeCollection  m_nodes = new JxBaseNodeCollection();
		
		
		ArrayList<JiBaseNode>  leftnodes=new ArrayList();
		ArrayList<JiBaseNode>  addednodes=new ArrayList();

		Random m_random = m_engine.getRandom();
	    
		JiBaseNode m_nodeFrom = new JxBaseNode();
		JiBaseNode m_nodeTo = new JxBaseNode();
	
	    JxBaseNodeCollection  m_leftnodes = new JxBaseNodeCollection();
		JxBaseNodeCollection  m_addednodes = new JxBaseNodeCollection();
		
		JxScaleFreeApplication()
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
	    
    	void test1()
    	{
    		JxBaseApplication engine = new JxBaseApplication();
    		
    		engine.setNodes(new JxBaseNodeCollection(engine, 10)); 
    		engine.setRelations(new JxBaseRelationCollection(engine, engine.getNodes()));
    		engine.setInteraction(new JxBaseInteraction(engine));
    		engine.setTrace(new JxBaseTrace(engine, "/temp/expr/20110722-124512-01"));
    		
    		engine.execute(10000);
    	}        
 }
	

