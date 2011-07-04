package kernel;

import java.util.ArrayList;

public class JxSimulator {

	JiNode node;
	JiRelation relation=new JxStdRelation();
	JiInteraction interact=new JxStdInteraction();
	JiTrace trace=new JxStdTrace();
	
	JxNodeCollection nodeCollection;
	JxEdgeCollection edgeCollection;
	
	
	
	JxSimulator(){	
		for (m_relationset){
			interact( m_relationset.current());
		}	
	}
	
	void step()	{
		
	}
	
	void run(){
		relation.generate();
		interact();
		saveNode();
		saveEdge();
		traceNode();
		traceEdge();
		loadNode();
		loadEdge();
	}
}
