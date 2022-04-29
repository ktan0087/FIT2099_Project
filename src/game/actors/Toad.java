package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.actions.TradeAction;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Random;

public class Toad extends Actor {
    private static final char TOAD_CHAR = 'O';
    private static final int EXCLUDE_WRENCH_MSG = 0;
    private static final int EXCLUDE_POWER_STAR_MSG = 1;
    private static final String[] TOAD_MSG = {"You might need a wrench to smash Koopa's hard shells.",
            "You better get back to finding the Power Stars.",
            "The Princess is depending on you! You are our only hope.",
            "Being imprisoned in these walls can drive a fungus crazy :("};

    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", TOAD_CHAR, Integer.MAX_VALUE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList(new TradeAction());

        ArrayList<Integer> excludeMsgIndexList = new ArrayList<>();

        // check for wrench in inventory
        for (Item item : otherActor.getInventory()){
            if (item.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)){
                excludeMsgIndexList.add(EXCLUDE_WRENCH_MSG);
                break;
            }
        }

        // check power star effect
        if (otherActor.hasCapability(Status.INVINCIBLE)){
            excludeMsgIndexList.add(EXCLUDE_POWER_STAR_MSG);
        }

        actions.add(new SpeakAction(getRandomMsg(excludeMsgIndexList), this));

        return actions;
    }

    private String getRandomMsg(ArrayList<Integer> excludeMsgIndexList){
        ArrayList<String> msg = new ArrayList<>();

        for (int i = 0; i < TOAD_MSG.length; i++){
            if (!(excludeMsgIndexList.contains(i))){
                msg.add(TOAD_MSG[i]);
            }
        }

        return msg.get(new Random().nextInt(msg.size()));
    }

}
