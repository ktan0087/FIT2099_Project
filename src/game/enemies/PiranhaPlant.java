package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
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
     * The intrinsice damage is set as a constant 10
     */
    private static final int INTRINSIC_DAMAGE = 10;
    /**
     * The damage verb is set as a constant String "kick"
     */
    private static final String DAMAGE_VERB = "kick";
    /**
     * The name of Goomba is set as a constant String "Goomba"
     */
    public static final String NAME = "Piranha Plant";
    /**
     * The display char is set as a constant char 'g'
     */
    public static final char DISPLAY_CHAR = 'Y';
    /**
     * The hitpoints is set as a constant 20
     */
    public static final int HITPOINTS = 20;
    /**
     * The suicide rate is set as a constant 10
     */
    private final int SUICIDE_RATE = 10;
    /**
     * The hitpoints is set as a constant 20
     */
    private final Random rand = new Random();

    public PiranhaPlant() {
        super(NAME, DISPLAY_CHAR, HITPOINTS);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
    }

}
