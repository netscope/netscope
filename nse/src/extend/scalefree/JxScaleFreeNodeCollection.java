package extend.scalefree;

import kernel.JiBaseNodeCollection;
import kernel.JxBaseNode;

import java.util.ArrayList;
import kernel.*;

/**
 * This class maintains the collection of all nodes. 
 * @author Allen
 */
//public class JxScaleFreeNodeCollection extends ArrayList<JxScaleFreeNode> implements JiBaseNodeCollection {
public class JxScaleFreeNodeCollection extends JxBaseNodeCollection implements JiBaseNodeCollection {	

	private static final long serialVersionUID = 1L;
    
	public JxScaleFreeNodeCollection()
	{
		
	}

	public void generate( int count )
	{
	    for (int i=0; i<count; i++)
		super.add( new JxScaleFreeNode(this, i) );		
	}
	

	
	
	

	JxScaleFreeNode get_node(int i) 
	{		
	   return super.get(i);		
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
