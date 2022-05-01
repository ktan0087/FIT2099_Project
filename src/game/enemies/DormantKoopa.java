package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Status;
import game.items.SuperMushroom;

public class DormantKoopa extends Enemy {

    public DormantKoopa(Location location){
        super("DormantKoopa", 'D', 1, location);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     *
     * **/
    @Override
    public void hurt(int points){
        super.hurt(points);
        if (this.hasCapability(Status.DESTROYED)){
            dropSuperMushroom();
        }
    }

    public void dropSuperMushroom(){
        if (spawnLocation != null){
            spawnLocation.addItem(new SuperMushroom());
            spawnLocation.map().removeActor(this);
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for (Item item : otherActor.getInventory()){
            if (item.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)){
                this.addCapability(Status.DESTROYED);
                otherActor.hasCapability(Status.CAN_SMASH_KOOPA_SHELL);
            }
        }

        if(otherActor.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

}
