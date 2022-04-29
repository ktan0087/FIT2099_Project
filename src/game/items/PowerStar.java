package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;
import game.interfaces.Tradable;

public class PowerStar extends Item implements Tradable {
    private static final int PRICE = 600;
    private static final char POWER_STAR_CHAR = '*';

    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", POWER_STAR_CHAR, true);
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(this.hasCapability(Status.UNDROPPABLE))
            return null;
        return new DropItemAction(this);
    }
}
