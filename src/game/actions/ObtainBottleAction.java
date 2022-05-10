package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

public class ObtainBottleAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Bottle());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains bottle from Toad";
    }
}
