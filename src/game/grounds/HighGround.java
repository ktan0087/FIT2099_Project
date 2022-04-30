package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.enums.Status;
import game.items.Coin;

public abstract class HighGround extends Ground {
    protected double jumpChance;
    protected int fallDamage;
    private static final double MAX_JUMP_CHANCE = 1;
    private static final int MIN_FALL_DAMAGE = 0;

    public HighGround(char displayChar) {
        super(displayChar);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.INVINCIBLE);
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    public String jumped(Actor actor, Location to, GameMap gameMap) {
        String jumpMessage;
        double currentJumpChance = jumpChance;
        int currentFallDamage = fallDamage;

        if (actor.hasCapability(Status.TALL)) {
            currentJumpChance = MAX_JUMP_CHANCE;
            currentFallDamage = MIN_FALL_DAMAGE;
        }
        if (Math.random() < currentJumpChance) {
            gameMap.moveActor(actor, to);
            jumpMessage = "Successfully jumped onto the ";
        } else {
            actor.hurt(currentFallDamage);
            jumpMessage = "Jump failed! Received " + currentFallDamage + " damage from the fall.";
        }
        return jumpMessage;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (!location.containsAnActor() && !actor.hasCapability(Status.INVINCIBLE)) {
            actions.add(new JumpAction(this, direction, location));
        }
        return actions;
    }

    public void setJumpChance(double jumpChance) {
        this.jumpChance = jumpChance;
    }

    public void setFallDamage(int fallDamage) {
        this.fallDamage = fallDamage;
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)) {
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }
    }
}
