package kernel;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class maintains a list of JiBaseRelation object. You can add/remove a relation
 * object into/from this collection.
 *
 * @author Allen
 *
 */
public class JxBaseRelationCollection extends ArrayList<JiBaseRelation> implements JiBaseRelationCollection 
{
	private static final long serialVersionUID = 1L;
	
	Random m_random = JxBaseFoundation.random();
	
	public Object m_owner = null;
	public JxBaseTrace m_trace = null;
	public JxBaseRelation m_relation = null;
	public JxBaseNodeCollection m_nodes = null;
	
	public JxBaseRelationCollection() 
	{
		m_owner = null;
		m_nodes = null;
		m_trace = null;
	}
	

	public JxBaseRelationCollection(Object owner) 
	{
		m_owner = owner;
		m_nodes = null;
		m_trace = null;
	}
	
	public JxBaseRelationCollection( Object owner, JiBaseNodeCollection nodes, JiBaseTrace trace )
	{
		m_nodes = (JxBaseNodeCollection)nodes;
		m_trace = (JxBaseTrace)trace;
		m_owner = owner;		
	}
	
	public JxBaseRelationCollection( Object owner, JiBaseNodeCollection nodes )
	{
		m_owner = owner;
		m_nodes = (JxBaseNodeCollection)nodes;
	}
	
	public void setTrace( JiBaseTrace trace )
	{
		m_trace = (JxBaseTrace)trace;
	}
	
    public int count() 
	{		
	  return super.size();		
	}
	  
	public boolean add(JiBaseRelation relation)
	{
	  return super.add(relation);
	}
	

	/** get JxScaleFreeEdge object at specified position with index */
	public JiBaseRelation get(int index)
	{ 
	  return super.get(index); 
	}
	
	public JiBaseRelation set(int index,JiBaseRelation relation)
	{
		return super.set(index, relation);
	}	
	  
	public Random getRandom()
	{
		return m_random;
	}
	/** 
	 * Generate relation objects into the relation collection. These objects 
	 * may describe the network topology as a graph, but it's not mandatory to 
	 * do so.
	 */
	public void generate( int count )
	{   
	      for(int i=0;i<count;i++)
	      {   
	 		super.add( new JxBaseRelation(i));	
	      }
	}
	
	public JiBaseRelation search(int id)
	{
		boolean found = false;
		JiBaseRelation  relation = new JxBaseRelation();
		for (int i=0; i<super.size(); i++)
		{
			relation = this.get(i);
			if (relation.getId()== id)
			{
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}
	
	public int search(JiBaseRelation relation)
	{
		return -1;
	}

	public JiBaseRelation search(JiBaseNode nodefrom, JiBaseNode nodeto)
	{
		return null;
/*		
		boolean found = false;
		
		JiBaseRelation relation = new JxBaseRelation();
		
		for (int i=0; i<super.size(); i++){
			
			relation = this.get(i);
			
			 if ((relation.getNodeFrom() == nodefrom) && (relation.getNodeTo() == nodeto)){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
*/		
	}	
	
	/** */
	public ArrayList<JiBaseRelation> getNodeAllRelations( JiBaseNode node )
	{
		return null;
	}
	
	public ArrayList<JiBaseRelation> getNodeOutRelations( JiBaseNode node )
	{
		return null;
	}
	
	public ArrayList<JiBaseRelation> getNodeInRelations( JiBaseNode node )
	{
		return null;
	}
	
    public void randomize()
	{  
    	int count=this.count();
		for(int i=0;i<this.count();i++)
		{ 
			int temp=m_random.nextInt(count-i)+i;
			JiBaseRelation tempRelation=this.get(temp);	
			
			this.set(temp, this.get(i));	
			this.set(i, tempRelation);
		}	
	}
    
    public void clear()
    {
    	super.clear();
    }
 }
