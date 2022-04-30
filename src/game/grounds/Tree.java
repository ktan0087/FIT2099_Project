package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.interfaces.Resettable;

public abstract class Tree extends HighGround implements Resettable {
    protected int age = 0;
    protected static final int GROWTH_AGE = 10;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();    // tree is resettable
    }

    @Override
    public void tick(Location location) {
        // if tree has RESET status and a 50% chance
        if (this.hasCapability(Status.RESET) && Math.random() > 0.5){
            location.setGround(new Dirt()); // convert to dirt
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);   // add RESET status to tree during reset action
    }
}
