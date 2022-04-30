package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import game.enums.Status;
import game.interfaces.Tradable;

public class SuperMushroom extends ConsumableItem implements Tradable {
    private static final int PRICE = 400;
    private static final String NAME = "Super Mushroom";
    private static final char SUPER_MUSHROOM_CHAR = '^';

    /***
     * Constructor.
     */
    public SuperMushroom() {
        super(NAME, SUPER_MUSHROOM_CHAR, true);
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void addCapabilityDuringTrading() {
        this.addCapability(Status.UNDROPPABLE);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(this.hasCapability(Status.UNDROPPABLE))
            return null;
        return new DropItemAction(this);
    }

    @Override
    public void consumed(Actor consumedActor) {
        consumedActor.addCapability(Status.TALL);
        consumedActor.increaseMaxHp(50);
    }

    @Override
    public String toString(){
        return "SuperMushroom";
    }
}
