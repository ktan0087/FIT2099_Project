package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents bare dirt.
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class Dirt extends Ground {

	/**
	 * A constructor for the Dirt class
	 */
	public Dirt() {
		super('.'); // display character
		this.addCapability(Status.FERTILE); // add fertile status to Dirt grounds
	}

	/**
	 * A method used to return the string representation of the Dirt class
	 * @return The string representation of the Dirt class
	 */
	@Override
	public String toString() {
		return "Dirt";
	}
}
