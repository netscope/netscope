package extend.scalefree;

import kernel.*;
import java.util.*;

public class JxScaleFreeApplication 
{
	JxBaseRelationCollection m_relations = new JxBaseRelationCollection();
	JxBaseNodeCollection m_nodes = new JxBaseNodeCollection();

	JxBaseInteraction m_interaction = new JxBaseInteraction();

	ArrayList<JiBaseNode> m_leftnodes = new ArrayList<JiBaseNode>();
	ArrayList<JiBaseNode> m_addnodes = new ArrayList<JiBaseNode>();

	JxBaseRelation m_relation = new JxBaseRelation();
	JxBaseTrace m_trace = new JxBaseTrace();
	JxBaseNode m_node = new JxBaseNode();

	Random m_random = new Random();

	public void open() 
	{
		m_trace.open();
	}

	public void close() 
	{
		m_trace.close();
	}

	public void generateNodes(int count) 
	{
		m_nodes.generate(count);
	}

	public void generateTopo1(int count) 
	{
		m_nodes.randomize();
		m_relations.generate(count);

		for (int i = 0; i <1; i++) // add the first nodes into the add nodes
		{
			m_addnodes.add(m_nodes.get(i));
		}

		for (int i = 1; i < m_nodes.count(); i++) 
		{
			m_leftnodes.add(m_nodes.get(i));
		}

		for (int i = 0; i < m_nodes.size() - 1; i++) 
		{
			if (i == 0) 
			{
				JxBaseNode firstNodeFrom = (JxBaseNode) m_leftnodes.remove(0);
				m_addnodes.add(firstNodeFrom);

				for (int j = 0; j < 1; j++)  // the first six is permanent
				{
					JxBaseRelation relation = (JxBaseRelation) m_relations.get(j);

					JxBaseNode nodeTo = (JxBaseNode) m_nodes.get(j);

					relation.setNodeFrom(firstNodeFrom);
					relation.setNodeTo(nodeTo);

					firstNodeFrom.addNeighborNode(nodeTo); // set the neighbor
															// nodes
					nodeTo.addNeighborNode(firstNodeFrom);

					// firstNodeFrom.addNeighborRelation(relation); //set the
					// neighbor relations
					// nodeTo.addNeighborRelation(relation);

					firstNodeFrom.setDegreeIn(firstNodeFrom.getDegreeIn() + 1);
					nodeTo.setDegreeIn(nodeTo.getDegreeIn() + 1);
				}
			}

			else {
				JxBaseNode nodeFrom = (JxBaseNode) m_leftnodes.remove(0);

				ArrayList<JiBaseNode> selectNodesTo = this.selectNodesTo(1);

				for (int j = 0; j < 1; j++) {
					JxBaseRelation relation = (JxBaseRelation) m_relations.get(i + j);
					relation.setNodeFrom(nodeFrom);

					JxBaseNode nodeTo = (JxBaseNode) selectNodesTo.get(j);
					relation.setNodeTo(nodeTo);

					nodeFrom.addNeighborNode(nodeTo);
					nodeTo.addNeighborNode(nodeFrom);

					nodeFrom.setDegreeIn( nodeFrom.getDegreeIn() + 1);
					nodeTo.setDegreeIn( nodeTo.getDegreeIn() + 1);

					m_addnodes.add(nodeFrom);
					m_addnodes.add(nodeTo);
				}
			}
		}
		m_trace.save(m_nodes);
		m_trace.save(m_relations);
	}

