package extend.scalefree;

import kernel.*;
import java.util.*;

  public class JxScaleFreeApplication 
  {  
	    JxBaseRelationCollection  m_relations = new JxBaseRelationCollection();
		JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();
		
		JxBaseInteraction m_interaction = new JxBaseInteraction();
		
		ArrayList<JiBaseNode> m_leftnodes = new ArrayList<JiBaseNode>();
		ArrayList<JiBaseNode> m_addnodes = new ArrayList<JiBaseNode>();

		JxBaseRelation  m_relation =  new JxBaseRelation();
		JxBaseTrace  m_trace = new JxBaseTrace();
		JxBaseNode  m_node = new JxBaseNode();
		
		Random m_random = new Random();
	    
		public void open()
		{
	       m_trace.open(); 
		}	
		
		public void close()
		{
		   m_trace.close();	
		}  
	      
	    public void generateNodes(int count)
	    {
	    	m_nodes.generate(count);
	    }    
	    
	    public void generateRelations(int count)
	    {
	    	m_nodes.randomize();                    //randomize the nodes
	    	for(int i=0;i<m_nodes.count();i++)
	    	{
	    	   m_leftnodes.add(i, m_nodes.get(i)); 
	    	}
	    	m_relations.generate(count);
	    	
    		JxBaseRelation relation = new JxBaseRelation();
    		JxScaleFreeNode nodeFrom = new JxScaleFreeNode();
    		JxScaleFreeNode nodeTo = new JxScaleFreeNode();
    		
	    	for(int i=0;i<m_relations.count();i++)
	    	{
	    		relation = (JxBaseRelation)m_relations.get(i);
	    		
	    		if(i==0)
	    		{   
	    			nodeFrom=(JxScaleFreeNode)m_nodes.get(0);
	    			nodeTo=(JxScaleFreeNode)m_nodes.get(1);	
	    			
	    			m_leftnodes.remove(nodeFrom);
	    			m_leftnodes.remove(nodeTo);		
	    		}
	    		else{
	    			nodeFrom=(JxScaleFreeNode)m_leftnodes.get(0);
	    			nodeTo=(JxScaleFreeNode)this.selectNodeTo();
	    			 
	    			m_leftnodes.remove(nodeFrom);
	    		}
	    		    relation.setNodeFrom(nodeFrom);
	    		    relation.setNodeTo(nodeTo);
	    		    
	    		    m_addnodes.add(nodeFrom);
    			    m_addnodes.add(nodeTo);	
    			    
    			    nodeFrom.setDegreeIn(nodeFrom.getDegreeIn()+1);    
    			    System.out.println("nodefromid is"+nodeFrom.getId());
    			    System.out.println("nodefrom degreein is"+nodeFrom.getDegreeIn());
    			    
    			    nodeTo.setDegreeIn(nodeTo.getDegreeIn()+1);
    			    System.out.println("nodetoid is"+nodeTo.getId());
    			    System.out.println("nodetodegreein is"+nodeTo.getDegreeIn());    
	       }	
	    }

	   
	    public void run(int expertime)
	    {           
		   for(int i=0;i<expertime;i++)
		   {
		      for(int j=0;j<m_relations.count();j++)
		      {
		    	m_interaction.interact(i, m_relations.get(j), m_trace);	
		      }
		      for(int k=0;k<m_nodes.count();k++)
		      {
		    	 m_trace.trace(i, m_nodes.get(k)); 
		      }
		   }	    	   
	    }
	    
	    public JiBaseNode selectNodeTo()
	    {
	    	int p = m_random.nextInt(m_addnodes.size());
	    	return m_addnodes.get(p);
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
	

