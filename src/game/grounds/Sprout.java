package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Goomba;

public class Sprout extends Tree {
    private static final double SPAWN_CHANCE = 0.1;

    public Sprout() {
        super('+');
    }

    @Override
    public void tick(Location location) {
        age++;
        if (age == GROWTH_AGE) {
            location.setGround(new Sapling());
        }
        if (Math.random() < SPAWN_CHANCE && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
    }

    @Override
    public String toString() {
        return "Sprout Tree";
    }
}
