package kernel;

import java.util.ArrayList;
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
	
	
	JxNodeCollection nodeCollection;
	JxEdgeCollection edgeCollection;
	 
	int nodecount=10;
	
	/** JxSimulator(){	
		for (int i;i<edgeCollection.count();i++){
			interact( m_relationset.current());
		}	
	} */

	
	void run(){
		
		stdRelation.generateGraph(nodecount);
		stdInteraction.interact();
		
		String database=null;
		Statement sta=stdTrace.openDatabase(database);
		
		stdTrace.saveNode(sta);
		stdTrace.saveEdge(sta);
		
		int time=0;
		stdTrace.traceNode(sta,time);
		stdTrace.traceEdge(sta,time);
		
	   /**	
		String tablename=null;
		stdTrace.loadNode(sta,tablename);
		stdTrace.loadEdge(sta,tablename);
		*/
		System.out.println("everything is ok");
	}
	public static void main(String []args){
		JxSimulator simulator=new JxSimulator();
		simulator.run();
	}
	
}
