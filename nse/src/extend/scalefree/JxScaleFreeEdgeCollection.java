package extend.scalefree;
import java.util.ArrayList;
public class JxScaleFreeEdgeCollection extends ArrayList<JxScaleFreeEdge>{
	/**
	 * 
	 */
	//JxscaleFreeEdge edge=new JxscaleFreeEdge();
	public int count() {
		return super.size();//计算边数
	}
	public JxScaleFreeEdge get_edge(int id){ //得到相应的边
		return super.get(id); 
	}
}
