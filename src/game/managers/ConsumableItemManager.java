package game.managers;

import edu.monash.fit2099.engine.items.Item;
import game.items.ConsumableItem;

import java.util.ArrayList;

public class ConsumableItemManager {
    private ArrayList<ConsumableItem> ConsumableItemList;

    private static ConsumableItemManager instance;

    private ConsumableItemManager() { // 1-  private to prevent anyone else from instantiating
        ConsumableItemList = new ArrayList<>();
    }

    public static ConsumableItemManager getInstance() {
        if (instance == null) {
            instance = new ConsumableItemManager();
        }
        return instance;
    }

    public void appendConsumableItem(ConsumableItem item) {
        this.ConsumableItemList.add(item);
    }

    public ArrayList<ConsumableItem> getConsumableItem() {
        return new ArrayList<ConsumableItem>(this.ConsumableItemList);
    }

    public ConsumableItem getConsumableItem(Item newItem){
        for(ConsumableItem item : ConsumableItemList){
            if(item.equals(newItem)) {
                return item;
            }
        }
        return null;
    }

    public void removeConsumableItem(Item item){
        ConsumableItemList.remove(item);
    }
}
