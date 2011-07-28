package kernel;

import java.util.*;

public class JxBaseInteraction implements JiBaseInteraction {
     
	 Object m_owner = null;
	 
     Random m_random = JxBaseFoundation.random();	
     JxBaseTrace m_trace = null;
     
     JxBaseInteraction()
     {  
    	 
     } 
     
     
	JxBaseInteraction(Object owner) 
	{
		m_owner = owner;
	}
	 
	
	public Object getOwner()
	{
		return m_owner;
	}
    
    
	public void setOwner(Object owner)
	{
		m_owner = owner;
	}
    
	
	public void interact(JiBaseRelation relation, JiBaseTrace trace) 
	{
		JxBaseEngine engine = (JxBaseEngine) m_owner;

		JiBaseNode nodefrom = engine.getNodes().search(relation.getNodeFrom().getId());
		JiBaseNode nodeto = engine.getNodes().search(relation.getNodeTo().getId());	

		int len1, len2, cut;
		len1 = nodefrom.getValue();
		len2 = nodeto.getValue();
		cut = m_random.nextInt(len1 + len2);
		nodefrom.setValue(cut);
		nodeto.setValue(len1 + len2 - cut);

		/**如何保证只trace改变点的*/
		trace.trace( nodefrom );
		trace.trace( nodeto );
		trace.trace( relation );
	}
    
	
	public int Minimum(int a, int b, int c) { // 发送包的个数要小于这三个值

		int minimum = 0;
		int mini = a;
		
		if (b < mini)
			mini = b;
		if (c < mini)
			mini = c;
		if (mini == 0) 	
			minimum = 0;
 
		else 
		{
			minimum = m_random.nextInt(mini);
		}
		return minimum;
	}

	@Override
	public void setTrace(JiBaseTrace trace) {
		m_trace = (JxBaseTrace)trace;		
	}
}
