package extend.scalefree;

import kernel.*;
import java.util.ArrayList;

public class JxScaleFreeEdgeCollection extends JxBaseRelationCollection implements JiBaseRelationCollection
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JxScaleFreeEdgeCollection(Object  engine)
	{
		super(engine);
	}
	
    public void generate(int relationCount)
    { 
    	//JxBaseEngine engine = (JxBaseEngine)this.m_owner;
    	JxScaleFreeNodeCollection nodes = (JxScaleFreeNodeCollection)engine.getNodes();
    	JxScaleFreeNodeCollection leftnodes = new JxScaleFreeNodeCollection();
    	
    	for(int i=0;i<nodes.count();i++)
    	{
    		leftnodes.add( nodes.get(i) );
    	}
    	
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
    
	public int count() 
	{		
		return super.size();		
	}
	
   	public boolean  add( int nodefrom, int nodeto )
   	{	
		JxScaleFreeEdge edge = new JxScaleFreeEdge(nodefrom, nodeto);
		return super.add( edge );
	}
  
	
	/** get JxScaleFreeEdge object at specified position with index */
	public JxScaleFreeEdge get(int index) 
	{
		return super.get(index);
	}
	
	
	public JxScaleFreeEdge search(int id)
	{
		boolean found = false;
		JxScaleFreeEdge edge = null;
		for (int i=0; i<super.size(); i++)
		{
			edge = this.get(i);
			if (edge.getId() == id)
			{
				found = true;
				break;
			}
		}
		return (found ? edge: null);
	}
	
	
	public JxScaleFreeEdge search(int nodefrom, int nodeto)
	{
		boolean found = false;
		JxScaleFreeEdge edge = null;
		for (int i=0; i<super.size(); i++)
		{
			edge = this.get(i);
			if ((edge.getNodeFrom() == nodefrom) && (edge.getNodeTo() == nodeto))
			{
				found = true;
				break;
			}
		}
		return (found ? edge: null);
	}	
	
	
	public ArrayList<JxScaleFreeEdge> out_edges_of( int nodefrom ){		 		
		ArrayList<JxScaleFreeEdge> out_edge_list =new ArrayList<JxScaleFreeEdge>();
		JxScaleFreeEdge edge=null;
		for(int i=0;i<super.size();i++)
		{
		   edge=this.get(i);
		   if(edge.m_nodefrom==nodefrom)
		   {
			   
		   }
		}
		return null;
	}
	
	
	public ArrayList<JxScaleFreeEdge> in_edges_of( int nodeto ){
		return null;
	}
	
	
	public ArrayList<JxScaleFreeEdge> edges_of( int nodeid){
		return null;
	}
	
	
	public ArrayList<JxScaleFreeNode> neighbors_of( int nodeid ){
		return null;
	}
	
	
}
