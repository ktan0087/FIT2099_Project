package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;
import game.interfaces.Tradable;

public class SuperMushroom extends Item implements Tradable {
    private static final int PRICE = 400;
    private static final String NAME = "Super Mushroom";
    private static final char SUPER_MUSHROOM_CHAR = '^';

    /***
     * Constructor.
     */
    public SuperMushroom() {
        super(NAME, SUPER_MUSHROOM_CHAR, true);
        this.registerTradableInstance();
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