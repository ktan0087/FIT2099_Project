package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

public class Sapling extends Tree {
    private static final double SPAWN_CHANCE = 0.1;
    private static final double JUMP_CHANCE = 0.8;
    private static final int FALL_DAMAGE = 20;

    public Sapling() {
        super('t');
        setJumpChance(JUMP_CHANCE);
        setFallDamage(FALL_DAMAGE);
    }

    @Override
    public void tick(Location location) {
        age++;
        if (age == GROWTH_AGE) {
            location.setGround(new Mature());
        }
        if (Math.random() < SPAWN_CHANCE) {
            location.addItem(new Coin(20));
        }
    }

    @Override
    public String toString() {
        return "Sapling Tree";
    }
}