	public void generateTopo3(int count) 
	{
		m_nodes.randomize();
		m_relations.generate(count);

		for (int i=0; i <3; i++) // add the initial 6 nodes into the add nodes
		{
			m_addnodes.add(m_nodes.get(i));
		}

		for (int i=3; i <m_nodes.count(); i++) {
			m_leftnodes.add(m_nodes.get(i));
		}

		for (int i=0; i <m_nodes.size()-3; i++) 
		{
			if (i==0) 
			{
				JxBaseNode firstNodeFrom = (JxBaseNode) m_leftnodes.remove(0);
				m_addnodes.add(firstNodeFrom);

				for (int j = 0; j < 3; j++)  //select 3 neighborNodes for the firstNodeFrom
				{
					JxBaseRelation relation = (JxBaseRelation) m_relations.get(j);

					JxBaseNode nodeTo = (JxBaseNode) m_addnodes.get(j);

					relation.setNodeFrom(firstNodeFrom);
					relation.setNodeTo(nodeTo);

					firstNodeFrom.addNeighborNode(nodeTo); // set the neighbor nodes
					nodeTo.addNeighborNode(firstNodeFrom);

					// firstNodeFrom.addNeighborRelation(relation); //set the
					// neighbor relations
					// nodeTo.addNeighborRelation(relation);

					firstNodeFrom.setDegreeIn(firstNodeFrom.getDegreeIn()+1);
					nodeTo.setDegreeIn(nodeTo.getDegreeIn()+1);
				}
			}

			else {
				JxBaseNode nodeFrom = (JxBaseNode) m_leftnodes.remove(0);

				ArrayList<JiBaseNode> selectNodesTo = this.selectNodesTo(3);

				for (int j = 0; j < 3; j++) 
				{
					JxBaseRelation relation = (JxBaseRelation) m_relations.get(3*i+j);
					relation.setNodeFrom(nodeFrom);

					JxBaseNode nodeTo = (JxBaseNode)selectNodesTo.get(j);
					relation.setNodeTo(nodeTo);

					nodeFrom.addNeighborNode(nodeTo);
					nodeTo.addNeighborNode(nodeFrom);

					int degree1 = nodeFrom.getDegreeIn();
					int degree2 = nodeTo.getDegreeIn();

					nodeFrom.setDegreeIn(degree1 + 1);
					nodeTo.setDegreeIn(degree2 + 1);

					m_addnodes.add(nodeFrom);
					m_addnodes.add(nodeTo);
				}
			}
		}
		m_trace.save(m_nodes);
		m_trace.save(m_relations);
	}
		public void generateTopo5(int count) 
		{
			m_nodes.randomize();
			m_relations.generate(count);

			for (int i = 0; i < 5; i++) // add the initial 6 nodes into the added nodes
			{
				m_addnodes.add(m_nodes.get(i));
			}

			for (int i = 5; i < m_nodes.count(); i++) {
				m_leftnodes.add(m_nodes.get(i));
			}

			for (int i = 0; i < m_nodes.size() - 5; i++) 
			{
				if (i == 0) 
				{
					JxBaseNode firstNodeFrom = (JxBaseNode) m_leftnodes.remove(0);
					m_addnodes.add(firstNodeFrom);

					for (int j = 0; j < 5; j++) // the first five is permanent
					{
						JxBaseRelation relation = (JxBaseRelation) m_relations.get(j);

						JxBaseNode nodeTo = (JxBaseNode) m_nodes.get(j);

						relation.setNodeFrom(firstNodeFrom);
						relation.setNodeTo(nodeTo);

						firstNodeFrom.addNeighborNode(nodeTo); // set the neighbor
																// nodes
						nodeTo.addNeighborNode(firstNodeFrom);

						// firstNodeFrom.addNeighborRelation(relation); //set the
						// neighbor relations
						// nodeTo.addNeighborRelation(relation);

						int degree1 = firstNodeFrom.getDegreeIn();
						int degree2 = nodeTo.getDegreeIn();

						firstNodeFrom.setDegreeIn(degree1 + 1);
						nodeTo.setDegreeIn(degree2 + 1);
					}
				}

				else 
				{
					JxBaseNode nodeFrom = (JxBaseNode) m_leftnodes.remove(0);

					ArrayList<JiBaseNode> selectNodesTo = this.selectNodesTo(5);

					for (int j = 0; j < 5; j++) {
						JxBaseRelation relation = (JxBaseRelation) m_relations
								.get(5 * i + j);
						relation.setNodeFrom(nodeFrom);

						JxBaseNode nodeTo = (JxBaseNode) selectNodesTo.get(j);
						relation.setNodeTo(nodeTo);

						nodeFrom.addNeighborNode(nodeTo);
						nodeTo.addNeighborNode(nodeFrom);

						int degree1 = nodeFrom.getDegreeIn();
						int degree2 = nodeTo.getDegreeIn();

						nodeFrom.setDegreeIn(degree1 + 1);
						nodeTo.setDegreeIn(degree2 + 1);

						m_addnodes.add(nodeFrom);
						m_addnodes.add(nodeTo);
					}
				}
			}
			m_trace.save(m_nodes);
			m_trace.save(m_relations);
		}
		public void generateTopo7(int count) 
		{
			m_nodes.randomize();
			m_relations.generate(count);

			for (int i = 0; i < 7; i++) // add the initial 7 nodes into the add nodes
			{
				m_addnodes.add(m_nodes.get(i));
			}

			for (int i = 7; i < m_nodes.count(); i++) 
			{
				m_leftnodes.add(m_nodes.get(i));
			}

			for (int i = 0; i < m_nodes.size() - 7; i++) 
			{
				if (i == 0) 
				{
					JxBaseNode firstNodeFrom = (JxBaseNode) m_leftnodes.remove(0);
					m_addnodes.add(firstNodeFrom);

					for (int j = 0; j < 7; j++) //the first six is permanent
					{
						JxBaseRelation relation = (JxBaseRelation) m_relations.get(j);

						JxBaseNode nodeTo = (JxBaseNode) m_nodes.get(j);

						relation.setNodeFrom(firstNodeFrom);
						relation.setNodeTo(nodeTo);

						firstNodeFrom.addNeighborNode(nodeTo); // set the neighbor
																// nodes
						nodeTo.addNeighborNode(firstNodeFrom);

						// firstNodeFrom.addNeighborRelation(relation); //set the
						// neighbor relations
						// nodeTo.addNeighborRelation(relation);

						int degree1 = firstNodeFrom.getDegreeIn();
						int degree2 = nodeTo.getDegreeIn();

						firstNodeFrom.setDegreeIn(degree1 + 1);
						nodeTo.setDegreeIn(degree2 + 1);
					}
				}

				else {
					JxBaseNode nodeFrom = (JxBaseNode) m_leftnodes.remove(0);

					ArrayList<JiBaseNode> selectNodesTo = this.selectNodesTo(7);

					for (int j = 0; j < 7; j++) {
						JxBaseRelation relation = (JxBaseRelation) m_relations.get(7* i + j);
						relation.setNodeFrom(nodeFrom);

						JxBaseNode nodeTo = (JxBaseNode) selectNodesTo.get(j);
						relation.setNodeTo(nodeTo);

						nodeFrom.addNeighborNode(nodeTo);
						nodeTo.addNeighborNode(nodeFrom);

						int degree1 = nodeFrom.getDegreeIn();
						int degree2 = nodeTo.getDegreeIn();

						nodeFrom.setDegreeIn(degree1 + 1);
						nodeTo.setDegreeIn(degree2 + 1);

						m_addnodes.add(nodeFrom);
						m_addnodes.add(nodeTo);
					}
				}
			}
			m_trace.save(m_nodes);
			m_trace.save(m_relations);
		}
		
