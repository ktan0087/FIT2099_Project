package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.items.Key;



import java.util.HashMap;
import java.util.Map;

public class Bowser extends Enemy{

    /**
     * Behaviours Hash map to store priority and  behaviour
     * */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * The intrinsice damage is set as a constant 10
     */
    private static final int INTRINSIC_DAMAGE = 80;
    /**
     * The damage verb is set as a constant String "punch"
     */
    private static final String DAMAGE_VERB = "punch";
    /**
     * The name of Bowser is set as a constant String "Bowser"
     */
    public static final String NAME = "Bowser";
    /**
     * The display char is set as a constant char 'B'
     */
    public static final char DISPLAY_CHAR = 'B';
    /**
     * The hitpoints is set as a constant 500
     */
    public static final int HITPOINTS = 500;

    private static final int POWER_BASE_DAMAGE = 15;

    private int numOfPowerWaterConsumption = 0;

    /**
     * Constructor.
     */
    public Bowser() {
        super(NAME, DISPLAY_CHAR, HITPOINTS);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(8, new AttackBehaviour());
        this.addCapability(Status.CAN_ATTACK_WITH_FIRE);
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Actor with description and damage using constant variable declared
     *
     * @return a freshly-instantiated IntrinsicWeapon
     * @see Actor getIntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        IntrinsicWeapon intrinsicWeapon = new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
        if (this.hasCapability(Status.POWER)){
            this.removeCapability(Status.POWER);
        }
        return new IntrinsicWeapon(intrinsicWeapon.damage() + POWER_BASE_DAMAGE * numOfPowerWaterConsumption, intrinsicWeapon.verb());
    }

    /**
     * Return actions taken by Bowser
     * @param otherActor the Actor that performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList
     * */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        //check if PLayer is in HOSTILE_TO_ENEMY state
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            //check if Bowser is conscious
            if (this.isConscious()) {
                actions.add(new AttackAction(this, direction));
            }
            //TODO: drop key on actor's location
            else {
                map.locationOf(this).addItem(new Key());
                //remove target from map
                map.removeActor(this);
            }
        }
        return actions;
    }

    /**
     * Figure out what to do next. Override playTurn method and add in
     * reset method and suicideAction
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     * @return Action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.POWER)) {
            numOfPowerWaterConsumption++;
        }
        super.playTurn(actions, lastAction, map, display);

        //loop through behaviours and get actions
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        //return action that do nothing
        return new DoNothingAction();
    }

    /**
     * Reset Bowser health back to normal
     */
    @Override
    public void resetInstance() {
        //reset Bowser's hitpoints
        this.resetMaxHp(this.getMaxHp());
    }

}
