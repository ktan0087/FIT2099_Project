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
        Location masterWarp = WarpPipe.getMasterWarpLocation();
        Location returnWarp = WarpPipe.getReturnLocation();
        if (!map.equals(masterWarp.map())) {
            if (masterWarp.map().isAnActorAt(masterWarp)) {
                masterWarp.map().removeActor(masterWarp.getActor());
            }
            WarpPipe.setReturnWarp(map.locationOf(actor));
            masterWarp.map().moveActor(actor, masterWarp);
            message = "Teleported to Lava Zone";
        } else {
            returnWarp.map().moveActor(actor, returnWarp);
            message = "Teleported back to original world";
        }
        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        String message;
        if (WarpPipe.getMasterWarpLocation().map().contains(actor)) {
            message = "Teleport back to original world";
        } else {
            message = "Teleport to Lava Zone";
        }
        return message;
    }
}
