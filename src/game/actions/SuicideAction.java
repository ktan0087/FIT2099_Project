package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class SuicideAction extends Action {
    private Location suicideLocation;

    public SuicideAction(Location location){
        this.suicideLocation = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " at (" + suicideLocation.x() + ", " + suicideLocation.y() + ") is killed";
    }
}