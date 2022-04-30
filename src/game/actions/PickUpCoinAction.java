package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.items.Coin;

public class PickUpCoinAction extends PickUpItemAction {

    private Coin coin;

    /**
     * Constructor.
     *
     * @param coin the coin to pick up
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Wallet.addBalance(coin.getValue());
        map.locationOf(actor).removeItem(coin);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up a $" + coin.getValue() + " coin";
    }
}

