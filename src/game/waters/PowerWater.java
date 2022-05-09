package game.waters;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;

public class PowerWater extends Water {
    private static final String NAME = "Power water";

    @Override
    public void drink(Actor actor) {
        actor.addCapability(Status.POWER);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
