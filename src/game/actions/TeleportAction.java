package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.WarpPipe;


public class TeleportAction extends Action{

    @Override
    public String execute(Actor actor, GameMap map) {
        String message;
        GameMap lavaMap = WarpPipe.getMasterWarpMap();
        Location masterWarp = WarpPipe.getMasterWarpLocation();
        if (!map.equals(lavaMap)) {
            if (lavaMap.isAnActorAt(masterWarp)) {
                lavaMap.removeActor(masterWarp.getActor());
            }
            WarpPipe.setReturn(map.locationOf(actor), map);
            lavaMap.moveActor(actor, masterWarp);
            message = "Teleported to Lava Zone";
        } else {
            WarpPipe.getReturnMap().moveActor(actor, WarpPipe.getReturnLocation());
            message = "Teleported back to original world";
        }
        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        String message;
        if (WarpPipe.getMasterWarpMap().contains(actor)) {
            message = "Teleport back to original world";
        } else {
            message = "Teleport to Lava Zone";
        }
        return message;
    }
}
