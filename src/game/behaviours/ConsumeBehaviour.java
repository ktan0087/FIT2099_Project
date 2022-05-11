package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.waters.HealingWater;
import game.waters.PowerWater;

public class ConsumeBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround().hasCapability(Status.POWER)){
            return new ConsumeAction(new PowerWater());
        }
        else if (map.locationOf(actor).getGround().hasCapability(Status.HEAL)){
            return new ConsumeAction(new HealingWater());
        }
        return null;
    }
}
