package game.items;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Item {
    /**
     * The hit rate of fire is set as a constant 50%
     */
    private static final int HIT_RATE = 50;

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
     * The number of turns for fire to fade away
     */
    private int turn = 4;
    /**
     * Create display object to print message to console
     */
    Display display = new Display();
    /**
     * Number of turns to start to let the player on fire
     */
    private int startBurningTurn = 4;

    /***
     * Constructor.
     */
    public Fire() {
        super(NAME, FIRE_CHAR, false);
    }

    /**
     * Remove PowerStar from the current location within 10 turns
     *
     * @param currentLocation The location of the ground on which PowerStar lie.
     */
    @Override
    public void tick(Location currentLocation){

        if (currentLocation.containsAnActor() && startBurningTurn <= 3) {
            currentLocation.getActor().hurt(20);
            display.println(currentLocation.getActor() + " is on fire, receive 20 damage");
        }

        startBurningTurn -= 1;
        turn -= 1;

        //if use up 3 turns
        if (turn == 0){
            currentLocation.removeItem(this);   // remove from current location
        }
    }

}