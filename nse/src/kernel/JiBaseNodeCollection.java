package kernel;

import java.util.ArrayList;

public interface JiBaseNodeCollection {
	
	
	public void generate( int nodecount );
	public void setTrace( JiBaseTrace trace );
	
	public int count();
	
	public JiBaseNode get( int index );
	public JiBaseNode set( int id, JiBaseNode node );

	public boolean add( JiBaseNode node );
	public void add(int index, JiBaseNode node );

	public JiBaseNode remove( int idx );
	public boolean remove( JiBaseNode node );

	public int indexOf( JiBaseNode node );
	
	/** 
	 * Searching for the node object with specified identifier in the collection 
	 * list.
	 * 
	 * @param id
	 * @return
	 */
	public JiBaseNode search( int id );

	public void clear();
	
	/**
	 * Returns all the neighbor node around some node in a ArrayList. 
	 * @param node
	 * @return
	 */
	public ArrayList<JiBaseNode> neighborsof( JiBaseNode node );
	
	/**
	 * Returns all the neighbor node around some node in a ArrayList. 
	 * @param node
	 * @return
	 */
	public ArrayList<JiBaseNode> neighborsof( JiBaseNode node, int distance );
	
	/**
	 * Returns all the relation objects related to the specified node in a ArrayList.
	 * @param node
	 * @return
	 */
	public ArrayList<JiBaseRelation> relationsof( JiBaseNode node );
	
    public void randomize();
}
