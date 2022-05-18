package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.PatrolBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.HashMap;
import java.util.Map;

public class Luigi extends Actor implements Resettable {
    private static final String NAME = "Luigi";
    private static final char DISPLAY_CHAR = '@';

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     */
    public Luigi() {
        super(NAME, DISPLAY_CHAR, Integer.MAX_VALUE);
        this.behaviours.put(2, new PatrolBehaviour());
        this.registerInstance();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)){
            this.removeCapability(Status.RESET);
            if (!map.at(map.locationOf(this).x(), PatrolBehaviour.getMaxYCoord()).containsAnActor()) {
                map.moveActor(this, map.at(map.locationOf(this).x(), PatrolBehaviour.getMaxYCoord()));
            }
            display.println(this + " moves back to original position (" + map.locationOf(this).x() + ", " + PatrolBehaviour.getMaxYCoord() + ")");
            return new DoNothingAction();
        }

        //loop through behaviours and get actions
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        //return action that do nothing
        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}

