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

import kernel.JxBaseNode2;
import kernel.JiBaseTrace;
import extend.wireless.JxRadioModel.JxNeighborhood;

/**
 * This class is the base class of all nodes. Nodes are entities in a simulator
 * which act on behalf of themselves, they also have some basic attributes like 
 * location. Nodes also take part in radio transmissions, they initiate 
 * transmissions and receive incomming radio messages.
 * 
 * @author Gabor Pap, Gyorgy Balogh, Miklos Maroti
 */
public abstract class JxNode extends JxBaseNode2 {
	/**
	 * The applications of the TinyOs node are linked together
	 * in a single linked list. This points to the first,
	 * and then {@link JxWirelessApplication#nextApplication} to the next. 
	 */
	protected JxWirelessApplication firstApplication = null;

	/** 
	 * This field defines the relative strength of a mote. If it is set to
	 * a high value for a given mote it can supress other motes.
	 */
	double maxRadioStrength = 100;	

	/** positions x in meters (not that it metters much) */
	protected double    x = 0;

	/** positions y in meters (not that it metters much) */
	protected double    y = 0;
	
	/** positions z in meters (not that it metters much) */
	protected double    z = 0;
    
	/**
	 * The neighborhood of this node, meaning all the neighboring nodes which 
	 * interact with this one.
	 */
	private JxNeighborhood neighborhood;

	/**
	 * Parameterized constructor, sets the simulator and creates an initial 
	 * neighborhood using the RadioModel as a factory.
	 * 
	 * @param sim the Simulator
	 * @param radioModel the RadioModel used to create the nodes neighborhood
	 */
	public JxNode(JxNetworkSimulator sim, JxRadioModel radioModel){
		super(sim);
		neighborhood = radioModel.createNeighborhood();
	} 
	
	/**
	 * A getter method used by the RadioModels to manipulate neighborhood of nodes. 
	 */
	public JxNeighborhood getNeighborhood(){
		return neighborhood;
	}

	/**
	 * Calculates the square of the distance between two nodes. This method is 
	 * used by the radio models to calculate the fading of radio signals.
	 * 
	 * @param other The other node
	 * @return The square of the distance between this and the other node
	 */		
	public double getDistanceSquare(JxNode other){
		return (x-other.x)*(x-other.x) + (y-other.y)*(y-other.y) + (z-other.z)*(z-other.z);
	} 

	/**
	 * Returns the maximum radio strength this node will ever transmit with. 
	 * This must be a positive number.
	 * 
	 * @return the maximum transmit radio power
	 * @see JxNode#beginTransmission
	 */
	public double getMaximumRadioStrength(){
		return maxRadioStrength;
	}
	
	/**
	 * A setter function for the {@link JxNode#maxRadioStrength}
	 * field.
	 * 
	 * @param d the desired new transmit strength of this mote
	 */
	public void setMaximumRadioStrength(double d) {
		maxRadioStrength = d;
	}
	
	/**
	 * Called by the drived class implementing the MAC layer when
	 * radio transmission is initiated. This method will call the
	 * {@link JxNode#receptionBegin} method in each of the neighboring
	 * nodes with the same <code>stream</code> object but with
	 * a diminished radio signal strength. Derived classes must
	 * avoid nested transmissions. 
	 * 
	 * @param strength The signal strength of the transmission. This
	 * must be positive and less than or equal to the maximum transmit
	 * strength.
	 * @param stream The object that is beeing sent. This parameter
	 * cannot be <code>null</code> and two nodes cannot send
	 * the same object at the same time.
	 * @see JxNode#getTransmitStrengthMultiplicator
	 */		
	protected final void beginTransmission(double strength, Object stream) {
		neighborhood.beginTransmission(strength, stream);
	}

