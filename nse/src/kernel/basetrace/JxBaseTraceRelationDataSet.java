package kernel.basetrace;

import java.util.ArrayList;

import kernel.JxBaseTraceRelationRecord;
import kernel.basetrace.JxBaseTraceLoader.JxBaseTraceRelation;

public class JxBaseTraceRelationDataSet{
	
	JxBaseTraceLoader m_owner = null;
	JxBaseTraceRelationDataSet(JxBaseTraceLoader owner) {m_owner = owner;};
	
	public ArrayList<JxBaseTraceRelation> loadmeta()
	{
		
	}
	public ArrayList<JxBaseTraceRelationRecord> loadtrace( int beingtime, int endtime )
	{
		
	}
}
