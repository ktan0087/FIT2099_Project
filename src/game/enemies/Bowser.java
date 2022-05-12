package game.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.Fire;

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

    /**
     * Constructor.
     */
    public Bowser() {
        super(NAME, DISPLAY_CHAR, HITPOINTS);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(8, new AttackBehaviour());
        addItemToInventory(new Fire());
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Intrinsic Weapon with constant variable initialised before.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     * @see Actor getIntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
    }

    /**
     * Return actions taken by Bowser in different state
     *
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
                //check if Player has capability to attack with fire
                if (otherActor.hasCapability(Status.CAN_ATTACK_WITH_FIRE)){
                    //add FireAttackAction to Player
                    actions.add(new FireAttackAction(this, direction));
                }
                else {
                    //add AttackAction to Player to attack enemy
                    actions.add(new AttackAction(this, direction));
                }
            }
        }

        return actions;
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
