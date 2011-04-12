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

import kernel.JiBaseTrace;

/**
 * This class represents one application that sends and receives
 * messages with a given fixed application id. You do not have to 
 * specify this application ID because the class object of your
 * derived class is used for this purpose. The {@link JxNode} class
 * demultiplexes the messages and notifies the appropriate 
 * applications. Note that the user of this framework can only have
 * one application of each "type" running on a Node, that is what makes
 * demultiplexing simple.  
 * 
 * @author Gyorgy Balogh, Gabor Pap, Miklos Maroti
 */
public abstract class JxWirelessApplication {
	
	/** 
	 * The applications objects of a node are linked together 
	 * in a list. This reference points to the next one in the list. 
	 */
	public JxWirelessApplication nextApplication;
	
	/** The parent {@link JxNode} this application belongs to. */
	JxNode node;

	/** Returns the {@link JxNode} this application belongs to. */
	public JxNode getNode(){
		return node;
	}

	/**
	 * The constructor for applications. Makes both the application and the Node
	 * reference to each other.
	 * 
	 * @param node The parent {@link JxNode} of this application
	 */
	public JxWirelessApplication(JxNode node){
		this.node = node;
		node.addApplication(this);
	}

	/**
	 * Fired when a new message is arrived for this application.
	 * The semantics is as of the ReceiveMsg.receive() event
	 * of TinyOS, except we do not manage the lifecycle of messages.
	 * It is important to note that the received message is simply
	 * a reference to the message that has been sent by the sender
	 * node, so it should not be modified.  
	 *
	 * @param message The message that arrived.
	 */
	public void receiveMessage(Object message){
	}
	
	/**
	 * Sends a message via the radio to neighboring nodes where
	 * the {@link JxWirelessApplication#receiveMessage} method will be fired in the 
	 * application instance of the same derived type.
	 * This method has the same semantics as SendMsg.send() in TinyOS.
	 * 
	 * @param message The message to be sent.
	 * @return Returns <code>true</code> if the message is accepted for 
	 * 	transmissions, in which case {@link JxWirelessApplication#sendMessageDone}
	 * 	will be called eventually, or <code>false</code> if a message 
	 * (maybe from another Application) is under transmission and
	 *  this message is not accepted.
	 * @see #sendMessageDone 
	 */

	public final boolean sendMessage(Object message){
		return node.sendMessage(message, this);
	}
	
	/**
	 * Signaled when the message posted is sent. This method
	 * is called only if {@link JxWirelessApplication#sendMessage} returned <code>true</code>. 
	 * This has the same semantics as SendMsg.sendDone() in TinyOS.
	 * 
	 * @see #sendMessage
	 */
	public void sendMessageDone(){
	}
	
	/**
	 * Every application has the ability to display its status, though it is not
	 * constrained in any way. Using the {@link JxNode#getX()}, {@link JxNode#getY()}
	 * {@link JxDisplayTrace#x2ScreenX(double)} and {@link JxDisplayTrace#y2ScreenY(double)} functions 
	 * might help positioning a sign.
	 * 
	 * @param disp
	 */
	public void display(JiBaseTrace disp){
	}
}
