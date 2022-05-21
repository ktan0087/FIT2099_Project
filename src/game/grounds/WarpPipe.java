package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.enemies.PiranhaPlant;
import game.enums.Status;
import game.interfaces.Resettable;

public class WarpPipe extends HighGround implements Resettable {
    /**
     * The jump chance related to a WarpPipe (used for jumping)
     */
    private static final double JUMP_CHANCE = 1.0;
    /**
     * The fall damage related to a WarpPipe (used for jumping)
     */
    private static final int FALL_DAMAGE = 0;
    /**
     * The age of the WarpPipe (used for spawning Piranha Plant)
     */
    private int age;

    /**
     * A constructor for the Warp pipe class
     */
    public WarpPipe() {
        super('C'); // display character
        setJumpChance(JUMP_CHANCE); // set jump chance of High Ground parent class based on a Warp pipe's jump chance
        setFallDamage(FALL_DAMAGE); // set fall damage of High Ground parent class based on a Warp pipe's fall damage
        age = 0;
        this.addCapability(Status.INDESTRUCTIBLE);
        this.registerInstance();
    }

    @Override
    public void tick(Location location) {
        age++;
        if (age == 1) {
            if (!location.containsAnActor()) {
                location.addActor(new PiranhaPlant());
            }
        }
        super.tick(location); // call parent tick, used for reset
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = super.allowableActions(actor, location, direction);
        if (location.containsAnActor() && location.getActor() == actor) {
            actions.add(new TeleportAction(location));
        }
        return actions;
    }

    /**
     * A method used to return the string representation of the WarpPipe class
     * @return The string representation of the WarpPipe class
     */
    @Override
    public String toString() {
        return "Warp Pipe";
    }

    @Override
    public void resetInstance() {
        this.age = 0;
    }



}
