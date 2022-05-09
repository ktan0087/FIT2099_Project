package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.waters.Water;

public class RefillAction extends Action {
    private Water water;

    public RefillAction(Water water){
        this.water = water;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle.fillWater(water);

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refill " + water;
    }
}
