package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SpeakAction extends Action {
    private String message;
    private Actor target;

    public SpeakAction(String message, Actor target){
        this.message = message;
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return target + ": " + message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + target;
    }

}
