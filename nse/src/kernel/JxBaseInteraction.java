package kernel;

import java.util.*;

public class JxBaseInteraction implements JiBaseInteraction {

	Object m_owner = null;

	JxBaseInteraction(Object owner) {
		m_owner = owner;
	}

	@Override
	public Object getOwner() {
		return m_owner;
	}

	@Override
	public void setOwner(Object owner) {
		m_owner = owner;
	}

	@Override
	public void interact(JiBaseRelation relation, JiBaseTrace trace) 
	{
		JxBaseEngine engine = (JxBaseEngine) m_owner;

		Random random = engine.getRandom();
	 // JiBaseTrace trace = engine.getTrace();

		
		JiBaseNode nodefrom = relation.getNodeFrom();
		JiBaseNode nodeto = relation.getNodeTo();

		int len1, len2, cut;
		len1 = nodefrom.getLength();
		len2 = nodeto.getLength();
		cut = random.nextInt(len1 + len2);
		nodefrom.setLength(cut);
		nodeto.setLength(len1 + len2 - cut);

		relation.trace(nodefrom);
		relation.trace(nodeto);
	}

	public int Minimum(int a, int b, int c) { // 发送包的个数要小于这三个值

		int minimum = 0;
		int mini = a;
		if (b < mini)
			mini = b;
		if (c < mini)
			mini = c;
		if (mini == 0) {
			minimum = 0;
		} else {
			minimum = random.nextInt(mini);
		}
		return minimum;
	}

}
