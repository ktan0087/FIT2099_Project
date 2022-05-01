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
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;

public class Koopa extends Enemy {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * The intrinsice damage is set as a constant 30
     */
    private static final int INTRINSIC_DAMAGE = 30;
    /**
     * The damage verb is set as a constant String "punch"
     */
    private static final String DAMAGE_VERB = "punch";
    /**
     * The name of Koopa is set as a constant String "Koopa"
     */
    public static final String NAME = "Koopa";
    /**
     * The display char is set as a constant char 'K'
     */
    public static final char DISPLAY_CHAR = 'K';
    /**
     * The hitpoints is set as a constant 100
     */
    public static final int HITPOINTS = 100;

    private static boolean isDormant = false;
    private static boolean isShellBroken = false;

    /**
     * Constructor.
     */
    public Koopa() {
        super(NAME, DISPLAY_CHAR, 5);
    }

    /**
     * Constructor.
     * @param spawnLocation
     */
    public Koopa(Location spawnLocation) {
        super(NAME, DISPLAY_CHAR, 5, spawnLocation);
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Actor with constant variable initialised before.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     * @see Actor getIntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
    }

    /**
     * Do some damage to the current Actor.
     *
     * If the Actor's hitpoints go down to zero, it will be knocked out.
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points){
        super.hurt(points);

        // check if Koopa is Conscious
        if (!this.isConscious()){
            // if Koopa is not conscious then remove from map
            spawnLocation.map().removeActor(this);

            // and add DormantKoopa actor at the same location
            spawnLocation.addActor(new DormantKoopa(spawnLocation));
        }
    }

    /**
     * Override playTurn method from Actor class to add in SuicideAction method
     * and return super class's action
     *
     * @return action
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location actorLocation = map.locationOf(this);

        //method used to remove Koopa when reset
        if (!(map.contains(this))){
            return new SuicideAction(actorLocation);
        }

        //return action in super class
        return super.playTurn(actions, lastAction, map, display);
    }









/*

     public void dropSuperMushroom(){
        if (spawnLocation != null){
            spawnLocation.addItem(new SuperMushroom());
            spawnLocation.map().removeActor(this);
        }
    }

 @Override
    public char getDisplayChar(){
        if (isDormant){
            return DORMANT_CHAR;
        }
        return super.getDisplayChar();
    }

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

    */


}
