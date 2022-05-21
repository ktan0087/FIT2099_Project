package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


public class TeleportAction extends Action{
    private static boolean ORIGINAL_MAP = true;
    private static Location warpSource;
    private static Location warpDestination;

    public TeleportAction(Location location) {
        warpSource = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String message;
        if (warpDestination.map().isAnActorAt(warpDestination)) {
            warpDestination.map().removeActor(warpDestination.getActor());
        }
        warpDestination.map().moveActor(actor, warpDestination);
        warpDestination = warpSource;
        message = (ORIGINAL_MAP) ? "Teleported to Lava Zone" : "Teleported back to original world";
        ORIGINAL_MAP = !ORIGINAL_MAP;
        return message;
    }

    public static void setDestination(Location location) {
        warpDestination = location;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (ORIGINAL_MAP) ? "Teleport to Lava Zone" : "Teleport back to original world";
    }
}
