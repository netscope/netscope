package kernel;
import java.util.ArrayList;

import extend.scalefree.JxScaleFreeNode;
public class JxNodeCollection extends ArrayList<JiNode> {

	/** Node count in the collection 
	 * @return
	 */

	private static final long serialVersionUID = 1L;
	
	/** Node count in the collection */
	int count() {  //返回节点数
	 return super.size();
	}
	
    public JiNode get_node(int i) {	
	  return super.get(i);	
	}
	/** Get the node with specified index */
	public JiNode get(int index){
		return super.get(index);
	}
	
	public void add(int index, JiNode node ){
		super.add(index,node); 
	}
	
	public boolean add( JiNode node ){
		return super.add(node);
	}
	
	public int indexOf(JxScaleFreeNode node){
		return super.indexOf(node);
	}
	
	public JiNode search( int id ){
		
		boolean found = false;
		JiNode node = new JxStdNode();
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
