package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.actions.TradeAction;
import game.interfaces.Tradable;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

import java.util.ArrayList;

public class Toad extends Actor {
    private static final String NAME = "Toad";
    private static final char TOAD_CHAR = 'O';
    private ArrayList<Tradable> tradableList = new ArrayList<>();

    /**
     * Constructor.
     */
    public Toad() {
        super(NAME, TOAD_CHAR, Integer.MAX_VALUE);
        addToTradableList();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // loop through different types of tradable item
        for (Tradable item : tradableList) {
            actions.add(new TradeAction(item));
        }

        actions.add(new SpeakAction(this));

        return actions;
    }

    private void addToTradableList(){
        tradableList.add(new PowerStar());
        tradableList.add(new SuperMushroom());
        tradableList.add(new Wrench());
    }

}
