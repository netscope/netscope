package kernel;
import java.util.ArrayList;
import extend.scalefree.JxScaleFreeEdge;
import extend.scalefree.JxScaleFreeNode;
public class JxEdgeCollection extends ArrayList<JiRelation>{
	
	private static final long serialVersionUID = 1L;

	
	public int count() 
	{		
		return super.size();		
	}
	
	  
	public boolean add(JiRelation relation)
	{
	 return	super.add(relation);
	}
   
	public JiRelation get_edge(int id)//得到相应的边
	{ 
		return super.get(id); 
	}
	
	
	/** get JxScaleFreeEdge object at specified position with index */
	public JiRelation get(int index){ 
		return super.get(index); 
	}
	
	
	public JiRelation search(int id){
		boolean found = false;
		JiRelation  relation = new JxStdRelation();
		for (int i=0; i<super.size(); i++){
			relation = this.get(i);
			if (relation.getId()== id){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}
	
	
	public JiRelation search(int nodefrom, int nodeto){
		
		boolean found = false;
		
		JiRelation relation = new JxStdRelation();
		
		for (int i=0; i<super.size(); i++){
			
			relation = this.get(i);
			
			 if ((relation.getNodeFrom() == nodefrom) && (relation.getNodeTo() == nodeto)){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}	
	
	
	public ArrayList<JiRelation> in_edges_of( int nodefrom ){
		
		ArrayList<JiRelation> in_edge_list =new ArrayList<JiRelation>();
		JiRelation relation=new JxStdRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom){
			  in_edge_list.add(relation);
		   }   	 
		}
		return in_edge_list;
	}
	
	
	public ArrayList<JiRelation> out_edges_of( int nodefrom ){
		
		ArrayList<JiRelation> out_edge_list =new ArrayList<JiRelation>();
		
		JiRelation relation=new JxStdRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom){
			  out_edge_list.add(relation);
		   }   	 
		}
		return out_edge_list;
	}
	
	
	public ArrayList<JiRelation> edges_of( int nodefrom,int nodeto ){
   
		ArrayList<JiRelation> edge_list =new ArrayList<JiRelation>();
		JiRelation relation=new JxStdRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom||relation.getNodeTo()==nodeto){
			  edge_list.add(relation);
		   }   	 
		}
		return edge_list;
	}
	
}
