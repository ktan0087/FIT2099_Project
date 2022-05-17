package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * A class that represents bare dirt.
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class Dirt extends Ground {

	//TODO: when ground is burning
	/**
	 * Number of turns to keep the ground on fire
	 */
	private int burningTurn = 3;
	/**
	 * Create display object to print message to console
	 */
	Display display = new Display();

	/**
	 * A constructor for the Dirt class
	 */
	public Dirt() {
		super('.'); // display character
		this.addCapability(Status.FERTILE); // add fertile status to Dirt grounds
	}

	@Override
	public void tick(Location location){
		if (location.getGround().hasCapability(Status.IS_BURNING) && burningTurn > 0){
			if (location.containsAnActor()){
				edu.monash.fit2099.engine.actors.Actor target = location.getActor();
				if (target.hasCapability(Status.HOSTILE_TO_ENEMY) || target.hasCapability(Status.HOSTILE_TO_ENEMY)){
					display.println(target + " is on fire, receives 20 damage! ");
					target.hurt(20);
				}
			}
			burningTurn--;
		}
		else if (location.getGround().hasCapability(Status.IS_BURNING) && burningTurn == 0){
			location.getGround().removeCapability(Status.IS_BURNING);
			display.println(this + " has stopped burning ");
			burningTurn = 3;
		}
		super.tick(location);
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