		public void generateTopo9(int count) 
		{
			m_nodes.randomize();
			m_relations.generate(count);

			for (int i = 0; i < 9; i++) // add the initial 9 nodes into the add nodes
			{
				m_addnodes.add(m_nodes.get(i));
			}

			for (int i = 9; i < m_nodes.count(); i++) 
			{
				m_leftnodes.add(m_nodes.get(i));
			}

			for (int i = 0; i < m_nodes.size()-9; i++) 
			{
				if (i == 0) 
				{
					JxBaseNode firstNodeFrom = (JxBaseNode) m_leftnodes.remove(0);
					m_addnodes.add(firstNodeFrom);

					for (int j = 0; j < 9; j++) // the first six is permanent
					{
						JxBaseRelation relation = (JxBaseRelation) m_relations.get(j);

						JxBaseNode nodeTo = (JxBaseNode) m_nodes.get(j);

						relation.setNodeFrom(firstNodeFrom);
						relation.setNodeTo(nodeTo);

						firstNodeFrom.addNeighborNode(nodeTo); // set the neighbor nodes
						nodeTo.addNeighborNode(firstNodeFrom);

						// firstNodeFrom.addNeighborRelation(relation); //set the
						// neighbor relations
						// nodeTo.addNeighborRelation(relation);

						int degree1 = firstNodeFrom.getDegreeIn();
						int degree2 = nodeTo.getDegreeIn();

						firstNodeFrom.setDegreeIn(degree1 + 1);
						nodeTo.setDegreeIn(degree2 + 1);
					}
				}

				else {
					JxBaseNode nodeFrom = (JxBaseNode) m_leftnodes.remove(0);

					ArrayList<JiBaseNode> selectNodesTo = this.selectNodesTo(9);

					for (int j = 0; j < 9; j++) 
					{
						JxBaseRelation relation = (JxBaseRelation) m_relations.get(9 * i + j);
						relation.setNodeFrom(nodeFrom);

						JxBaseNode nodeTo = (JxBaseNode) selectNodesTo.get(j);
						relation.setNodeTo(nodeTo);

						nodeFrom.addNeighborNode(nodeTo);
						nodeTo.addNeighborNode(nodeFrom);

						nodeFrom.setDegreeIn(nodeFrom.getDegreeIn()+1);
						nodeTo.setDegreeIn(nodeTo.getDegreeIn()+1);

						m_addnodes.add(nodeFrom);
						m_addnodes.add(nodeTo);
					}
				}
			}
			m_trace.save(m_nodes);
			m_trace.save(m_relations);
		}
		
