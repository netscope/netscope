package kernel;

import java.util.*;

public class JxBaseInteraction implements JiBaseInteraction {

	Object m_owner = null;
	Random m_random = JxBaseFoundation.random();	
	JxBaseTrace m_trace = null;

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
		// JxBaseTrace trace = (JxBaseTrace)engine.getTrace();

		JiBaseNode nodefrom = engine.getNodes().search(relation.getNodeFrom());
		JiBaseNode nodeto = engine.getNodes().search(relation.getNodeTo());

		int len1, len2, cut;
		len1 = nodefrom.getValue();
		len2 = nodeto.getValue();
		cut = m_random.nextInt(len1 + len2);
		nodefrom.setValue(cut);
		nodeto.setValue(len1 + len2 - cut);

		trace.trace( nodefrom );
		trace.trace( nodeto );
		trace.trace( relation );
	}

/*	// to be deleted
	// 对所有的边做一次包交换 
	public void interact() {
		
		*//** 定义随机序列 *//*
		JiBaseTrace trace = engine.getTrace();
		int edgeCount = relationSet.count();
		int[] randomSerial = new int[edgeCount];
		randomSerial = JxBaseRelationCollection.randomSerial(edgeCount);

		for (int i = 0; i < edgeCount; i++) {

			*//** 随机得到一条边 *//*
			int relationId = randomSerial[i];
			relation = relationSet.get(relationId);

			int nodefrom = relation.getNodeFrom();
			int nodeto = relation.getNodeTo();

			*//** 发送节点-接收节点 *//*
			JiBaseNode sender = new JxBaseNode();
			JiBaseNode receiver = new JxBaseNode();

			*//** 随机确定发送方向 *//*
			int temp = random.nextInt(2);
			if (temp == 0) {
				sender = nodeSet.get(nodefrom);
				receiver = nodeSet.get(nodeto);
			} else {
				sender = nodeSet.get(nodeto);
				receiver = nodeSet.get(nodefrom);
			}
			*//** 发送点的包个数 *//*
			int senderValue = sender.getValue();

			*//** 接收点的剩余空间 *//*
			int receiverVolume = receiver.getCapacity() - receiver.getValue();

			*//** 边的带宽 *//*
			int bandwidth = relation.getBandWidth();

			int packet = Minimum(senderValue, receiverVolume, bandwidth);

			sender.setValue(senderValue - packet);

			int receiverValue = receiver.getValue();
			receiver.setValue(receiverValue + packet);

			int packetsum = relation.getPacketSum() + packet;
			relation.setPacketSum(packetsum); // 记录边上包的流量
		}
		for (int i = 0; i < nodeSet.count(); i++) {
			System.out.println("nodeId：" + nodeSet.get(i).getId() + " loc_x "
					+ nodeSet.get(i).getLocx() + " loc_y "
					+ nodeSet.get(i).getY() + " nodelength "
					+ nodeSet.get(i).getValue());
		}
	}
*/
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
			minimum = m_random.nextInt(mini);
		}
		return minimum;
	}

	@Override
	public void setTrace(JiBaseTrace trace) {
		// TODO Auto-generated method stub
		m_trace = trace;		
	}
}
