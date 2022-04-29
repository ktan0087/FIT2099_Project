package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.interfaces.Tradable;


public class TradeAction extends Action {
    private Tradable item;
    private static final String SUCCESS_PURCHASE_MSG = " has been added to the inventory successfully\nThank you. Please come again";
    private static final String FAIL_PURCHASE_MSG = "You don't have enough coins!";

    public TradeAction(Tradable item){
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (Wallet.getBalance() >= item.getPrice()){
            Wallet.subtractBalance(item.getPrice());
            item.addCapabilityDuringTrading();
            actor.addItemToInventory((Item) item);
            return item + SUCCESS_PURCHASE_MSG;
        } else {
            return FAIL_PURCHASE_MSG;
        }

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " ($" + item.getPrice() + ")";
    }
}

