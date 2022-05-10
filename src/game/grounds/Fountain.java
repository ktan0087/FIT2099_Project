package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

public abstract class Fountain extends Ground {
    private static final int MAX_CAPACITY = 10;
    private int slot;
    private int rechargeTimer = 5;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        this.slot = MAX_CAPACITY;
    }

    public int getSlot(){
        return slot;
    }

    public void subtractSlot(){
        slot--;
    }

    private void rechargeFountain(){
        slot = MAX_CAPACITY;
        rechargeTimer = 5;
    }

    public int getMaxCapacity(){
        return MAX_CAPACITY;
    }


    @Override
    public void tick(Location location) {
        if (slot == 0){
            rechargeTimer--;
            if (rechargeTimer < 0){
                rechargeFountain();
            }
        }
    }

}
