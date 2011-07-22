package kernel;

import java.util.ArrayList;
import java.util.Random;

// import extend.scalefree.JxScaleFreeEdge;
// import extend.scalefree.JxScaleFreeNode;

/**
 * This class maintains a list of JiBaseRelation object. You can add/remove a relation
 * object into/from this collection.
 *
 * @author Allen
 *
 */
public class JxBaseRelationCollection extends ArrayList<JiBaseRelation>{
	
	private static final long serialVersionUID = 1L;

    static  JxBaseNodeCollection nodeSet=new JxBaseNodeCollection();
	static  JxBaseRelationCollection relationSet=new JxBaseRelationCollection();
	ArrayList<Integer> addedSet=new ArrayList<Integer>(); 
	Random random=new Random(); 
	
    public int count() 
	{		
	  return super.size();		
	}
	  
	public boolean add(JiBaseRelation relation)
	{
	  return super.add(relation);
	}
	

	/** get JxScaleFreeEdge object at specified position with index */
	public JiBaseRelation get(int index)
	{ 
	  return super.get(index); 
	}
	
	/** 
	 * Generate relation objects into the relation collection. These objects 
	 * may describe the network topology as a graph, but it's not mandatory to 
	 * do so.
	 */
	public void generate()
	{
		JxBaseEngine engine = (JxBaseEngine)m_owner;
		Random random = engine.random();
		JxBaseTrace = engine.getTrace();
		
		// todo		
	}
	
      public void generateGraph( int nodecount){
		
		/**产生节点，并将其加入到网络中
		 * 如何保证产生的节点坐标不重复（重复的概率为1/10000）
        */
	    for (int id=0; id< nodecount; id++) {
	    	int loc_x = random.nextInt(100);
	    	int loc_y = random.nextInt(100);
	    	
	    	/**添加节点-初始负载量50，容量100 */
	    	JxBaseNode node=new JxBaseNode(id,loc_x,loc_y,50,100);
	        nodeSet.add(node);
	    }
	    
	    int[] randomNodeSerial =new int[nodecount]; //从点集中随机选择节点
	    randomNodeSerial =randomSerial(nodecount); 
	    
	    int currentNodeId=0;
    	int selectNodeId=0;
    	
	    for (int i = 0; i<nodecount; i++){ //(将节点号依次加入网络 	
	    
	    	/** 随机得到第一条边并加入addedSet中*/   //偏向连接算法可能有问题！！！                                             
	    	if(i==0)
	    	{
	    	  currentNodeId = randomNodeSerial[0];
	    	  addedSet.add(currentNodeId); 
	        }
	    	/**前两个点组成一条边,并将其加入边集中 */
	    	else if(i==1)
	    	{ 
	    	 currentNodeId = randomNodeSerial[1];
	    	 selectNodeId = randomNodeSerial[0];
	     	 addedSet.add(currentNodeId);  
	
	    	 JxBaseRelation relation=new JxBaseRelation(0, currentNodeId,selectNodeId,20);
	    	 relationSet.add(relation);
	    	 
	    	 /**得到当前的点及选中点*/
	    	 JiNode currentNode=nodeSet.get(currentNodeId);
			 JiNode selectNode=nodeSet.get(selectNodeId);
				
			 
			/** 将当前点与选中点的度分别加1*/
			int currentNodeDegree =currentNode.getDegree();
			currentNode.setDegree(currentNodeDegree+1);
				
			int selectNodeDegree=selectNode.getDegree();
			selectNode.setDegree(selectNodeDegree+1);    	 
	    }	
	   else{
	         currentNodeId=randomNodeSerial[i]; 
	         
	        /**选择与之相连的节点ID*/
			 selectNodeId =selectnodeto();
			/**将产生的边加入边集中 */
			JxBaseRelation relation=new JxBaseRelation(i-1,selectNodeId, currentNodeId,20);
         	relationSet.add(relation); 
			
			/**将当前点的编号及选中点的编号加入到addedSet中  */
         	addedSet.add(currentNodeId);
         	addedSet.add(selectNodeId);         //最后一次会多加一次

			JiNode currentNode=nodeSet.get(currentNodeId);
			JiNode selectNode=nodeSet.get(selectNodeId);
			
			/** 将当前点与选中点的度分别加1*/
			int currentNodeDegree =currentNode.getDegree();
			currentNode.setDegree(currentNodeDegree+1);
			
			int selectNodeDegree=selectNode.getDegree();
			selectNode.setDegree(selectNodeDegree+1);
	      }     	
		}
   }     
	
    protected int selectnodeto() {  //2n-2	
		int p = random.nextInt(addedSet.size()); //生成在0——列表长度之间的整数值
		return addedSet.get(p);  //返回选中
    }

    /**
     * Make the relation order in the list randomized. 
     */
    public void randomize()
    {
    	// todo
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
	
	public JiBaseRelation search(int id){
		boolean found = false;
		JiBaseRelation  relation = new JxBaseRelation();
		for (int i=0; i<super.size(); i++){
			relation = this.get(i);
			if (relation.getId()== id){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}
	
	
	public JiBaseRelation search(int nodefrom, int nodeto){
		
		boolean found = false;
		
		JiBaseRelation relation = new JxBaseRelation();
		
		for (int i=0; i<super.size(); i++){
			
			relation = this.get(i);
			
			 if ((relation.getNodeFrom() == nodefrom) && (relation.getNodeTo() == nodeto)){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}	
	
	
	public ArrayList<JiBaseRelation> in_edges_of( int nodefrom ){
		
		ArrayList<JiBaseRelation> in_edge_list =new ArrayList<JiBaseRelation>();
		JiBaseRelation relation=new JxBaseRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom){
			  in_edge_list.add(relation);
		   }   	 
		}
		return in_edge_list;
	}
	
	
	public ArrayList<JiBaseRelation> out_edges_of( int nodefrom ){
		
		ArrayList<JiBaseRelation> out_edge_list =new ArrayList<JiBaseRelation>();
		
		JiBaseRelation relation=new JxBaseRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom){
			  out_edge_list.add(relation);
		   }   	 
		}
		return out_edge_list;
	}
	
	
	public ArrayList<JiBaseRelation> edges_of( int nodefrom,int nodeto ){
   
		ArrayList<JiBaseRelation> edge_list =new ArrayList<JiBaseRelation>();
		JiBaseRelation relation=new JxBaseRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom||relation.getNodeTo()==nodeto){
			  edge_list.add(relation);
		   }   	 
		}
		return edge_list;
	}
	
}
