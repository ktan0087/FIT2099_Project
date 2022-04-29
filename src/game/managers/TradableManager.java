package game.managers;

import game.interfaces.Tradable;

import java.util.HashMap;

public class TradableManager {
    private HashMap<Integer, Tradable> tradableHashMap;

    private static TradableManager instance;

    public static TradableManager getInstance(){
        if(instance == null){
            instance = new TradableManager();
        }
        return instance;
    }

    private TradableManager(){
        tradableHashMap = new HashMap<>();
    }

    public void addTradableInstance(Tradable item){
        tradableHashMap.put(item.hashCode(), item);
    }
}
