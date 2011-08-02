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
public class JxBaseRelationCollection extends ArrayList<JiBaseRelation> implements JiBaseRelationCollection {
	
	private static final long serialVersionUID = 1L;
	
	Object m_owner = null;
	JxBaseNodeCollection m_nodes = null;
	JxBaseTrace m_trace = null;
	Random m_random = JxBaseFoundation.random();

	JxBaseRelationCollection() 
	{
		m_owner = null;
		m_nodes = null;
		m_trace = null;
	}
	
	JxBaseRelationCollection( Object owner, JiBaseNodeCollection nodes, JiBaseTrace trace  )
	{
		m_owner = owner;
		m_nodes = (JxBaseNodeCollection)nodes;
		m_trace = (JxBaseTrace)trace;
	}
	
	JxBaseRelationCollection( Object owner, JiBaseNodeCollection nodes )
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
	
	/** 
	 * Generate relation objects into the relation collection. These objects 
	 * may describe the network topology as a graph, but it's not mandatory to 
	 * do so.
	 */
	public void generate( int count )
	{
		JxBaseEngine engine = (JxBaseEngine)m_owner;
		Random random = JxBaseFoundation.random();
		JxBaseTrace trace = (JxBaseTrace)engine.getTrace();	
	}
	
	public void generate()
	{
		this.generate(1000);
	}

	public JiBaseRelation search(int id)
	{
		boolean found = false;
		JiBaseRelation  relation = new JxBaseRelation();
		for (int i=0; i<super.size(); i++){
			relation = this.get(i);
			if (relation.getId()== id){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}
	
	public int search(JiBaseRelation relation){
		return -1;
	}

	public JiBaseRelation search(JiBaseNode nodefrom, JiBaseNode nodeto){
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
	
	/** 得到与节点相关的所有边*/
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
		
	}
 }
