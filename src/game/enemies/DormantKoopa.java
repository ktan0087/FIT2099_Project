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
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;

public class DormantKoopa extends Enemy {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * The name of DormantKoopa is set as a constant String "DormantKoopa"
     */
    public static final String NAME = "DormantKoopa";
    /**
     * The display char is set as a constant char 'D'
     */
    public static final char DISPLAY_CHAR = 'D';
    /**
     * The hitpoints is set as a constant 1
     */
    public static final int HITPOINTS = 1;

    public DormantKoopa(Location location){
        super(NAME, DISPLAY_CHAR, HITPOINTS, location);
        this.behaviours.remove(10, new WanderBehaviour());
        this.behaviours.remove(8, new AttackBehaviour());
    }

    /**
     * Override playTurn method so it returns doNothingAction
     *
     * @return DoNothingAction()
     * @see Actor
     * **/
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Override hurt method from Actor class so that if player is destroyed,
     * invoke dropSuperMushroom() method
     *
     * @param points
     * **/
    @Override
    public void hurt(int points){
        super.hurt(points);
        //check if DormantKoopa is destroyed
        if (this.hasCapability(Status.DESTROYED)){
            dropSuperMushroom();
        }
    }

    /**
     * dropSuperMushroom() method to add SuperMushroom on map
     * at the DormantKoopa location
     * **/
    public void dropSuperMushroom(){
        //check if spawnLocation is null
        if (spawnLocation != null){
            //if spawnLocation is not null, then add SuperMushroom
            spawnLocation.addItem(new SuperMushroom());
            //remove DormantKoopa from map
            spawnLocation.map().removeActor(this);
        }
    }

    /**
     * Override allowableActions from enemy class to add condition,
     * if player has capability to smash Koopa shell
     *
     * @return action
     * **/
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

/*       //done in engine
       for (Item item : otherActor.getInventory()){
            if (item.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)){
                this.addCapability(Status.DESTROYED);
                otherActor.addCapability(Status.CAN_SMASH_KOOPA_SHELL);
            }
        }*/

        //check if actor hasCapability to smash koopa shell
        if(otherActor.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)) {
            this.addCapability(Status.DESTROYED);
            //if yes, add AttackAction to actor
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

}
