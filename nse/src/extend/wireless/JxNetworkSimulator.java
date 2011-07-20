/*
 * Copyright (c) 2003, Vanderbilt University
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written agreement is
 * hereby granted, provided that the above copyright notice, the following
 * two paragraphs and the author appear in all copies of this software.
 * 
 * IN NO EVENT SHALL THE VANDERBILT UNIVERSITY BE LIABLE TO ANY PARTY FOR
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
 * OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE VANDERBILT
 * UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * THE VANDERBILT UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
 * ON AN "AS IS" BASIS, AND THE VANDERBILT UNIVERSITY HAS NO OBLIGATION TO
 * PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * Author: Gyorgy Balogh, Gabor Pap, Miklos Maroti
 * Date last modified: 02/09/04
 */

package extend.wireless;

import java.lang.reflect.Constructor;
import kernel.JxBaseSimulator2;

/**
 * This class is the heart of Prowler, as this is the event based scheduler, or
 * simulator.
 * 
 * @author Gyorgy Balogh, Miklos Maroti, Gabor Pap (gabor.pap@vanderbilt.edu)
 */
public class JxNetworkSimulator extends JxBaseSimulator2{
	
	/** Needed for the display, stores the maximum of both the x and y coordinates */
	double maxCoordinate = 0;
	
	/**
	 * Adds a single Node to the simulator.
	 * 
	 * @param app the Node to be added
	 */
	protected void addNode(JxNode node) {	
		super.addNode( node );
		if (node.getX() > maxCoordinate)
			maxCoordinate = node.getX();
		if (node.getY() > maxCoordinate)
			maxCoordinate = node.getY();
	}
	
	/**
	 * Creates a node in the simulator at the given position with the given id.
	 * WARNING: Please call {@link JxRadioModel#updateNeighborhoods} after you 
	 * added all nodes to the system.
	 * 
	 * @param nodeClass the class of motes we want to use during the experiment
	 * @param radioModel the radioModel to use
	 * @param nodeId the id of the node
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 * @return returns the node created
	 * @throws Exception throws various exceptions due to the generic way of 
	 *         constructing the classes
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JxNode createNode( Class nodeClass, JxRadioModel radioModel, int nodeId, double x, double y, double z) throws Exception{
		Constructor c = nodeClass.getConstructor( new Class[] { JxNetworkSimulator.class, JxRadioModel.class } );
		JxNode node = (JxNode)c.newInstance( new Object[] { this, radioModel } );
		node.setPosition( x, y ,z );
		node.setId( nodeId );
		addNode(node);
		return node;
	}
    
	/**
	 * Creates a given number of motes dispersed randomly over a given square area.
	 * WARNING: Please call {@link JxRadioModel#updateNeighborhoods} after you 
	 * added all nodes to the system.
	 * 
	 * @param nodeClass the class of motes we want to use during the experiment
	 * @param radioModel the radioModel to use
	 * @param startNodeId the first node id of the created nodes
	 * @param nodeNum the number of motes
	 * @param areaWidth the size of one side of the square shaped area
     * @param maxElevation the maximum node elevation (z coordinate)
	 * @return returns a reference to the first node in the simulator
	 * @throws Exception throws various exceptions due to the generic way of 
	 *         constructing the classes
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JxNode createNodes( Class nodeClass, JxRadioModel radioModel, int startNodeId, int nodeNum, double areaWidth, double maxElevation ) throws Exception{        
		Constructor c = nodeClass.getConstructor( new Class[] { JxNetworkSimulator.class, JxRadioModel.class } );
		for( int i=0; i<nodeNum; ++i ){
			JxNode node = (JxNode)c.newInstance( new Object[] { this, radioModel } );
			node.setPosition( areaWidth * random.nextDouble(), areaWidth * random.nextDouble(), maxElevation * random.nextDouble() );
			node.setId( startNodeId + i );
			addNode( node );            
		}
		return (JxNode)firstNode();
	}
}
