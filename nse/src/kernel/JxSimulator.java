package kernel;
import java.sql.Statement;
<<<<<<< HEAD
=======

// public class JxSimuApplication
>>>>>>> 861510d5ddf29c4cf3735e7d308c902e7e15ae2e
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
<<<<<<< HEAD
    
=======
	
	/** JxSimulator(){	
		for (int i;i<edgeCollection.count();i++){
			interact( m_relationset.current());
		}	
	} */

	JxSimulator(class NodeClass, class RelationClass, class InteractionClass)
	{
		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	
	}
>>>>>>> 861510d5ddf29c4cf3735e7d308c902e7e15ae2e
	
	void run(){
<<<<<<< HEAD
		/**产生拓扑 */
=======
		
		nodes = new JxNodeCollection();
		relations = new JxRelationCollection();
		
		nodes.generate( 10000 );
		relations.generate( 20000 );
		
>>>>>>> f7dd2920cbc8f6be9964d882446f0dca27b6d6f5
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
	public static void test(String []args){
		
		nodes = new JxNodeCollection();
		relations = new JxRelationCollection();
		interaction = new JxInteraction ;
		
		nodes.deploy(1000, 1000);
		relations.generate( nodes )
		
		JxSimulator simulator=new JxSimulator(nodes, relations, interaction);
		for (i=0; i<500; i++)
			
		simulator.run();
	}		
}
