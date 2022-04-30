package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.interfaces.Consumable;
import game.interfaces.Tradable;

public class PowerStar extends Item implements Tradable, Consumable {
    private static final int PRICE = 600;
    private static final String NAME = "Power Star";
    private static final char POWER_STAR_CHAR = '*';
    private int turn = 10;

    /***
     * Constructor.
     */
    public PowerStar() {
        super(NAME, POWER_STAR_CHAR, true);
        this.addToConsumableItemManager();
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
    public void tick(Location currentLocation, Actor actor){
        turn -= 1;
        if (turn < 1){
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    public void tick(Location currentLocation){
        turn -= 1;
        if (turn < 1){
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void consumeMagicalItems(Actor consumedActor) {
        consumedActor.addCapability(Status.INVINCIBLE);
        consumedActor.heal(200);
    }

    @Override
    public String toString(){
        return "Power Star";
    }
}
