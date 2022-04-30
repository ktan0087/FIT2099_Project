package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import game.managers.ConsumableItemManager;

public interface Consumable {

    public void consumeMagicalItems(Actor actor);

    default void addToConsumableItemManager(){
        ConsumableItemManager.getInstance().appendConsumableItem(this);
    }

}
