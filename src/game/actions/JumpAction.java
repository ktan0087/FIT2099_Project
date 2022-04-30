package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.HighGround;

public class JumpAction extends Action {
    private final String direction;
    private final Location highGroundLocation;
    private final HighGround highGround;
    private final String coordinateString;


    public JumpAction(HighGround highGround, String direction, Location highGroundLocation) {
        this.highGround = highGround;
        this.highGroundLocation = highGroundLocation;
        this.direction = direction;
        coordinateString = String.format("%s(%d, %d)", highGround, highGroundLocation.x(), highGroundLocation.y());
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return highGround.jumped(actor, highGroundLocation, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s jumps on the %s to the %s", actor, coordinateString, direction);
    }
}
