package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.enums.Status;
import game.interfaces.Resettable;

public class Coin extends Item implements Resettable {
    private int value;
    private static final char COIN_CHAR = '$';

    /***
     * Constructor.
     *  @param value the value of this Coin
     */
    public Coin(int value) {
        super("Coin", COIN_CHAR, false);
        this.value = value;
        this.addAction(new PickUpCoinAction(this));
        this.registerInstance();
    }

    public int getValue() {
        return value;
    }

    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESET)){
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
