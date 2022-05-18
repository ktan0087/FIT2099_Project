package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BlinkAction;
import game.enums.Status;

public class BlinkingTower extends HighGround {
    /**
     * The jump chance related to a WarpPipe (used for jumping)
     */
    private static final double JUMP_CHANCE = 1.0;
    /**
     * The fall damage related to a WarpPipe (used for jumping)
     */
    private static final int FALL_DAMAGE = 0;

    /**
     * Constructor.
     */
    public BlinkingTower() {
        super('?');
        setJumpChance(JUMP_CHANCE);
        setFallDamage(FALL_DAMAGE);
        this.addCapability(Status.BLINKABLE);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (location.containsAnActor() && location.getActor() == actor){
            actions.add(new BlinkAction());
        }
        return actions;
    }

    @Override
    public String toString() {
        return "Blinking Tower";
    }
}
