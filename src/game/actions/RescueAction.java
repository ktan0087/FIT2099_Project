package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

public class RescueAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * Constructor.
     *
     * @param target    target to be destroyed

     */
    public RescueAction(Actor target) {
        this.target = target;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return output, a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output;
        output = "Congratulations! " + target + " has been saved.";
        return output;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlock " + target + "'s handcuffs.";
    }
}
