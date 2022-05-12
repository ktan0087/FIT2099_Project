package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

public class Fire extends WeaponItem {


    /**
     * The hit rate of fire is set as a constant 100%
     */
    private static final int HIT_RATE = 100;

    /**
     * The damage of fire is set as a constant 20
     */
    private static final int DAMAGE = 20;

    /**
     * The name is set as a constant "Fire" / whatever you think is appropriate
     */
    private static final String NAME = "Fire";

    /**
     * The display character of fire is set as a constant 'v' / whatever you think is appropriate
     */
    private static final char FIRE_CHAR = 'v';

    /**
     * The verb used by fire when carrying out an attack
     */
    private static final String FIRE_VERB = "burns";

    /**
     * Constructor.
     */
    public Fire() {
        super(NAME, FIRE_CHAR, DAMAGE, FIRE_VERB, HIT_RATE);
        this.addCapability(Status.CAN_ATTACK_WITH_FIRE);
    }



}
