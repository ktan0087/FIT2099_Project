package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

public class BlinkAction extends Action {
    private static final int CARDINAL_NUM_OF_STEPS = 4;
    private static final int ORDINAL_NUM_OF_STEPS = 2;


    @Override
    public String execute(Actor actor, GameMap map) {
        Location destinationLocation = getDestinationLocation(map.locationOf(actor));
        if (destinationLocation != null) {
            map.moveActor(actor, destinationLocation);
            return actor + " blinks to " + destinationLocation.getGround().toString() + " at (" + destinationLocation.x() + ", " + destinationLocation.y() + ")";
        }

        else {
            return "There is no location (" + CARDINAL_NUM_OF_STEPS + " steps away) for " + actor + " to blink";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " blinks to random location (" + CARDINAL_NUM_OF_STEPS + " steps away)";
    }

    private Location getDestinationLocation(Location currentLocation){
        ArrayList<Location> validLocations = new ArrayList<>();

        int curXCoord = currentLocation.x();
        int curYCoord = currentLocation.y();

        // handle cardinal direction
        getCardinalLocation(currentLocation.map(), validLocations, curXCoord, curYCoord);

        // handle ordinal direction
        getOrdinalLocation(currentLocation.map(), validLocations, curXCoord, curYCoord);

        if (!validLocations.isEmpty()) {
            return validLocations.get(new Random().nextInt(validLocations.size()));
        }
        else {
            return null;
        }
    }

    private void getCardinalLocation(GameMap map, ArrayList<Location> validLocations, int curXCoord, int curYCoord) {
        Actor actor = map.getActorAt(map.at(curXCoord, curYCoord));

        // north & south
        if (map.getXRange().contains(curXCoord)){
            if (map.getYRange().contains(curYCoord + CARDINAL_NUM_OF_STEPS) && map.at(curXCoord, curYCoord + CARDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord, curYCoord + CARDINAL_NUM_OF_STEPS));
            }
            if (map.getYRange().contains(curYCoord - CARDINAL_NUM_OF_STEPS) && map.at(curXCoord, curYCoord - CARDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord, curYCoord - CARDINAL_NUM_OF_STEPS));
            }
        }

        // east & west
        if (map.getYRange().contains(curYCoord)){
            if (map.getXRange().contains(curXCoord - CARDINAL_NUM_OF_STEPS) && map.at(curXCoord - CARDINAL_NUM_OF_STEPS, curYCoord).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord - CARDINAL_NUM_OF_STEPS, curYCoord));
            }
            if (map.getXRange().contains(curXCoord + CARDINAL_NUM_OF_STEPS) && map.at(curXCoord + CARDINAL_NUM_OF_STEPS, curYCoord).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord + CARDINAL_NUM_OF_STEPS, curYCoord));
            }
        }
    }

    private void getOrdinalLocation(GameMap map, ArrayList<Location> validLocations, int curXCoord, int curYCoord){
        Actor actor = map.getActorAt(map.at(curXCoord, curYCoord));

        // NE & SE
        if (map.getXRange().contains(curXCoord + ORDINAL_NUM_OF_STEPS)){
            if (map.getYRange().contains(curYCoord + ORDINAL_NUM_OF_STEPS) && map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS));
            }
            if (map.getYRange().contains(curYCoord - ORDINAL_NUM_OF_STEPS) && map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS));
            }
        }

        // NW & SW
        if (map.getXRange().contains(curXCoord - ORDINAL_NUM_OF_STEPS)){
            if (map.getYRange().contains(curYCoord + ORDINAL_NUM_OF_STEPS) && map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS));
            }
            if (map.getYRange().contains(curYCoord - ORDINAL_NUM_OF_STEPS) && map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS));
            }
        }
    }
}
