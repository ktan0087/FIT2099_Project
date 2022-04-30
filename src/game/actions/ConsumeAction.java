package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

public class ConsumeAction extends Action {

    private ConsumableItem consumableItem;

    public ConsumeAction(ConsumableItem consumableItem) {
        this.consumableItem = consumableItem;
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

        actor.removeItemFromInventory(consumableItem);
        consumableItem.consumed(actor);
        return actor + " consumed " + consumableItem;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumableItem;
    }
}