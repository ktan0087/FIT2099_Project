package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.behaviours.*;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.enums.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Resettable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    protected Location spawnLocation;

    /**
     * Constructor.
     *  @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param spawnLocation
     */
    public Enemy(String name, char displayChar, int hitPoints, Location spawnLocation) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_PLAYER);  //can be attacked by player
        this.spawnLocation = spawnLocation;
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(8, new AttackBehaviour());
        this.registerInstance();
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

        if(this.isConscious()) {
            Location location = map.locationOf(this);
            this.spawnLocation = map.at(location.x(), location.y());
        }
        else {
            return new DoNothingAction();
        }

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        if (this.hasCapability(Status.RESET)){
            map.removeActor(this);
        }

        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        return new ActionList();
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
