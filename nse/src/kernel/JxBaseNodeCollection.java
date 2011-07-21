package kernel;
import java.util.ArrayList;

import extend.scalefree.JxScaleFreeNode;
public class JxBaseNodeCollection extends ArrayList<JiBaseNode> {

	/** Node count in the collection 
	 *  @return
	 */
	private static final long serialVersionUID = 1L;
	
	/** Node count in the collection */
	int count() {  //返回节点数
	 return super.size();
	}
	
	/** Get the node with specified index */
	public JiBaseNode get(int index){
		return super.get(index);
	}
	
	public boolean add( JiBaseNode node ){
		return super.add(node);
	}
	
	public void add(int index, JiBaseNode node ){
		super.add(index,node); 
	}
	
	public int indexOf(JiBaseNode node){
		return super.indexOf(node);
	}
	
	public JiBaseNode search( int id ){
		
		boolean found = false;
		JiBaseNode node = new JxBaseNode();
		for (int i=0; i<super.size(); i++){
			node = this.get(i);
			if (node.getId() == id){
				found = true;
				break;
			}
		}
		return (found ? node : null);
	}
	
	/** Clear all the nodes and release resources allocate for them */
	public void clear() {
	  for (int i=0; i<super.size(); i++){		
	    super.set(i, null);
	  }
		super.clear();
	}
}
