package game.managers;

import edu.monash.fit2099.engine.items.Item;
import game.interfaces.Consumable;

import java.util.ArrayList;

public class ConsumableItemManager {
    private ArrayList<Consumable> consumableList;

    private static ConsumableItemManager instance;

    private ConsumableItemManager() { // 1-  private to prevent anyone else from instantiating
        consumableList = new ArrayList<>();
    }

    public static ConsumableItemManager getInstance() {
        if (instance == null) {
            instance = new ConsumableItemManager();
        }
        return instance;
    }

    public void appendConsumableItem(Consumable item) {
        this.consumableList.add(item);
    }

    public ArrayList<Consumable> getConsumableItem() {
        return new ArrayList<Consumable>(this.consumableList);
    }

    public Consumable getConsumableItem(Item newItem){
        for(Consumable item : consumableList){
            if(item.equals(newItem)) {
                return item;
            }
        }
        return null;
    }

    public void removeConsumableItem(Item item){
        consumableList.remove(item);
    }
}
