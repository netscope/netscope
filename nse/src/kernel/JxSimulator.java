package kernel;

import java.util.ArrayList;
import java.sql.Statement;

// public class JxSimuApplication
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

	JxSimulator(class NodeClass, class RelationClass, class InteractionClass)
	{
		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	
	}
	
	void run(){
		
		nodes = new JxNodeCollection();
		relations = new JxRelationCollection();
		
		nodes.generate( 10000 );
		relations.generate( 20000 );
		
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
