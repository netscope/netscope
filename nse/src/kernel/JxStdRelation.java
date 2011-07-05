package kernel;
import java.util.*;

import extend.scalefree.JxScaleFreeEdge;
import extend.scalefree.JxScaleFreeNode;
public class JxStdRelation implements JiRelation {
	
	
	private Object m_owner;
	private int m_relation_id; 
	private JiRelationType m_type;
	private int m_nodefrom;
	private int  m_nodeto;
	private int m_bandwidth;
	private int m_weight;
	private int m_packetsum;

	Random random= new Random();
    JxEdgeCollection edgeCollection=new JxEdgeCollection();
    JxNodeCollection nodeCollection=new JxNodeCollection();
    JiNode node=new JxStdNode();
    JiRelation relation= new JxStdRelation();
    
    ArrayList<Integer> addedSet = new ArrayList<Integer>();
    ArrayList<Integer> leftset = new ArrayList<Integer>();
    
    public JxStdRelation(){
		this.m_owner = null;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwidth = 0;
		this.m_weight = 0;
	}
	
	public JxStdRelation( Object owner ){
		this.m_owner = owner;
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwidth = 0;
		this.m_weight = 0;
	}

    public JxStdRelation (int nodefrom,int nodeto){
     	super();
    	this.m_nodefrom = nodefrom;
		this.m_nodeto = nodeto;	
    }

    public JxStdRelation(int nodefrom,int  nodeto, int bandwidth){
    	super();
    	this.m_nodefrom=nodefrom;
    	this.m_nodeto=nodeto;
    	this.m_bandwidth=bandwidth;	
    }
    
	public JxStdRelation(int relationid, int nodefrom, int nodeto, int bandwidth, int weight){
		super();
		this.m_owner = null;
		this.m_relation_id=relationid;
        this.m_nodefrom =nodefrom;
		this.m_nodeto = nodeto;
		this.m_bandwidth = bandwidth;
		this.m_weight = weight;
	}
	
	public int getId(){
	  return m_relation_id;
   }
	public void setId(int id){
		m_relation_id=id; 	
   }
	
	public JiRelationType getType(){
		return  m_type;
	}
	public void setType(JiRelationType m_type){
		this.m_type=m_type;	
	}
	
	
	public int getNodeFrom(){
		return  m_nodefrom;
	}
	public void setNodeFrom (int nodefrom){
		this.m_nodefrom=nodefrom;
	}
	
	
	public int getNodeTo(){
		return m_nodeto;
	}
	public void setNodeTo(int nodeto){
		this.m_nodeto=nodeto;	
	}
	
	public int getBandWidth(){
		return m_bandwidth;
	}
	
	public int getPacketSum(){
       return m_packetsum;		
	}
	public void setPacketSum(int sum){
		m_packetsum=sum;
	}
	@Override
  	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m_bandwidth;
		result = prime * result + m_nodefrom;
		result = prime * result + m_nodeto;
		result = prime * result + ((m_owner == null) ? 0 : m_owner.hashCode());
		result = prime * result + m_relation_id;
		result = prime * result + ((m_type == null) ? 0 : m_type.hashCode());
		result = prime * result + m_weight;
		return result;
	}
	
	void generateGraph( int nodecount){
		
		/**产生节点，并将其加入到网络中
        */
		int i, loc_x, loc_y; 
	    for (i=0; i< nodecount; i++) {
	       loc_x = random.nextInt(100);
		   loc_y = random.nextInt(100);
	       nodeCollection.add(new JxStdNode( loc_x, loc_y, 100 ));			
	    }
	    
	    int[] randomNodeSerial =new int[nodecount]; //随机选择节点加入网络  
	    randomNodeSerial =JxStdRelation.randomSerial(nodecount);
	    
	    for (i = 0; i<nodecount; i++){ //(将节点号为1-9999的节点依次加入网络 
	    	
	    	if(i==0){
	    	 int firstNodeId = randomNodeSerial[i];
	    	 addedSet.add(firstNodeId);
	    }
	        
	    	/**产生边,并将边加入边集中
	    	 */
	    	JiNode currentNode=new JxStdNode(); 
	    	JiNode selectNode =new JxStdNode();
	    	 
		    currentNode= nodeCollection.get(i);
	    	
		    int cur_node_id = currentNode.getId(); //当前节点ID 
			int select_node_id =selectnodeto();    //选择与之相连的节点ID
            
			selectNode=nodeCollection.get(select_node_id);
			
			edgeCollection.add(select_node_id, cur_node_id);
			
			/**将当前点的编号及选中点的编号加入到addedSet中  */
			
			
			/** 将当前点与选中点的度分别加1 */
			int currentNodeDegree =currentNode.getDegree();
			currentNode.setDegree(currentNodeDegree+1);
			
			int selectNodeDegree=selectNode.getDegree();
			selectNode.setDegree(selectNodeDegree+1);		
		}		
   }    
	
     protected int selectnodeto() {  	
		int p = random.nextInt(addedSet.size()); //生成在0——列表长度之间的整数值
		return addedSet.get(p);  //返回选中
    }

    public static int[] randomSerial(int count){
		   
		  int result[]=new int [count];	
	      for(int i=0;i<count;i++){
	    	  result[i]=i;
	      }	 
	      
	      Random random=new Random();
	      for(int i=count-1; i>0; i--){
	    	  int r= random.nextInt(i);
	    	  int temp =result[i];
	      	  result[i]=result[r];
	    	  result[r]=temp;
	      }
	     return result;   	
	} 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JxStdRelation other = (JxStdRelation) obj;
		if (m_bandwidth != other.m_bandwidth)
			return false;
		if (m_nodefrom != other.m_nodefrom)
			return false;
		if (m_nodeto != other.m_nodeto)
			return false;
		if (m_owner == null) {
			if (other.m_owner != null)
				return false;
		} else if (!m_owner.equals(other.m_owner))
			return false;
		if (m_relation_id != other.m_relation_id)
			return false;
		if (m_type != other.m_type)
			return false;
		if (m_weight != other.m_weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JxStdRelation [m_type=, m_nodefrom=" + m_nodefrom
				+ ", m_nodeto=" + m_nodeto + ", m_bandwidth=" + m_bandwidth
				+ ", m_weight=" + m_weight + "]";
	}
}
