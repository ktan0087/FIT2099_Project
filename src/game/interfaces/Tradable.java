package game.interfaces;

import game.managers.TradableManager;

public interface Tradable {
    public int getPrice();

    default void registerTradableInstance(){
        TradableManager.getInstance().addTradableInstance(this);
    }
}
