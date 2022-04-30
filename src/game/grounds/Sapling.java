package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

public class Sapling extends Tree {
    private static final double SPAWN_CHANCE = 0.1;

    public Sapling() {
        super('t');
    }

    @Override
    public void tick(Location location) {
        age++;
        if (age == GROWTH_AGE) {
            location.setGround(new Mature());
        }
        if (Math.random() < SPAWN_CHANCE && !location.containsAnActor()) {
            location.addItem(new Coin(20));
        }
    }

    @Override
    public String toString() {
        return "Sapling Tree";
    }
}
