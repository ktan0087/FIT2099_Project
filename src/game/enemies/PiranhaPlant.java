package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PiranhaPlant extends Enemy {
    /**
     * Behaviours Hash map to store priority and  behaviour
     * */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * The intrinsice damage is set as a constant 90
     */
    private static final int INTRINSIC_DAMAGE = 90;
    /**
     * The damage verb is set as a constant String "chomps"
     */
    private static final String DAMAGE_VERB = "chomps";
    /**
     * The name of Piranha Plant is set as a constant String "Piranha Plant"
     */
    public static final String NAME = "Piranha Plant";
    /**
     * The display char is set as a constant char 'Y'
     */
    public static final char DISPLAY_CHAR = 'Y';
    /**
     * The hitpoints is set as a constant 150
     */
    public static final int HITPOINTS = 150;
    /**
     * The hitpoints is set as a constant 20
     */
    private final Random rand = new Random();

    public PiranhaPlant() {
        super(NAME, DISPLAY_CHAR, HITPOINTS);
        this.registerInstance();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
    }

    /**
     * Reset Piranha Plant hp
     */
    @Override
    public void resetInstance() {
        this.increaseMaxHp(50);
        this.heal(getMaxHp());
    }
}
