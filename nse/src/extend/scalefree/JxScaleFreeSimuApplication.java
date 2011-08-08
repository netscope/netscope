/**
 * 
 */
package extend.scalefree;
import java.util.*;

import kernel.JxBaseEngine;
import kernel.JxBaseInteraction;
import kernel.JxBaseNodeCollection;
import kernel.JxBaseRelationCollection;
import kernel.JxBaseRelation;
import kernel.JiBaseNode;
import kernel.JxBaseNode;
import kernel.JxBaseTrace;

  public class JxScaleFreeSimuApplication 
  {  
	    JxBaseEngine  m_engine = new JxBaseEngine();
	    
	   JxBaseRelationCollection  m_relations =  new JxBaseRelationCollection();
		JxBaseNodeCollection  m_nodes = new JxBaseNodeCollection();
		
	    JxBaseNodeCollection  m_leftnodes = new JxBaseNodeCollection();
		JxBaseNodeCollection  m_addednodes = new JxBaseNodeCollection();
		
		Random m_random = m_engine.getRandom();
	
		JiBaseNode m_nodeFrom = null;
		JiBaseNode m_nodeTo = null;
		
		String m_datadir = null;
		
		public void open()
		{
	       m_engine.open();  
		}
			
		public void close()
		{
		   m_engine.close();	
		}
		
	    public void generateNodes(int nodeCount)
	    {
	    	for(int i=0;i<nodeCount;i++)
	    	{
	          int loc_x= m_random.nextInt(100);
	          int loc_y= m_random.nextInt(100);
	         
	          JiBaseNode node=new JxBaseNode(m_engine,i,loc_x,loc_y,50);
	         
	          m_engine.addNode(node);  
	          m_engine.save(node);
	    	}  
	    }
	    
	    public void generateRelations(int relationCount)
	    { 
	    	 m_relations.generate(relationCount);
	    	 m_relations.randomize(); 
	    	 m_engine.setRelations(m_relations); 
	    	 
	    	 m_leftnodes=(JxBaseNodeCollection)m_engine.getNodes();
	    	 m_leftnodes.randomize();
	    	 
		     for(int i=0;i<m_relations.count();i++)
			 { 
		    	JxBaseRelation relation=(JxBaseRelation)m_engine.getRelations().get(i);	
		    	 
		    	  if(i==0)
		          {
		    	    m_nodeFrom = m_leftnodes.get(0);
		    	    m_nodeTo=m_leftnodes.get(1);
		    	  
		    	    relation.setNodeFrom( m_nodeFrom );
		    	    relation.setNodeTo( m_nodeTo );
		    	  
		    	    m_leftnodes.remove( m_nodeFrom );
		    	    m_leftnodes.remove( m_nodeTo );
		    	  
		    	    m_addednodes.add( m_nodeFrom ); 
		    	    m_addednodes.add( m_nodeTo );         	    
	              }
	    	      else 
	    	      { 
		    	    m_nodeFrom  = m_leftnodes.get(0);
		    	    m_nodeTo  = selectnodeto();
		    	  
		    	    relation.setNodeFrom(m_nodeFrom);
		    	    relation.setNodeTo(m_nodeTo);
		    	  
		     	    m_addednodes.add( m_nodeFrom ); 
		     	    m_addednodes.add( m_nodeTo ); 
		     	  
		     	    m_leftnodes.remove(m_nodeFrom);
		         } 
		    	  m_engine.saveRelation(relation);  
		    }     
	    } 
	    
	    JiBaseNode selectnodeto() 
	    {    	
			int p = m_random.nextInt(m_addednodes.count()); 
			return m_addednodes.get(p);  
	    }
	    		
	    public void run()
	    {        
	         m_engine.setInteraction(new JxBaseInteraction(m_engine));
		     m_engine.setTrace(new JxBaseTrace(m_engine, "/temp/expr/20110722-124512-01"));
		
		     m_engine.execute(1000);	    	    
	    }
	    
        public static void main(String []args)
        {	
    	    JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication(); 	    
    	    
    	    app.open();
            
    	    app.generateNodes(10000);
    	    app.generateRelations(999);
    	   
    	    app.run();
    	    
    	    app.close();
    	    System.out.println("sucess!");
	    }     	   		
 }
	

