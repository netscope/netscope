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

/**
 * This class is the abstract baseclass of all radio models.
 * Radio models describe the radio propagation in space by
 * modelling the radio signal strength fading. There should
 * be only one radio model created for a simulation.
 * 
 * @author Gabor Pap, Gyorgy Balogh, Miklos Maroti
 */
public abstract class JxRadioModel{
	/**
	 * Radio models must implement this method to create
	 * a radio model dependent Neighborhood object. 
	 * One {@link JxRadioModel.Neighborhood} object is created for
	 * each {@link JxNode} object. The newly created
	 * neighborhood object should be empty, because
	 * the {@link JxRadioModel#updateNeighborhoods}
	 * 
	 * @return a new Neighborhood object
	 */	
	public abstract JxNeighborhood createNeighborhood();

	/**
	 * (Re)calculates the neighborhoods of every transciver 
	 * in the network. This operation should be called 
	 * whenever the location of the transcivers changed.  
	 * This operation is extremely expensive and should 
	 * be used sparsely.
	 */
	public abstract void updateNeighborhoods();
	
	/**
	 * The Neighborhood class represents the set of neighboring nodes of a given
	 * node. This class is radio model specific and must be derived in the 
	 * specific radio model.
	 */
	public abstract static class JxNeighborhood{
		/**
		 * This method must call the {@link JxNode#receptionBegin} method of
		 * each of the neighboring nodes. 
		 * 
		 * @param strength The diminished radio strength of the received 
		 * 	signal
		 * @param stream The object represented the data stream.
		 */
		public abstract void beginTransmission(double strength, Object stream);

		/**
		 * It must guarantee that each {@link JxNode#receptionBegin} call is 
		 * matched with a {@link JxNode#receptionEnd} call with the exact same 
		 * parameters.
		 */
		public abstract void endTransmission();
	}
}
