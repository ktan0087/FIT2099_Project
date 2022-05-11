package game.waters;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;

public class PowerWater extends Water {
    private static final String NAME = "Power water";
    private static final char DISPLAY_CHAR = '`';

    /***
     * Constructor.
     */
    public PowerWater() {
        super(NAME, DISPLAY_CHAR, false);
    }

    @Override
    public void consumeMagicalItems(Actor actor) {
        actor.addCapability(Status.POWER);
    }

    @Override
    public String toString() {
        return NAME;
    }

}
