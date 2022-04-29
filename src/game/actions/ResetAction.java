package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ResetManager;

public class ResetAction extends Action {
    private static boolean resetFlag = false;

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        resetFlag = true;
        return "Game has been reset";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reset game";
    }

    @Override
    public String hotkey() {
        return "r";
    }

    public static boolean getResetFlag(){
        return resetFlag;
    }
}

