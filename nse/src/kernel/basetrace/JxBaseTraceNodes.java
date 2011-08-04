package kernel.basetrace;

import java.util.ArrayList;
import kernel.JxBaseNode;
import kernel.basetrace.JxBaseTraceLoader.JxBaseTraceNodeRecord;

public class JxBaseTraceNodes {
	
	Object m_owner = null;

	JxBaseTraceNodes(Object owner)
	{
		m_owner = owner;
	}
	
	public JxBaseNode[] loadmeta()
	{
		return null;		
	}
	
	public JxBaseTraceNodeRecord[] loadtrace()
	{
		return null;
	}

}
