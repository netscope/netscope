package kernel;

// public class JxSimuApplication

public class JxSimulator {
    
	/**点*/
    JiNode node= new JxStdNode(); 
    
    /**边*/
	JiRelation relation=new JxStdRelation();
	
	/**相互作用*/
	JiInteraction interact=new JxStdInteraction();
	
	/**输出*/
	JiTrace trace=new JxStdTrace();
	int nodecount=10;
	
    /**点集*/
	JxNodeCollection nodeset = new JxNodeCollection();
	
	/**边集*/
	JxRelationCollection relationset = new JxRelationCollection();
	
	/**构造函数*/
	JxSimulator(JxNodeCollection NodeSet,JxRelationCollection relationset,JxStdInteraction Interaction){	
		this.nodeset=NodeSet;
		this.relationset=relationset;
		this.interact=Interaction;	
	}

	
	void run(){

		/**产生拓扑 */
		
		nodes.generate( 10000 );
		relations.generate( 20000 );
		

		stdRelation.generateGraph(nodecount);
	
		
		/** 保存边和节点结构 */
		stdTrace.saveNode(sta);
		stdTrace.saveEdge(sta);
		
		int experienttime=1;
		
		for(int i=0;i<experienttime;i++){
			stdInteraction.interact();
			stdTrace.traceNode(sta,i);
			stdTrace.traceEdge(sta,i);
		}
		stdTrace.CloseDatabase();
		System.out.println("everything is ok");
	}
	public static void main(String []args){
		
		JxNodeCollection nodeset = new JxNodeCollection();
		JxRelationCollection relationset = new JxRelationCollection();
		JxStdInteraction interaction = new JxStdInteraction() ;
		
		relationset.generate();
		
		JxSimulator simulator=new JxSimulator(nodeset, relationset, interaction);
		
		for (int i=0; i<500; i++)	
		simulator.run();
	}		
}
