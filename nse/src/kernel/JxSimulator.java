package kernel;

import java.util.ArrayList;

public class JxSimulator {

	JiNode node;
	JiRelation rel;
	JiInteraction interact;
	
	ArrayList<JiNode> m_nodeset;
	ArrayList<JiRelation> m_relationset;
	
	JxSimulator()
	{
		m_nodeset = new[] JxNetNode();
		m_relationset = topology_generater( m_nodeset );
		
		for (m_relationset)
		{
			interact( m_relationset.current());
		}
		
	}
	
	void step()
	{
		
	}
	
	void run()
	{
		
	}
}
