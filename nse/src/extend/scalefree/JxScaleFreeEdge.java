package extend.scalefree;

public class JxScaleFreeEdge {
	
	int m_nodefrom;
	int m_nodeto;
	int m_bandwidth;
	int m_weight;
	int m_distance;
	
	public JxScaleFreeEdge() {//构造函数1
		
		super();
		this.m_nodefrom = 0;
		this.m_nodeto = 0;
		this.m_bandwidth = 0;
		this.m_weight = 0;
	}

	public JxScaleFreeEdge(int m_nodefrom, int m_nodeto, int m_bandwidth, int m_weight) {//构造函数2
		
		super();
		this.m_nodefrom = m_nodefrom;
		this.m_nodeto = m_nodeto;
		this.m_bandwidth = m_bandwidth;
		this.m_weight = m_weight;
	}

	@Override //重载
	public int hashCode() { //哈希码 
		final int prime = 31;
		int result = 1;
		result = prime * result + m_nodefrom;
		result = prime * result + m_nodeto;
		return result;
	}

	@Override
	public boolean equals(Object obj) { //
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JxScaleFreeEdge other = (JxScaleFreeEdge) obj;
		if (m_nodefrom != other.m_nodefrom)
			return false;
		if (m_nodeto != other.m_nodeto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JxScaleFreeEdge [m_nodefrom=" + m_nodefrom + ", m_nodeto=" + m_nodeto
				+ "]";
	}
/*
	double distance()
	{
		JxScaleFreeNode node1 = JxScaleFreeNodeCollection.search(m_nodefrom);
		JxScaleFreeNode node2 = JxScaleFreeNodeCollection.search(m_nodeto);
		
		return Math.sqrt(((node1.x() - node2.x()) * (node1.x() - node2.x())
				+ (node1.y() - node2.y()) * (node1.y() - node2.y())));
		
	}
*/	
	public int distance() {   
		return m_distance;
	}

	public void setDistance(int distance) {
		this.m_distance = distance;
	}

}
