package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.interfaces.Resettable;

public class Tree extends Ground implements Resettable {

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('+');
        this.registerInstance();
    }

    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET) && Math.random() > 0.5){
            location.setGround(new Dirt());
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
