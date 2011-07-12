package kernel;
import java.util.*;

public class JxStdRelation implements JiRelation {
	
	private Object m_owner;
	private int m_relation_id; 
	private JiRelationType m_type;
	private int  m_nodefrom;
	private int  m_nodeto;
	private int  m_bandwidth;
	private int  m_weight;
	private int  m_packetsum;

	Random random= new Random();
	
   static  JxNodeCollection nodeCollection=new JxNodeCollection();
   static  JxEdgeCollection edgeCollection=new JxEdgeCollection();
    
    JiNode node=new JxStdNode();
    
    ArrayList<Integer> addedSet = new ArrayList<Integer>();
    
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
    
	public JxStdRelation(int relationid, int nodefrom, int nodeto,int bandwidth){
		super();
		this.m_owner = null;
		this.m_relation_id=relationid;
        this.m_nodefrom =nodefrom;
		this.m_nodeto = nodeto;
		this.m_bandwidth=bandwidth;
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
		 * 如何保证产生的节点坐标不重复（重复的概率为1/10000）
        */
	    for (int id=0; id< nodecount; id++) {
	    	int loc_x = random.nextInt(100);
	    	int loc_y = random.nextInt(100);
	    	
	    	/**添加节点-初始负载量50，容量100 */
	    	JxStdNode node=new JxStdNode(id,loc_x,loc_y,50,100);
	        nodeCollection.add(node);
	    }
	    
	    int[] randomNodeSerial =new int[nodecount]; //从点集中随机选择节点
	    randomNodeSerial =JxStdRelation.randomSerial(nodecount);
	    
	   
	    for (int i = 0; i<nodecount; i++){ //(将节点号依次加入网络 	
	    	
	    	/** 随机得到第一条边并加入addedSet中*/
	                                                //偏向连接算法可能有问题！！！
	    	if(i==0)
	    	{
	    	  int firstNodeId = randomNodeSerial[i];
	    	  addedSet.add(firstNodeId);
	        }
	    	/**产生边,并将边加入边集中 */
	 	    else {
	        int currentNodeId=randomNodeSerial[i]; 
	        
	        /**选择与之相连的节点ID*/
			int selectNodeId =selectnodeto();
			
			/**将产生的边加入边集中 */
			JxStdRelation relation=new JxStdRelation(i-1,selectNodeId, currentNodeId,20);
         	edgeCollection.add(relation); 
			
			/**将当前点的编号及选中点的编号加入到addedSet中  */
         	addedSet.add(currentNodeId);
         	addedSet.add(selectNodeId);         //最后一次会多加一次

			JiNode currentNode=nodeCollection.get(currentNodeId);
			JiNode selectNode=nodeCollection.get(selectNodeId);
			
			/** 将当前点与选中点的度分别加1 */
			int currentNodeDegree =currentNode.getDegree();
			currentNode.setDegree(currentNodeDegree+1);
			
			int selectNodeDegree=selectNode.getDegree();
			selectNode.setDegree(selectNodeDegree+1);
	      }     	
		}	
	    System.out.println("checking is over!");
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
		return "JxStdRelation [m_type="+m_type+",m_relation_id="+ m_relation_id +",m_nodefrom=" + m_nodefrom
				+ ", m_nodeto=" + m_nodeto + ", m_bandwidth=" + m_bandwidth
				+ ", m_weight=" + m_weight + ",m_bandwidth="+m_bandwidth+"]";
	}
   /** 最后一个点不用加如到addedset中
       if(i==nodecount-1)
	   currentNodeId=randomNodeSerial[i]; 
		   selectNodeId =selectnodeto();
		  
		   edgeCollection.add(selectNodeId, currentNodeId);

		JiNode currentNode=nodeCollection.get(currentNodeId);
		JiNode selectNode=nodeCollection.get(selectNodeId);
		
		/** 将当前点与选中点的度分别加1 
		int currentNodeDegree =currentNode.getDegree();
		currentNode.setDegree(currentNodeDegree+1);
		
		int selectNodeDegree=selectNode.getDegree();
		selectNode.setDegree(selectNodeDegree+1);
	}  */
}