	public void saveNodes() 
	{
		m_trace.save(m_nodes);
	}

	public void traceNodes(int experTime) 
	{
		for (int j = 0; j < m_nodes.count(); j++) 
		{
			m_trace.trace(experTime, m_nodes.get(j));
		}
	}

	/** random packet exchange */
	public void interact(int experTime)// the number of is initial node is 6;
	{ // there 6 relations for each new node,Each time(for each node)
		// we choose one of them to do the packet exchanges
		for (int i = 0; i < m_nodes.count(); i++) // trace the node queue length when time=0;
		{
			m_trace.trace(0, m_nodes.get(i));
		}

		for (int i = 1; i <= experTime; i++) 
		{
			for (int j = 0; j < m_nodes.count(); j++)
			{
				JxBaseNode currentNode = (JxBaseNode) m_nodes.get(j);
				int neighborSize = currentNode.neighborNodeSize();

				int index = m_random.nextInt(neighborSize);
				JxBaseNode neighborNode = (JxBaseNode) currentNode.getNeighborNode(index);

				int queLength1 = currentNode.getLength();
				int queLength2 = neighborNode.getLength();

				int totalength = queLength1 + queLength2;
				int cut = 0;

				if (totalength != 0) 
				{
					cut = m_random.nextInt(totalength);
				}
				currentNode.setLength(cut);
				neighborNode.setLength(totalength - cut);
			}
			this.traceNodes(i);
		}
	}

	/** No BandWidth, No Packet Capacity */
	public void interact1(int experTime)// the number of is initial node is 6;
	{ // there 6 relations for each new node,Each time(for each node)

		for (int i = 0; i < m_nodes.count(); i++) // trace the node queue length when time=0;
		{
			m_trace.trace(0, m_nodes.get(i));
		}

		for (int i = 1; i <= experTime; i++)
		{
			for (int j = 0; j < m_nodes.count(); j++) 
			{
				JxBaseNode currentNode = (JxBaseNode) m_nodes.get(j);
				int neighborSize = currentNode.neighborNodeSize();

				int index = m_random.nextInt(neighborSize);
				JxBaseNode neighborNode = (JxBaseNode) currentNode.getNeighborNode(index);

				int length1 = currentNode.getLength();
				int length2 = neighborNode.getLength();

				int v = 0;
				if (length1 != 0) 
				{
					v = m_random.nextInt(length1);
				}

				currentNode.setLength(length1 - v);
				neighborNode.setLength(length2 + v);
			}
			this.traceNodes(i);
		}
	}

