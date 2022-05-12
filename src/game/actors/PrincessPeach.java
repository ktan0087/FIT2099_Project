package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class PrincessPeach extends Actor {

    /**
     * The name is set as a constant "PrincessPeach" / whatever you think is appropriate
     */
    private static final String NAME = "PrincessPeach";

    /**
     * The display character of toad is set as a constant 'P'
     */
    private static final char PRINCESS_PEACH_CHAR = 'P';

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super(NAME, PRINCESS_PEACH_CHAR, Integer.MAX_VALUE);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
