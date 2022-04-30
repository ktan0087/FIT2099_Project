package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Koopa;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Random;

public class Mature extends Tree {
    private static final double DEATH_CHANCE = 0.20;
    private static final double SPAWN_CHANCE = 0.15;
    private static final double JUMP_CHANCE = 0.7;
    private static final int FALL_DAMAGE = 30;

    public Mature() {
        super('T');
        setJumpChance(JUMP_CHANCE);
        setFallDamage(FALL_DAMAGE);
    }

    @Override
    public void tick(Location location) {
        age++;
        if (age % 5 == 0) {
            ArrayList<Location> fertileGrounds = findFertileGrounds(location);
            growSprout(fertileGrounds);
        }
        if (Math.random() < SPAWN_CHANCE && !location.containsAnActor()) {
            location.addActor(new Koopa());
        }
        if (Math.random() < DEATH_CHANCE) {
            location.setGround(new Dirt());
        }
    }

    private ArrayList<Location> findFertileGrounds(Location currentLocation) {
        ArrayList<Location> fertileGrounds = new ArrayList<>();
        for (Exit exit: currentLocation.getExits()) {
            Location potentialFertileGround = exit.getDestination();
            if (potentialFertileGround.getGround().hasCapability(Status.FERTILE) && !potentialFertileGround.containsAnActor()) {
                fertileGrounds.add(potentialFertileGround);
            }
        }
        return fertileGrounds;
    }

    private void growSprout(ArrayList<Location> fertileGrounds) {
        if (fertileGrounds.size() > 0) {
            Random rand = new Random();
            Location chosenGround = fertileGrounds.get(rand.nextInt(fertileGrounds.size()));
            chosenGround.setGround(new Sprout());
        }
    }

    @Override
    public String toString() {
        return "Mature Tree";
    }
}
