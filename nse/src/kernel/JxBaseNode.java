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
 
package nse.kernel;

/**
 * This class is the base class of all nodes. Nodes are entities in a simulator
 * which act on behalf of themselves, they also have some basic attributes like 
 * location. Nodes also take part in radio transmissions, they initiate 
 * transmissions and receive incomming radio messages.
 * 
 * @author Gabor Pap, Gyorgy Balogh, Miklos Maroti
 */
public abstract class JxBaseNode {
	/**
	 * Nodes of the Simulator are linked together in a single linked list. 
	 * This points to the next element.
	 */
	public JxBaseNode nextNode = null;
	
	/** A reference to the simulator in which the Node exists. */
	public JxBaseSimulator simulator;

	/**
	 * The id of the node. It is allowed that two nodes have
	 * the same id in the simulator.
	 */
	protected int id;
	
	/**
	 * Parameterized constructor, sets the simulator and creates an initial 
	 * neighborhood using the RadioModel as a factory.
	 * 
	 * @param sim the Simulator
	 * @param radioModel the RadioModel used to create the nodes neighborhood
	 */
	//public JxBaseNode(JxBaseSimulator sim, JxRadioModel radioModel){
	public JxBaseNode(JxBaseSimulator sim ){
		this.simulator = sim;
	} 
	
	/**
	 * Sets the id of the node. It is allowed that two nodes have the
	 * same id for experimentation.
	 * 
	 * @param id the new id of the node.
	 */
	public void setId( int id ){
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	/**
	 * @return simply returns the simulator in which this Node exists
	 */
	public JxBaseSimulator getSimulator(){
		return simulator;
	}
	
	public JxBaseNode nextNode(){
		return nextNode;
	}

	/**
	 * The default implementation of the display function, calls the display 
	 * funtions of the member applications.
	 * 
	 * @param disp the main display provided by the Simulator
	 */
	public void trace(JiBaseTrace trace){
	}
}
