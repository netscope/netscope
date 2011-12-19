package extend.scalefree;

import kernel.*;
import java.util.*;

  public class JxScaleFreeApplication 
  {  
	    JxBaseRelationCollection  m_relations = new JxBaseRelationCollection();
		JxBaseNodeCollection m_nodes = new JxBaseNodeCollection();
		
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
	    	m_trace.save(m_nodes);
	    }    
	    
	    public void generateRelations(int count)
	    {
	    	m_nodes.randomize();  //randomize the nodes
	    	
	    	for(int i=0;i<m_nodes.count();i++)
	    	{
	    	   m_leftnodes.add(i, m_nodes.get(i)); 
	    	}
	    	m_relations.generate(count);
    		
	    	for(int i=0;i<m_relations.count();i++)
	    	{
	    		JxBaseRelation relation = (JxBaseRelation)m_relations.get(i);
	    		
	    		JxBaseNode nodeFrom = new JxBaseNode();
	    		JxBaseNode nodeTo =new JxBaseNode();
	    		
	    		if(i==0)
	    		{   
	    		    nodeFrom =(JxBaseNode)m_nodes.get(0);
	    	        nodeTo =(JxScaleFreeNode)m_nodes.get(1);	
	    	        
	    	        nodeFrom.setNeighborNodes(nodeTo);
	  
	    			
	    			m_leftnodes.remove(nodeFrom);
	    			m_leftnodes.remove(nodeTo);		
	    		}
	    		
	    		else{
	    			nodeFrom =(JxScaleFreeNode)m_leftnodes.get(0);
	    			nodeTo =(JxScaleFreeNode)this.selectNodeTo();
	    			 
	    			m_leftnodes.remove(nodeFrom);
	    		}
	    		    relation.setNodeFrom(nodeFrom);
	    		    relation.setNodeTo(nodeTo);
	    		    
	    		    m_addnodes.add(nodeFrom);
    			    m_addnodes.add(nodeTo);	
    			    
    			    nodeFrom.setDegreeIn(nodeFrom.getDegreeIn()+1);    
    			    nodeTo.setDegreeIn(nodeTo.getDegreeIn()+1);     
	       }	
	    }
	  
	    public void generateTopo(int count)	    
	    {
	       	m_nodes.randomize();
	       	m_relations.generate(count);
	       	
	    	for(int i=0;i<6;i++)        //add the initial 6 nodes into the addnodes	      
	    	{
	       	  m_addnodes.add(m_nodes.get(i)); 
	       	}
	    	
	       	for(int i=6;i<m_nodes.count();i++) 
	       	{
	       	  m_leftnodes.add(m_nodes.get(i));	
	       	}
	       		        
 	      	for(int i=0;i<m_leftnodes.size()+i;i++)
	      	{
	      	   if(i==0)
	      	   {
	      		  JxBaseNode firstNodeFrom=(JxBaseNode)m_leftnodes.remove(0);
	      		  
	      		  for(int j=0;j<6;j++)
	      		  {
	                  JxBaseRelation relation=(JxBaseRelation)m_relations.get(j);
	      			  
	                  JxBaseNode nodeTo=(JxBaseNode)m_addnodes.get(j);
	                  
	                  relation.setNodeFrom(firstNodeFrom);
	      			  relation.setNodeTo(nodeTo);
	      			  
	      			  firstNodeFrom.setNeighborNodes(nodeTo); //set the neighborNodes
	      			  nodeTo.setNeighborNodes(firstNodeFrom); 
	      			  
	      			  firstNodeFrom.setNeighborRelations(relation);
	      			  nodeTo.setNeighborRelations(relation);
	      			  
	      			  m_trace.save(relation);
	      			  m_addnodes.add(firstNode);
	      		  }   		 
	      	   }
	      	   
	      	   else{
	      		    JxBaseNode nodefrom=(JxBaseNode)m_leftnodes.remove(0);
	      		    
	      		    ArrayList<JiBaseNode> neighborNodes=this.selectNodesTo();
	      		    
	      		    for(int j=0;j<6;j++)
	      		    { 
	      		      JxBaseRelation relation=(JxBaseRelation)m_relations.get(6*i+j);
	      		      relation.setNodeFrom(nodefrom);
	      		      
	      		      JxBaseNode nodeTo=(JxBaseNode)neighborNodes.get(j);
	      		      relation.setNodeTo(nodeTo);
	      		      
	      		      m_trace.save(relation);
	      		      
	      		      m_addnodes.add(nodefrom);
	      		      m_addnodes.add(nodeTo);
	      		    }    
	      	  }
	      	}
	    }
	   
	    public void run(int experTime)
	    {           
		   for(int i=0;i<experTime;i++)
		   { 
		      for(int j=0;j<m_relations.count();j++)
		      {
		         m_interaction.interact(i,m_relations.get(j), m_trace);	
		      }
		      
		      for(int k=0;k<m_nodes.count();k++)
		      {
		    	 m_trace.trace(i, m_nodes.get(k)); 
		      }
		   }	    	   
	    }    
	    
	    public void run1(int experTime)   //the number of is initial node is 6;
	    {                                 //there 6 relations for each new node,Each time(for each node) 
	       for(int i=0;i<experTime;i++)   //we choose one of them to do the packet exchanges 
	       {
	    	  for(int j=0;j<m_nodes.count();j++)
	    	  { 	
	        	int relationId =j*6+m_random.nextInt(6);
	
	        	JxBaseRelation relation=(JxBaseRelation)m_relations.get(relationId);
	    		
	        	JxBaseNode nodefrom = (JxBaseNode)relation.getNodeFrom();
	    		JxBaseNode nodeto =(JxBaseNode)relation.getNodeTo();
	    		
	    		int length1 = nodefrom.getLength();
		    	int length2 = nodeto.getLength();
		    	
		    	int totalength = length1+length2;
		    	int cut = 0;
		    	
		    	if(totalength!=0)
		    	{
		           cut = m_random.nextInt(nodefrom.getLength()+nodeto.getLength());
		    	}
		    	
		    	nodefrom.setLength(cut);
		    	nodeto.setLength(totalength-cut);
		    	
		    	m_trace.trace(i, relation);
	    	 }
	       }
	    }
	    
	    public JiBaseNode selectNodeTo()
	    {
	    	int p = m_random.nextInt(m_addnodes.size());
	    	return m_addnodes.get(p);
	    }
	    
	    public ArrayList<JiBaseNode> selectNodesTo()
	    {
	      	ArrayList <JiBaseNode> neighborNodes=new ArrayList<JiBaseNode>();
	      	int[] totalNode=new int[10000];
	      	for(int i=0;i<6;i++)
	      	{
	      		int nodeId;
	      		JxBaseNode nodeto=new JxBaseNode();
	      		do
	      		 {
	      		   int p=m_random.nextInt(m_addnodes.size());
	      		   nodeto=(JxBaseNode)m_addnodes.get(p); 
	      		   nodeId=nodeto.getId();	   
	      		 }while(totalNode[nodeId]==1);
	      		   neighborNodes.add(nodeto);
	      		   totalNode[nodeId]=1;
	      	}
	      	return neighborNodes;
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
	