	public JiBaseNode selectNodeTo() 
	{
		int p = m_random.nextInt(m_addnodes.size());
		return m_addnodes.get(p);
	}

	public ArrayList<JiBaseNode> selectNodesTo(int neighborCount) 
	{
		ArrayList<JiBaseNode> neighborNodes = new ArrayList<JiBaseNode>();
		int[] totalNode = new int[10000];
		for (int i = 0; i < neighborCount; i++) 
		{

			int nodeId;
			
			JxBaseNode nodeto = new JxBaseNode();
			do {
				int p = m_random.nextInt(m_addnodes.size());
				nodeto = (JxBaseNode) m_addnodes.get(p);
				nodeId = nodeto.getId();
			} while (totalNode[nodeId] == 1);
			neighborNodes.add(nodeto);
			totalNode[nodeId] = 1;
		}
		return neighborNodes;
	}

	public void saveRelations() 
	{
		m_trace.save(m_relations);
	}

	public void setNodes(JiBaseNodeCollection nodes) 
	{
		m_nodes = (JxScaleFreeNodeCollection) nodes;
	}

	public JxBaseNodeCollection getNodes() 
	{
		return m_nodes;
	}

	public void setRelations(JxBaseRelationCollection relations) 
	{
		m_relations = relations;
	}

	public JxBaseRelationCollection getRelations() 
	{
		return m_relations;
	}

	public void traceNode(int experTime, JiBaseNode node) 
	{
		m_trace.trace(experTime, node);
	}

	public void traceRelaion(int experTime, JiBaseRelation relation) 
	{
		m_trace.trace(experTime, relation);
	}

	// public void generateRelations(int count)
	// {
	// m_nodes.randomize(); //randomize the nodes
	//
	// for(int i=0;i<m_nodes.count();i++)
	// {
	// m_leftnodes.add(i, m_nodes.get(i));
	// }
	// m_relations.generate(count);
	//
	// for(int i=0;i<m_relations.count();i++)
	// {
	// JxBaseRelation relation = (JxBaseRelation)m_relations.get(i);
	//
	// JxBaseNode nodeFrom = new JxBaseNode();
	// JxBaseNode nodeTo =new JxBaseNode();
	//
	// if(i==0)
	// {
	// nodeFrom =(JxBaseNode)m_nodes.get(0);
	// nodeTo =(JxScaleFreeNode)m_nodes.get(1);
	//
	// nodeFrom.addNeighborNode(nodeTo);
	//
	//
	// m_leftnodes.remove(nodeFrom);
	// m_leftnodes.remove(nodeTo);
	// }
	//
	// else{
	// nodeFrom =(JxScaleFreeNode)m_leftnodes.get(0);
	// nodeTo =(JxScaleFreeNode)this.selectNodeTo();
	//
	// m_leftnodes.remove(nodeFrom);
	// }
	// relation.setNodeFrom(nodeFrom);
	// relation.setNodeTo(nodeTo);
	//
	// m_addnodes.add(nodeFrom);
	// m_addnodes.add(nodeTo);
	//
	// nodeFrom.setDegreeIn(nodeFrom.getDegreeIn()+1);
	// nodeTo.setDegreeIn(nodeTo.getDegreeIn()+1);
	// }
	// }
	//
	// public void run(int experTime)
	// {
	// for(int i=0;i<experTime;i++)
	// {
	// for(int j=0;j<m_relations.count();j++)
	// {
	// m_interaction.interact(i,m_relations.get(j), m_trace);
	// }
	//
	// for(int k=0;k<m_nodes.count();k++)
	// {
	// m_trace.trace(i, m_nodes.get(k));
	// }
	// }
	// }
}
