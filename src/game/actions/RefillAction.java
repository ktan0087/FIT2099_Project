package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Fountain;
import game.items.Bottle;
import game.waters.Water;

public class RefillAction extends Action {
    private Water water;
    private Fountain fountain;

    public RefillAction(Water water, Fountain fountain){
        this.water = water;
        this.fountain = fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle.fillWater(water);

        fountain.subtractSlot();

        return actor + " refill " + water;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + fountain + " (" + fountain.getSlot() + "/" + fountain.getMaxCapacity() + ")";
    }
}
