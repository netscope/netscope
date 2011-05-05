package extend.scalefree;
import java.util.ArrayList;
public class JxScaleFreeNodeCollection extends ArrayList<JxScaleFreeNode>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//private static final long serialVersionUID = 6512692821729075017L; 
    //ArrayList<JxScaleFreeNode> nodelist; 
	int count() {  //返回节点数
		
		return super.size();
	}
	
	public JxScaleFreeNode get_node(int index) //得到索引号为index的节点
	{
		return super.get(index);
	}
	
	public void add(int index, JxScaleFreeNode node )//添加节点
	{
		super.add(index,node); 
	}
	
	public int indexOf(JxScaleFreeNode node)//指定字符第一次出现时的索引
	{
		return super.indexOf(node);
	}
	
	public JxScaleFreeNode search( int id )
	{
	  
		return null;
	}
	
	public void clear()  //清除节点
	{
		super.clear();
	}
}
