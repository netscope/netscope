package extend.scalefree;

import kernel.JiBaseNodeCollection;
import java.util.*;
import kernel.*;

/**
 * This class maintains the collection of all nodes. 
 * @author Allen
 */

public class JxScaleFreeNodeCollection extends JxBaseNodeCollection implements JiBaseNodeCollection {	

	private static final long serialVersionUID = 1L;
    
	JxBaseEngine m_engine=new JxBaseEngine();
	
	public JxScaleFreeNodeCollection()
	{
		
	}

	public void generate( int count )
	{
	    for (int i=0; i<count; i++)
	    {
	    	Random random=m_engine.getRandom();
	    	
	    	/** this value should be modified*/
	    	int loc_x = random.nextInt(100);
	    	int loc_y = random.nextInt(100);
	    	
	    	super.add( new JxScaleFreeNode(this,i,loc_x,loc_y,100));	
	    }		
	}
	
	/** Get the node with specified index */
	public JiBaseNode get(int index)
	{
		return super.get(index);
	}
	
	public void add(int index, JxScaleFreeNode node)
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
	
	public JxScaleFreeNode search(int id)
	{
		boolean found = false;
		JxScaleFreeNode node = null;
		
		for (int i=0; i<super.size(); i++)
		{
			node = (JxScaleFreeNode)this.get(i);	
			if (node.getId() == id)
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
