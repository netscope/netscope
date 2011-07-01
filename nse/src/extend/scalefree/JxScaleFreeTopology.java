package extend.scalefree;

import java.util.*;

public class JxScaleFreeTopology {
	
	Object m_owner;
	
	JxScaleFreeNodeCollection m_nodeset;
	
	JxScaleFreeEdgeCollection m_edgeset; 
	
	ArrayList<JxScaleFreeNode>  JoinInNetNode;
	
	public static Random random = new Random();
	
	JxScaleFreeTopology( Object owner ){
		
		m_nodeset = new JxScaleFreeNodeCollection();
		
		m_edgeset = new JxScaleFreeEdgeCollection();
		
		this.m_owner = owner;
	}
	
	JxScaleFreeTopology(){
		
		m_nodeset = new JxScaleFreeNodeCollection();
		
		m_edgeset = new JxScaleFreeEdgeCollection();
		
	}
	
	JxScaleFreeNodeCollection nodeset(){
		
		return this.m_nodeset;
	}
	
	JxScaleFreeEdgeCollection edgeset(){
		
		return this.m_edgeset;
	}
	
	void generate_random_graph( int nodecount, int edgecount )
	{
		int i, x, y;
		
		JxScaleFreeEdge edge; 
		
		JxScaleFreeNode node;
		
		int nodefrom, nodeto;

		// generate nodes and place them into the node set
	    for (i=0; i< nodecount; i++){
		
	    	x = random.nextInt(100);
			
	    	y = random.nextInt(100);
			
			m_nodeset.add( new JxScaleFreeNode(x, y, 100));			
		}
	    
	    // generate edges and place them into the nodeset
	    for (i=0; i< edgecount; i++){
			
	    	nodefrom = random.nextInt(nodecount);
			
	    	nodeto = random.nextInt(nodecount);
	    	
	    	m_edgeset.add(new JxScaleFreeEdge(nodefrom, nodeto, 100));
	    	
	    	//m_edgeset.add( new JxScaleFreeEdge( m_edgeset, nodefrom, nodeto, 100 ));			
		}
	}
	
	void generate( int nodecount ){
		
		int i, x, y;
		
		JxScaleFreeEdge edge; 
		
		JxScaleFreeNode node;  

		// generate nodes and place them into the node set
	    for (i=0; i< nodecount; i++){
	    	
			x = random.nextInt(100);
			
			y = random.nextInt(100);
			
			m_nodeset.add( new JxScaleFreeNode( x, y, 100 ));			
		}
	    
	    // generate edges and place them into the nodeset
	
	    int initnode = random.nextInt( nodecount );
	    
	    ArrayList<Integer> addedset = new ArrayList<Integer>();
	    
	    ArrayList<Integer> leftset = new ArrayList<Integer>();
	  
           // 创建边并将边加入到边集中
		
		    node = m_nodeset.get(0); //第一个点(作为初始点)                
	       
		    JoinInNetNode.add(node); //加入到网络中
	        
		    node.set_degree(1);      
		
	    for (i = 1; i<m_nodeset.count(); i++){ //(将节点号为1-9999的节点依次加入网络 
			
	    	JxScaleFreeNode cur_node; 
	    	
			JxScaleFreeNode select_node;
		  
		    cur_node = m_nodeset.get(i);     //当前节点
		  
			select_node =selectnodeto();  //选择与之相连的节点
            
			JoinInNetNode.add(cur_node);     //当前点加入网络
			
			cur_node.set_degree(cur_node.degree()+1);  //当前点的度加1
			
			JoinInNetNode.add(select_node);            //选中点加入网络
			
			select_node.set_degree(select_node.degree()+1);   //选中点的度加1
			
			edge = new JxScaleFreeEdge( i,cur_node.id(),select_node.id(), 10, 0 ); //新边(边号，起点，终点，带宽，权值)
			
			m_edgeset.add(edge);
		}    
	}	
	protected JxScaleFreeNode selectnodeto() {  
		
		int p = random.nextInt(JoinInNetNode.size()); //生成在0——列表长度之间的整数值
		
		return JoinInNetNode.get(p);  //返回选中节点
    }
	void load(){
		
	}
	void save(){
		
	}
}
