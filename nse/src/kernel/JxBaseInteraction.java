package kernel;

import java.util.*;

public class JxBaseInteraction implements JiBaseInteraction {

	Object m_owner = null;

	JxBaseInteraction(Object owner) {
		m_owner = owner;
	}

	public void interact(JiBaseRelation relation) {
		JxBaseEngine engine = (JxBaseEngine) m_owner;

		Random random = engine.getRandom();
		JiBaseTrace = engine.getTrace();

		JiBaseNode nodefrom = relation.nodefrom();
		JiBaseNode nodeto = relation.nodeto();

		int len1, len2, cut;
		len1 = nodefrom.length();
		len2 = nodeto.length();
		cut = random.nextInt(len1 + len2);
		nodefrom.setLength(cut);
		nodeto.setLength(len1 + len2 - cut);

		relation.trace(nodefrom);
		relation.trace(nodeto);
	}

	/** �����еı���һ�ΰ����� */
	public void interact() {
		/** ����������� */
		int edgeCount = relationSet.count();
		int[] randomSerial = new int[edgeCount];
		randomSerial = JxBaseRelationCollection.randomSerial(edgeCount);

		for (int i = 0; i < edgeCount; i++) {

			/** ����õ�һ���� */
			int relationId = randomSerial[i];
			relation = relationSet.get(relationId);

			int nodefrom = relation.getNodeFrom();
			int nodeto = relation.getNodeTo();

			/** ���ͽڵ�-���սڵ� */
			JiBaseNode sender = new JxBaseNode();
			JiBaseNode receiver = new JxBaseNode();

			/** ���ȷ�����ͷ��� */
			int temp = random.nextInt(2);
			if (temp == 0) {
				sender = nodeSet.get(nodefrom);
				receiver = nodeSet.get(nodeto);
			} else {
				sender = nodeSet.get(nodeto);
				receiver = nodeSet.get(nodefrom);
			}
			/** ���͵�İ����� */
			int senderValue = sender.getValue();

			/** ���յ��ʣ��ռ� */
			int receiverVolume = receiver.getCapacity() - receiver.getValue();

			/** �ߵĴ��� */
			int bandwidth = relation.getBandWidth();

			int packet = Minimum(senderValue, receiverVolume, bandwidth);

			sender.setValue(senderValue - packet);

			int receiverValue = receiver.getValue();
			receiver.setValue(receiverValue + packet);

			int packetsum = relation.getPacketSum() + packet;
			relation.setPacketSum(packetsum); // ��¼���ϰ�������
		}
		for (int i = 0; i < nodeSet.count(); i++) {
			System.out.println("nodeId��" + nodeSet.get(i).getId() + " loc_x "
					+ nodeSet.get(i).getLocx() + " loc_y "
					+ nodeSet.get(i).getY() + " nodelength "
					+ nodeSet.get(i).getValue());
		}
	}

	public int Minimum(int a, int b, int c) { // ���Ͱ��ĸ���ҪС��������ֵ

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