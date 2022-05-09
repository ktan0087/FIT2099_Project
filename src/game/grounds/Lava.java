package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * A class that represents a blazing fire ground that inflicts damage when the player steps on it.
 * @author Mark Manlangit
 * @version 1.0
 * @since 09-05-2022
 */
public class Lava extends Ground {

    /**
     * A constructor for the Lava class
     */
    public Lava() {
        super('L'); // display character
    }

    /**
     * A method used to return the string representation of the Lava class
     * @return The string representation of the Lava class
     */
    @Override
    public String toString() {
        return "Lava";
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.HOSTILE_TO_PLAYER);
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            location.getActor().hurt(15);
        }
    }
}
