package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

public class HealingWater extends Water {
    private static final String NAME = "Healing water";
    private static final int HEAL_HITPOINT = 50;

    @Override
    public void drink(Actor actor) {
        actor.heal(HEAL_HITPOINT);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
