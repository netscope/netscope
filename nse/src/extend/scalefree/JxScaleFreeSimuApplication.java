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
		
	    JxBaseEngine  m_engine = new JxBaseEngine();
	  
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
 }
	

