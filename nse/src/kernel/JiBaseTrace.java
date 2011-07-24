package kernel;

/** 
 * Trace 
 * @author Allen
 */
public interface JiBaseTrace {
  
	public Object getOwner();
	public Object setOwner();

	public void open(String datadir );
	public void open();
	public void close();
	
	public void save( JiBaseNode node );
	//public void save( JiBaseNodeCollection nodes );
	public void save( JiBaseRelation relation );
	//public void save( JiBaseRelationCollection relations );
	//public void load( JiBaseNodeCollection nodes );
	//public void load( JiBaseRelationCollection relations );
	public void trace( JiBaseNode node );
	public void trace( JiBaseRelation relation );
	
	/**
	 * Take a snapshot at the current network status, and save all of them into database, 
	 * including node collection, and relation collection. 
	 * 
	 * @param nodes
	 * @param relations
	 */
	public void snapshot( JiBaseNodeCollection nodes, JiBaseRelationCollection relations );
  
    /**
     * Restore nodes, relations information from trace file saved previously.
     * @param dbname
     */
    public void restore( String datadir, JiBaseNodeCollection nodes, JiBaseRelationCollection relations );
    
    //public void update();
    //public void show();
} 
