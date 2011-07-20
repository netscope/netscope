package kernel;

public class JxSimulator {
    
	/**点*/
    JiNode node= new JxStdNode(); 
  
    /**边*/
	JiRelation relation=new JxStdRelation();
	
	/**相互作用*/
	JiInteraction interaction=new JxStdInteraction();
	
	/**输出*/
	JiTrace trace=new JxStdTrace();
	
	int nodecount=10;
   
	/**点集*/
	JxNodeCollection nodeset = new JxNodeCollection();
	
	/**边集*/
	JxRelationCollection relationset = new JxRelationCollection();
	
	/**构造函数*/
	public JxSimulator(){
    }
	
	JxSimulator(JxNodeCollection NodeSet,JxRelationCollection relationset,JxStdInteraction Interaction){	
		this.nodeset=NodeSet;
		this.relationset=relationset;
		this.interaction=Interaction;	
	}
	
	void run()
	{
		/**产生拓扑 */
		relationset.generateGraph(nodecount);
		
		/**打开数据库*/
		trace.openDatabase();
		
		/**保存边和节点结构 */
		trace.saveNode();
		trace.saveEdge();
		
		int experienttime=3;
		for(int i=0;i<experienttime;i++){
			
		 	interaction.interact();
		    trace.traceNode(i);
		    trace.traceEdge(i);
		}
		trace.closeDatabase();
		System.out.println("everything is ok");
	}
	
	public static void main(String []args){	
		JxSimulator simulator=new JxSimulator();	
		simulator.run();
		/**
		JxNodeCollection nodeset = new JxNodeCollection();
		JxRelationCollection relationset = new JxRelationCollection();
		JxStdInteraction interaction = new JxStdInteraction();
		
		relationset.generate();
		
		JxSimulator simulator=new JxSimulator(nodeset, relationset, interaction);
	
		for (int i=0; i<500; i++)	
		simulator.run();
	}	*/	
  }
}
