
package extend.scalefree;

import kernel.*;
import java.util.*;
  public class JxScaleFreeApplication 
  {  
	    JxBaseRelationCollection  m_relations = new JxBaseRelationCollection();
		JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();
		
		JxScaleFreeNodeCollection leftnodes=new JxScaleFreeNodeCollection();
		JxScaleFreeNodeCollection addnodes=new JxScaleFreeNodeCollection();
		

		JxBaseRelation  m_relation =  new JxBaseRelation();
		JxBaseNode  m_node = new JxBaseNode();
		JxBaseTrace  m_trace =new JxBaseTrace(); 
		
		Random m_random=new Random();
	    
		JxBaseInteraction m_interaction = new JxBaseInteraction();
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
		    	 for(int j=0;j<m_relations.count();i++)
		    	 {
		    		 m_interaction.interact(i, m_relations.get(j), m_trace);	
		    	 }    
		     }	    	   
	    }
	      
	    public void generateNodes(int count)
	    {
	    	m_nodes.generate(count);
	    }    
	    
	    public void generateRelations(int count)
	    {
	    	m_relations.generate(count);
	    	
	    	leftnodes=m_nodes;
	    			 
    		JxBaseRelation relation=new JxBaseRelation();
    		JxScaleFreeNode nodeFrom=new JxScaleFreeNode();
    		JxScaleFreeNode nodeTo=new JxScaleFreeNode();
    		
	    	for(int i=0;i<m_relations.count();i++)
	    	{
	    		relation = (JxBaseRelation)m_relations.get(i);
	    		
	    		if(i==0)
	    		{   
	    			nodeFrom=(JxScaleFreeNode)m_nodes.get(0);
	    			nodeTo=(JxScaleFreeNode)m_nodes.get(1);	
	    			
	    			leftnodes.remove(nodeFrom);
	    			leftnodes.remove(nodeTo);		
	    		}
	    		else{
	    			nodeFrom=(JxScaleFreeNode)leftnodes.get(0);
	    			nodeTo=(JxScaleFreeNode)selectNodeTo();
	    			 
	    			leftnodes.remove(nodeFrom);
	    		}
	    		    relation.setNodeFrom(nodeFrom);
	    		    relation.setNodeTo(nodeTo);
	    		    
	    		    addnodes.add(nodeFrom);
    			    addnodes.add(nodeTo);	
	       }	
	    }
	    
	    public JiBaseNode selectNodeTo()
	    {
	    	int p=m_random.nextInt(addnodes.count());
	    	return addnodes.get(p);
	    }
	    
	    public void saveNodes()
	    {
	    	m_trace.save(m_nodes);
	    }
	    
	    public void saveRelations()
	    {
	    	m_trace.save(m_relations);
	    }
	      
        public void setNodes(JiBaseNodeCollection nodes)
        {
        	m_nodes = (JxScaleFreeNodeCollection)nodes;
        }
        
		public  JxBaseNodeCollection getNodes()
		{
			return m_nodes;
		}
		
		public void setRelations(JxBaseRelationCollection relations)
		{ 
			m_relations = relations;
		}
		
		public JxBaseRelationCollection getRelations()
		{
			return m_relations;
		}	
 }
	

