package extend.scalefree;

import java.util.ArrayList;

public class JxScaleFreeNodeCollection extends ArrayList<JxScaleFreeNode>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6512692821729075017L;

	int count() {
		return super.size();
	}
	
	public JxScaleFreeNode get(int index)
	{
		return super.get(index);
	}
	
	public void add(int index, JxScaleFreeNode node )
	{
		super.add(node); 
	}
	
	public int indexOf(JxScaleFreeNode node)
	{
		return super.indexOf(node);
	}
	
	public JxScaleFreeNode search( int id )
	{
		// todo
		return null;
	}
	
	public void clear()
	{
		super.clear();
	}
}
