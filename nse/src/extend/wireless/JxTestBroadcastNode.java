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
 
package nse.ns.wireless;

import java.awt.Color;
import java.awt.Graphics;

import nse.kernel.JiBaseTrace;
import nse.kernel.JxBaseEvent;

/**
 * This is a sample application, it shows a way of utilizing the Prowler 
 * simulator. This is a broadcast application, where a mote in the middle of the 
 * field broadcasts a message which is further broadcasted by all the recepients.
 * Please note that this is not the only way of writing applications, this is 
 * just an example.
 * 
 * @author Gabor Pap, Gyorgy Balogh, Miklos Maroti
 */
public class JxTestBroadcastNode extends JxMica2Node{

	/** This field is true if this mote rebroadcasted the message already. */
	boolean sent = false;

	/** This field stores the mote from which the message was first received. */
	private JxNode parent = null; 

	/**
	 * This extension of the {@link JxApplication} baseclass does everything we 
	 * expect from the broadcast application, simply forwards the message once,
	 * and that is it. 
	 */
	public class BroadcastApplication extends JxApplication{
		
		/**
		 * @param node the Node on which this application runs.
		 */
		public BroadcastApplication(JxNode node) {
			super(node);
		}
    
		/**
		 * Stores the sender from which it first receives the message, and passes 
		 * the message.
		 */
		public void receiveMessage(Object message ){
			if (parent == null){
				parent = parentNode;
				sendMessage( message );
			}            
		}    

		/**
		 * Sets the sent flag to true. 
		 */
		public void sendMessageDone(){
			sent = true;                        
		}
	}

	public JxTestBroadcastNode(JxNetworkSimulator sim, JxRadioModel radioModel) {
		super(sim, radioModel);
	}
    
	/**
	 * Draws a filled circle, which is: <br>
	 *  - blue if the node is sending a message <br>
	 *  - red if the node received a corrupted message <br>
	 *  - green if the node received a message without problems <br>
	 *  - pink if the node sent a message <br>
	 *  - and black as a default<br>
	 * It also draws a line between mote and its parent, which is another mote
	 * who sent the message first to this.
	 */ 
	public void trace(JxDisplayTrace trace){
		Graphics g = trace.getGraphics();
		int      x = trace.x2ScreenX(this.x);
		int      y = trace.y2ScreenY(this.y);
               
		if( sending ){                    
			g.setColor( Color.blue );
		}else if( receiving ){
			if( corrupted )
				g.setColor( Color.red );
			else
				g.setColor( Color.green );                            
		}else{
			if( sent )
				g.setColor( Color.pink );
			else
				g.setColor( Color.black );
		}
		g.fillOval( x-3, y-3, 5, 5 );
		if( parent != null ){
			g.setColor( Color.black );
			int x1 = trace.x2ScreenX(parent.getX());
			int y1 = trace.y2ScreenY(parent.getY());
			g.drawLine(x,y,x1,y1);
		}
	}        

	/**
	 * Starts up a simulator with a ROOT in the middle of a 300 by 300 meters
	 * field with 1000 motes and runs it in real time mode.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{       
		System.out.println("creating nodes...");        
    	JiBaseTrace trace = null;
		JxNetworkSimulator sim = new JxNetworkSimulator();
		
		// creating the desired radio model, uncomment the one you need 
		JxGaussianRadioModel radioModel = new JxGaussianRadioModel(sim);
		//RayleighRadioModel radioModel = new RayleighRadioModel(sim);

		// creating the node in the middle of the field, and adding a broadcast
		// application
        long time0 = System.currentTimeMillis();
		JxTestBroadcastNode root = (JxTestBroadcastNode)sim.createNode( JxTestBroadcastNode.class, radioModel, 1, 15, 15, 0);
		BroadcastApplication bcApp = root.new BroadcastApplication(root);

		// creating all the other nodes
		JxNode tempNode = sim.createNodes( JxTestBroadcastNode.class, radioModel, 2, 1000, 300, 5);
		while (tempNode != null){
			BroadcastApplication tempBcApp = ((JxTestBroadcastNode)tempNode).new BroadcastApplication(tempNode);
			tempNode = (JxNode)tempNode.nextNode();
		}
		// This call is a must, please do not forget to call it whenever the mote 
		// field is set up
		radioModel.updateNeighborhoods();
        
        System.out.println("creation time: " + (System.currentTimeMillis()-time0) + " millisecs" );
        final long time1 = System.currentTimeMillis();
	          
	    System.out.println("start simulation");
                	               
		root.sendMessage( "test message", bcApp );
        
        boolean realTime = false;
        if( realTime )
        {
        	int maxCoordinate = 1000;
        	trace = new JxDisplayTrace( sim, maxCoordinate );
            sim.runWithTraceInRealTime( trace );
        }
        else
        {
            // run as fast as possible and measure dump execution time
            JxBaseEvent event = new JxBaseEvent()
            {
                public void execute()
                {
                    System.out.println("execution time: " + (System.currentTimeMillis()-time1) + " millisecs" );
                }
            };
            event.time = JxNetworkSimulator.ONE_SECOND * 1000;
            sim.addEvent(event);
            sim.run(20000);
        }       
	}
}

