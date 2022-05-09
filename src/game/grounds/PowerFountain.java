package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
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

        if (location.containsAnActor()) {
            actions.add(new RefillAction(new PowerWater()));
        }

        return actions;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
