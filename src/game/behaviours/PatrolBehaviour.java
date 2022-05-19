package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Behaviour;

public class PatrolBehaviour implements Behaviour {
    private static final int MAX_Y_COORD = 16;
    private static final int MIN_Y_COORD = 12;
    private boolean patrolDirFlag = true; // true - move upwards; false - move downwards

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (reachedBottom(actor, map)){
            patrolDirFlag = true;
        }

        else if (reachedTop(actor, map)){
            patrolDirFlag = false;
        }

        int nextYCoord = getNextAvailableYCoord(patrolDirFlag, map, actor);

        if (nextYCoord == -1){
            if (patrolDirFlag){
                patrolDirFlag = false;
            }
            else {
                patrolDirFlag = true;
            }
            return new DoNothingAction();
        }

        else {
            if (patrolDirFlag) {
                return new MoveActorAction(map.at(map.locationOf(actor).x(), nextYCoord), "North");
            } else {
                return new MoveActorAction(map.at(map.locationOf(actor).x(), nextYCoord), "South");
            }
        }

    }

    private boolean reachedTop(Actor actor, GameMap map){
        return map.locationOf(actor).y() == MIN_Y_COORD;
    }

    private boolean reachedBottom(Actor actor, GameMap map){
        return map.locationOf(actor).y() == MAX_Y_COORD;
    }

    public static int getMaxYCoord(){
        return MAX_Y_COORD;
    }

    private int getNextAvailableYCoord(boolean patrolDirFlag, GameMap map, Actor actor){
        if (patrolDirFlag){
            for (int i = map.locationOf(actor).y() - 1; i >= MIN_Y_COORD; i--){
                if (map.at(map.locationOf(actor).x(), i).canActorEnter(actor)){
                    return i;
                }
            }
        }
        else {
            for (int i = map.locationOf(actor).y() + 1; i <= MAX_Y_COORD; i++){
                if (map.at(map.locationOf(actor).x(), i).canActorEnter(actor)){
                    return i;
                }
            }
        }
        return -1;
    }
}
