package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

public abstract class Fountain extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.HOSTILE_TO_PLAYER);
    }
}
