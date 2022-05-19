package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.HealAction;
import game.interfaces.Behaviour;

public class HealBehaviour implements Behaviour {
    private Actor target;

    public HealBehaviour(Actor target){
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new HealAction(target);
    }
}
