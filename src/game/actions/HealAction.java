package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class HealAction extends Action {
    private Actor target;
    private static final int HEAL_POINT = 5;

    public HealAction(Actor target){
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        target.heal(HEAL_POINT);
        return actor + " heal " + target + " (" + HEAL_POINT + "hp)";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " heal " + target;
    }
}
