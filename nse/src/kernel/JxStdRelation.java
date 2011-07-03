package kernel;
public class JxStdRelation implements JiRelation {
   
	int relation_id; //±ß
	JiRelationType type;
	int nodefrom;
	int nodeto;
	
	public int getId(){
	  return relation_id;
   }
	public void setId(int id){
		relation_id=id; 	
   }
	
	
	public JiRelationType getType(){
		return  type;
	}
	public void setType(JiRelationType type){
		this.type=type;	
	}
	
	
	public int getNodeFrom(){
		return nodefrom;
	}
	public void setNodeFrom (int nodefrom){
		this.nodefrom=nodefrom;
	}
	
	
	public int getNodeTo(){
		return nodeto;
	}
	public void setNodeTo(int nodeto){
		this.nodeto=nodeto;	
	}
	
}
