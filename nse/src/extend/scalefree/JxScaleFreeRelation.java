package extend.scalefree;

import kernel.JiBaseRelation;
import kernel.JxBaseRelation;

public class JxScaleFreeRelation extends JxBaseRelation implements JiBaseRelation 
{
	@Override
	public String toString() 
	{
		return "JxScaleFreeRelation [m_id=" + m_id + "nodefrom="+m_nodes.get(0)+"m_nodeto="+m_nodes.get(1)+", m_totalsent="
				+ m_totalsent + "]";
	}
}
