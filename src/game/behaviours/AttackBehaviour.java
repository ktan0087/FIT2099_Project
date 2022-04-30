package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;

public class AttackBehaviour implements Behaviour {

    private Actor followedTarget;

    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {

        for(Exit exit : map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            if (destination.containsAnActor()){
                Actor target = destination.getActor();
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY) && !actor.hasCapability(Status.HOSTILE_TO_ENEMY) || !target.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    followedTarget = target;
                    actor.addCapability(Status.AGGRO);
                    return new AttackAction(target, exit.getName());
                }
            }
        }

        if (followedTarget != null && actor.hasCapability(Status.AGGRO)){
            return new FollowBehaviour(followedTarget).getAction(actor, map);
        }

        return null;
    }
}
