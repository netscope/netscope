package kernel;

import java.util.ArrayList;
import java.util.Random;

/**
 * The JxBaseNodeCollection maintains a node set. You can add/remove node into/from 
 * this set.
 *  
 * @author Allen
 *
 */
public class JxBaseNodeCollection extends ArrayList<JiBaseNode> implements JiBaseNodeCollection {
    
	private static final long serialVersionUID = 1L;
	
	Object m_owner;
	
	JiBaseTrace m_trace = null;
	Random m_random = JxBaseFoundation.random();
	
	JxBaseNodeCollection( )
	{
		m_owner = null;
	}
	
	JxBaseNodeCollection( Object owner, int count )
	{
		m_owner = owner;
		generate( count );
	}
	
	public void generate( int count )
	{
		// generate count nodes and add them into the collection
		
		for (int i=0; i<count; i++)
			super.add( new JxBaseNode(this, i) );		
	}
	
	public void setTrace( JiBaseTrace trace )
	{
		m_trace = trace;
	}
	
	/** Node count in the collection 
	 *  @return
	 */
	public int count() 
	{  
	 return super.size();
	}
	
	/** Get the node at specified index */
	@Override
	public JiBaseNode get(int index)
	{
		return super.get(index);
	}
	
	//@Override
	public JiBaseNode set( int idx, JiBaseNode node )
	{
		return super.set( idx, node );
	}
	
	@Override
	public boolean add( JiBaseNode node ){
		return super.add(node);
	}
	
	@Override
	public void add(int index, JiBaseNode node ){
		super.add(index,node); 
	}
	
	@Override
	public JiBaseNode remove( int idx )
	{
		return super.remove(idx);
	}
	
	public boolean remove( JiBaseNode node )
	{
		return super.remove(node);
	}
	
	/**
	 * Get the position of the specified node in the collection list.
	 * @param node
	 * @return
	 */
	public int indexOf(JiBaseNode node){
		return super.indexOf(node);
	}
	
	/** 
	 * Searching for the node object with specified identifier in the collection 
	 * list.
	 * 
	 * @param id
	 * @return
	 */
	public JiBaseNode search( int id ){
		
		boolean found = false;
		JiBaseNode node = null;
		for (int i=0; i<super.size(); i++){
			node = this.get(i);
			if (this.get(i).getId() == id){
				found = true;
				break;
			}
		}
		return (found ? node : null);
	}
	
	/** Clear all the nodes and release resources allocate for them */
	@Override
	public void clear() {
	  for (int i=0; i<super.size(); i++){		
	    super.set(i, null);
	  }
		super.clear();
	}
	
	/**
	 * Returns all the neighbor node around some node in a ArrayList. 
	 * @param node
	 * @return
	 */
	public ArrayList<JiBaseNode> neighborsof( JiBaseNode node )
	{
		ArrayList<JiBaseNode> neighbors = new ArrayList<JiBaseNode>();
		
		return neighbors;
	} 
	
	/**
	 * Returns all the neighbor node around some node in a ArrayList. 
	 * @param node
	 * @return
	 */
	public ArrayList<JiBaseNode> neighborsof( JiBaseNode node, int distance )
	{
		ArrayList<JiBaseNode> neighbors = new ArrayList<JiBaseNode>();

		return neighbors;
	}
	
	/**
	 * Returns all the relation objects related to the specified node in a ArrayList.
	 * @param node
	 * @return
	 */
	public ArrayList<JiBaseRelation> relationsof( JiBaseNode node )
	{
		ArrayList<JiBaseRelation> relations = new ArrayList<JiBaseRelation>();
		return relations;
	}	
	
    public void randomize()
    {
    
    }	
}
