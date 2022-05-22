package game.interfaces;

/**
 * A Turnable interface implemented by AnnoyingKoopa or other enemies that will turn to other state when they are unconscious
 *
 * @author Kennedy Tan
 * @version 1.0
 */
public interface Turnable {
    /**
     * Method for enemies to get their current hp
     *
     * @return current hp
     */
    public default int getHp(){
        return 0;
    }
}
