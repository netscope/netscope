package kernel;

import java.util.Random;

public class JxBaseInteraction implements JiBaseInteraction {
     
	 Object m_owner = null;	
     JxBaseTrace m_trace = null;
     Random m_random = JxBaseFoundation.random();
     
     public  JxBaseInteraction()
     {  
    	 m_owner = null;
         m_trace = null;
     } 
        
    public JxBaseInteraction(Object owner) 
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
   
	public void interact(int time,JiBaseRelation relation,JiBaseTrace trace) 
	{	
		JiBaseNode nodefrom =relation.getNodeFrom();
		JiBaseNode nodeto =relation.getNodeTo();	

		int len1, len2, cut;
		
		len1 = nodefrom.getValue();
		len2 = nodeto.getValue();
		
		if(len1+len2==0)
		{
		    cut=0;
		}
		else
		{
			cut = m_random.nextInt(len1 + len2);	
		}
		
		nodefrom.setValue(cut);
		nodeto.setValue(len1 + len2 - cut);
       
		relation.setPacket(cut);
		
        if(cut!=0)
        {
		   trace.trace( time,relation );
		}
	}
	 
    
	public int Minimum(int a, int b, int c) 
	{ 
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
	
	
    public JiBaseTrace getTrace()
    {
    	return m_trace;
    }
    
    public void setTrace(JiBaseTrace trace)
    {
    	m_trace=(JxBaseTrace)trace;
    } 
}
