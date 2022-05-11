package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

public class HealingWater extends Water {
    private static final String NAME = "Healing water";
    private static final char DISPLAY_CHAR = '~';
    private static final int HEAL_HITPOINT = 50;

    /***
     * Constructor.
     */
    public HealingWater() {
        super(NAME, DISPLAY_CHAR, false);
    }


    @Override
    public void consumeMagicalItems(Actor actor) {
        actor.heal(HEAL_HITPOINT);
    }

    @Override
    public String toString() {
        return NAME;
    }

}
