package kernel;
import java.sql.Statement;
public class JxSimulator {
    
	
	JiNode node;
     
	JiRelation relation=new JxStdRelation();
	JiInteraction interact=new JxStdInteraction();
	JiTrace trace=new JxStdTrace();
	
	
	JxStdNode stdnode= new JxStdNode(); 
	JxStdRelation stdRelation=new JxStdRelation();
	JxStdInteraction stdInteraction=new JxStdInteraction();
	JxStdTrace stdTrace=new JxStdTrace();
	
	
	int nodecount=10;
    
	
	void run(){
		/**产生拓扑 */
		stdRelation.generateGraph(nodecount);
	
		Statement sta=stdTrace.openDatabase();
		
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
		JxSimulator simulator=new JxSimulator();
		simulator.run();
	}		
}
