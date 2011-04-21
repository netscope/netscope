package extend.scalefree;

import java.util.ArrayList;

/** 
 * @author Allen
 *
 */

/**
 * JxScaleFreeNode represents a single node in the network.
 */
public class JxScaleFreeNode {
	int m_loc_x;
	int m_loc_y;
	int m_length;
	int m_capacity;
	int m_weight;
	ArrayList<JxScaleFreeEdge> m_edgelist;
	
	public JxScaleFreeNode() {
		super();
		this.m_loc_x = 0;
		this.m_loc_y = 0;
		this.m_capacity = 0;
	}

	public JxScaleFreeNode(int x, int y, int capacity) {
		super();
		this.m_loc_x = x;
		this.m_loc_y = y;
		this.m_capacity = capacity;
	}

	@Override
	public String toString() {
		return "JxScaleFreeNode [m_loc_x=" + m_loc_x + ", m_loc_y=" + m_loc_y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m_capacity;
		result = prime * result
				+ ((m_edgelist == null) ? 0 : m_edgelist.hashCode());
		result = prime * result + m_loc_x;
		result = prime * result + m_loc_y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JxScaleFreeNode other = (JxScaleFreeNode) obj;
		if (m_capacity != other.m_capacity)
			return false;
		if (m_edgelist == null) {
			if (other.m_edgelist != null)
				return false;
		} else if (!m_edgelist.equals(other.m_edgelist))
			return false;
		if (m_length != other.m_length)
			return false;
		if (m_loc_x != other.m_loc_x)
			return false;
		if (m_loc_y != other.m_loc_y)
			return false;
		return true;
	}

	public int x() {
		return m_loc_x;
	}

	public void set_x(int loc_x) {
		this.m_loc_x = loc_x;
	}

	public int y() {
		return m_loc_y;
	}

	public void set_y(int loc_y) {
		this.m_loc_y = loc_y;
	}

	public int length() {
		return m_length;
	}

	public void set_length(int length) {
		this.m_length = length;
	}

	public int capacity() {
		return m_capacity;
	}

	public int weight() {
		return m_weight;
	}

	public void set_weight(int weight) {
		this.m_weight = weight;
	}

	public void set_capacity(int capacity) {
		this.m_capacity = capacity;
	}

	public ArrayList<JxScaleFreeEdge> edgelist() {
		return m_edgelist;
	}

	int degree() {
		return m_edgelist.size();
	};

}
