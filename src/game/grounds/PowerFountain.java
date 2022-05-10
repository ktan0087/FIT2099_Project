package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.enums.Status;
import game.waters.PowerWater;

public class PowerFountain extends Fountain {
    private static final char DISPLAY_CHAR = 'A';
    private static final String NAME = "Power Fountain";

    /**
     * Constructor.
     */
    public PowerFountain() {
        super(DISPLAY_CHAR);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (this.getSlot() > 0) {
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.NON_REMOVABLE_FROM_INVENTORY) && location.getActor() == actor) {
                actions.add(new RefillAction(new PowerWater(), this));
            }
        }

        return actions;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
