package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.interfaces.Consumable;
import game.managers.ConsumableItemManager;

public class ConsumeAction extends Action {

    private Consumable consumable;

    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //remove consumable from inventory
        actor.removeItemFromInventory((Item) consumable);

        //invoke consumeMagicalItems method from consumable interface
        consumable.consumeMagicalItems(actor);

        //remove consumed items from consumableList
        ConsumableItemManager.getInstance().removeConsumableItem((Item) consumable);

        //
        actor.hasCapability(Status.TALL);

        //return output to console
        return actor + " consume Magical Items: " + consumable;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
