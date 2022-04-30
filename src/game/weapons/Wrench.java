package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.interfaces.Tradable;

public class Wrench extends WeaponItem implements Tradable {
    private static final int PRICE = 200;
    private static final int HIT_RATE = 80;
    private static final int DAMAGE = 50;
    private static final String NAME = "Wrench";
    private static final char WRENCH_CHAR = 'w';
    private static final String WRENCH_VERB = "whacks";

    /**
     * Constructor.
     *
     */
    public Wrench() {
        super(NAME, WRENCH_CHAR, DAMAGE, WRENCH_VERB, HIT_RATE);
        this.addCapability(Status.CAN_SMASH_KOOPA_SHELL);
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void addCapabilityDuringTrading() {
        this.addCapability(Status.CAN_SMASH_KOOPA_SHELL);
    }

}
