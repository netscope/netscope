package extend.scalefree;

import java.util.ArrayList;

public class JxScaleFreeEdgeCollection extends ArrayList<JxScaleFreeEdge>{

	/** edge count in the list */
	public int count() {
		
		return super.size();
		
	}
<<<<<<< HEAD

	public boolean add( int nodefrom, int nodeto )
	{
		// todo: how to get the edge id? 
		int id = 9;
		JxScaleFreeEdge edge = new JxScaleFreeEdge(id,nodefrom, nodeto,0,0);
		//return super.add( edge );
		// todo
		return false;
	}

=======
	
	public boolean  add( int nodefrom, int nodeto ){
		
		JxScaleFreeEdge edge = new JxScaleFreeEdge(nodefrom, nodeto);
		
		return super.add( edge );
	}
>>>>>>> 5132d5ba41ba3e385b1f5813952847a36767b779
	public JxScaleFreeEdge get_edge(int id){ //得到相应的边
		return super.get(id); 
	}
	
	/** get JxScaleFreeEdge object at specified position with index */
	public JxScaleFreeEdge get(int index){ 
		return super.get(index); 
	}
	
	public JxScaleFreeEdge search(int id)
	{
		boolean found = false;
		JxScaleFreeEdge edge = null;
		for (int i=0; i<super.size(); i++)
		{
			edge = (JxScaleFreeEdge)this.get(i);
			if (edge.id() == id)
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
			edge = (JxScaleFreeEdge)this.get(i);
			if ((edge.nodefrom() == nodefrom) && (edge.nodeto() == nodeto))
			{
				found = true;
				break;
			}
		}
		return (found ? edge: null);
	}	
	
	public ArrayList<JxScaleFreeEdge> out_edges_of( int nodefrom )
	{
		
		return null;
	}
	
	public ArrayList<JxScaleFreeEdge> in_edges_of( int nodefrom )
	{
		return null;
	}
	
	public ArrayList<JxScaleFreeEdge> edges_of( int nodefrom )
	{
		
		return null;
	}
	
	public ArrayList<JxScaleFreeNode> neighbors_of( int nodefrom )
	{
	
		return null;
	}
	
	
}
