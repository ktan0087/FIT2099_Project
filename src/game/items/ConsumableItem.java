package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.managers.ConsumableItemManager;

public interface ConsumableItem {

    public void consumeMagicalItems(Actor actor);

    default void addToConsumableItemManager(){
        ConsumableItemManager.getInstance().appendConsumableItem(this);
    }

}
