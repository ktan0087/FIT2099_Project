package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.FollowBehaviour;
import game.behaviours.HealBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.HashMap;
import java.util.Map;

public class Yoshi extends Actor implements Resettable {
    private static final String NAME = "Yoshi";
    private static final char DISPLAY_CHAR = '!';

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private static final int RESET_X_COORD = 43;
    private static final int RESET_Y_COORD = 10;


    /**
     * Constructor.
     */
    public Yoshi() {
        super(NAME, DISPLAY_CHAR, Integer.MAX_VALUE);
        this.registerInstance();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)){
            this.removeCapability(Status.RESET);

            if (map.at(RESET_X_COORD, RESET_Y_COORD).containsAnActor()){
                map.removeActor(map.at(RESET_X_COORD, RESET_Y_COORD).getActor());
            }

            map.moveActor(this, map.at(RESET_X_COORD, RESET_Y_COORD));
            display.println(this + " moves back to original position (" + RESET_X_COORD + ", " + RESET_Y_COORD + ")");

            return new DoNothingAction();
        }

        behaviours.clear();
        Actor player = findPlayer(map);
        if (player != null && distance(map.locationOf(player), map.locationOf(this)) <= 2){
            this.behaviours.put(2, new FollowBehaviour(player));
            if (player.hasCapability(Status.INJURED)){
                display.println(new HealBehaviour(player).getAction(this, map).execute(this, map));
                player.removeCapability(Status.INJURED);
            }
        }

        else {
            display.println(this + ": Hey, your adventure partner is right over here (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")");
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

    private Actor findPlayer(GameMap map){
        for (int y : map.getYRange()){
            for (int x : map.getXRange()){
                if (map.at(x, y).containsAnActor() && map.at(x, y).getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                    return map.at(x, y).getActor();
                }
            }
        }
        return null;
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }



}