	/**
	 * Called by the derived class implementing the MAC layer when
	 * radio transmission is finished. This method will call the
	 * {@link JxNode#receptionEnd} method in each  of the neighboring
	 * nodes with the same <code>stream</code> object but
	 * with a diminished radio strength. Derived classes must make
	 * sure that this method is invoked only once for each matching
	 * {@link JxNode#beginTransmission} call.
	 */
	protected final void endTransmission() {
		neighborhood.endTransmission();
	}

	/**
	 * Called for each transmission of a neighboring node by the 
	 * radio model. The <code>recpetionBegin</code> and 
	 * <code>receptionEnd</code> calles can be nested or interleaved, 
	 * but they are always coming in pairs. The derived class 
	 * implementing the MAC protocol must select the transmission 
	 * that it wants to receive based on some heuristics on the 
	 * radio signal stregths. Note that these methods are called 
	 * even when the nodes is currently transmitting. 
	 * 
	 * @param strength The radio strength of the incoming signal.
	 * @param stream The object representing the incoming data.
	 * This stream object is never <code>null</code>.
	 * @see #receptionEnd
	 */
	public abstract void receptionBegin(double strength, Object stream);

	/**
	 * Called for each transmission of a neighboring node by the 
	 * radio model. This method is always invoked after a corresponding
	 * {@link #receptionBegin} method invokation with the exact same 
	 * parameters.
	 * 
	 * @param strength The radio strength of the incoming signal.
	 * @param stream The received object message.
	 */
	public abstract void receptionEnd(double strength, Object stream);
	
	/**
	 * Sends out a radio message. If the node is in receiving mode the sending is 
	 * postponed until the receive is finished. This method should behave
	 * exactly as the SendMsg.send command in TinyOS.
	 * 
	 * @param message the message to be sent
	 * @param app the application sending the message
	 * @return If the node is in sending state it returns false otherwise true.
	 */
	public abstract boolean sendMessage(Object message, JxWirelessApplication app);
	
	/**
	 * Sets the position of the mote in space. Please call the 
	 * {@link JxRadioModel#updateNeighborhoods} to update the network topology 
	 * information before starting the simulation.
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @param z the z position
	 */
	public void setPosition( double x, double y, double z ){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * A getter function for position X.
	 * 
	 * @return Returns the x coordinate of the node.
	 */
	public double getX(){
		return x;
	}

	/**
	 * A getter function for position Y.
	 * 
	 * @return Returns the y coordinate of the node.
	 */
	public double getY(){
		return y;
	}

	/**
	 * A getter function for position Z.
	 * 
	 * @return Returns the z coordinate of the node.
	 */
	public double getZ(){
		return z;
	}
	
	/**
	 * This function is part of the application management. Adds an 
	 * {@link JxWirelessApplication} to the list of applications running on this Node.
	 * Note that applications on a node represent TinyOS components, so do not 
	 * try to solve all your problems in a derived class of Node. Also note
	 * that there can be only one instance of an Application class running on 
	 * every Node, unlike components in TinyOS! Yes, this is a reasonable
	 * constraint that makes message demultiplexing easier.   
	 * 
	 * @param app the Application 
	 */
	public void addApplication(JxWirelessApplication app){
		app.nextApplication = firstApplication;
		firstApplication = app;
	}

	/**
	 * Visiting the elements of the application list, it returns the first with
	 * the given application class.
	 * 
	 * @param appClass the class that identifies the needed application for us
	 * @return Returns the application instance running on this node
	 */
	protected JxWirelessApplication getApplication(Class appClass){
		JxWirelessApplication tempApp = firstApplication;
		while (tempApp != null && tempApp.getClass() != appClass )
			tempApp = tempApp.nextApplication;
		return tempApp;
	}
	
	/**
	 * The default implementation of the display function, calls the display 
	 * funtions of the member applications.
	 * 
	 * @param disp the main display provided by the Simulator
	 */
	public void display(JiBaseTrace disp){
		JxWirelessApplication tempApp = firstApplication;
		while(tempApp != null){
			tempApp.display(disp);
			tempApp = tempApp.nextApplication;
		} 
	}
	
}
