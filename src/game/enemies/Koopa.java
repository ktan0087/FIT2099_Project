package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;

public class Koopa extends Enemy{

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private static final int INTRINSIC_DAMAGE = 30;
    private static final String DAMAGE_VERB = "punch";
    private static final char DORMANT_CHAR = 'D';
    private boolean isShellBroken = false;
    private boolean isDormant = false;

    /**
     * Constructor.
     */
    public Koopa(Location spawnLocation) {
        super("Koopa", 'K', 100, spawnLocation);
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#CAN_SMASH_KOOPA_SHELL
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        for (Item item : otherActor.getInventory()){
            if (item.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)){
                isShellBroken = true;
                this.addCapability(Status.DESTROYED);
            }
        }
        return actions;
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Actor with more interesting descriptions and/or different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     * @see Actor getIntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location actorLocation = map.locationOf(this);
        Action action = super.playTurn(actions, lastAction, map, display);


        //if Koopa is in dormant state
        if (isDormant){
            this.addCapability(Status.DORMANT);
            return new DoNothingAction();
        }
        if (isShellBroken){
            this.removeCapability(Status.DORMANT);
            this.addCapability(Status.DESTROYED);
            dropSuperMushroom();
        }

        //method used to remove Koopa when reset
        if (!(map.contains(this))){
            return new SuicideAction(actorLocation);
        }

        return action;
    }

    public void dropSuperMushroom(){
        if (spawnLocation != null){
            spawnLocation.addItem(new SuperMushroom());
            spawnLocation.map().removeActor(this);
        }
    }

    @Override
    public void hurt(int points){
        super.hurt(points);

        if (!this.isConscious()){
            isDormant = true;
        }
    }

    @Override
    public char getDisplayChar(){
        if (isDormant){
            return DORMANT_CHAR;
        }
        return super.getDisplayChar();
    }

    @Override
    public String toString(){
        if (!isDormant){
            return name;
        }
        return super.toString();
    }

}
