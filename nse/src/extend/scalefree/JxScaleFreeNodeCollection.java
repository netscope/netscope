package extend.scalefree;
import java.util.ArrayList;

/**
 * This class maintains the collection of all nodes. 
 * @author Allen
 */
public class JxScaleFreeNodeCollection extends ArrayList<JxScaleFreeNode>{

	private static final long serialVersionUID = 1L;
    
	//ArrayList<JxScaleFreeNode> nodelist; 
	
	/** Node count in the collection */
	int count() {  
		return super.size();
	}
	
	/** Get the node with specified index */
	public JxScaleFreeNode get(int index)
	{
		return super.get(index);
	}
	
	public void add(int index, JxScaleFreeNode node )
	{
		super.add(index,node); 
	}
	
	public boolean add( JxScaleFreeNode node )
	{
		return super.add(node);
	}
	
	/*
	public int add( JxScaleFreeNode node )
	{
		// @todo
		return -1;
	}
	*/
	
	public int indexOf(JxScaleFreeNode node)
	{
		return super.indexOf(node);
	}
	
	public JxScaleFreeNode search( int id )
	{
		boolean found = false;
		JxScaleFreeNode node = null;
		for (int i=0; i<super.size(); i++)
		{
			node = this.get(i);
			if (node.id() == id)
			{
				found = true;
				break;
			}
		}
		return (found ? node : null);
	}
	
	/** Clear all the nodes and release resources allocate for them */
	public void clear() 
	{
		for (int i=0; i<super.size(); i++)
		{
			super.set(i, null);
		}
		super.clear();
	}
}
