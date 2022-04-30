package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Random;

public class SpeakAction extends Action {
    private Actor target;
    private static final int EXCLUDE_WRENCH_MSG = 0;
    private static final int EXCLUDE_POWER_STAR_MSG = 1;
    private static final String[] TOAD_MSG = {"You might need a wrench to smash Koopa's hard shells.",
                                              "You better get back to finding the Power Stars.",
                                              "The Princess is depending on you! You are our only hope.",
                                              "Being imprisoned in these walls can drive a fungus crazy :("};

    public SpeakAction(Actor target){
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<Integer> excludeMsgIndexList = new ArrayList<>();

        // check for wrench in inventory
        for (Item item : actor.getInventory()){
            if (item.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)){
                excludeMsgIndexList.add(EXCLUDE_WRENCH_MSG);
                break;
            }
        }

        // check power star effect
        if (actor.hasCapability(Status.INVINCIBLE)){
            excludeMsgIndexList.add(EXCLUDE_POWER_STAR_MSG);
        }

        return target + ": " + getRandomMsg(excludeMsgIndexList);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + target;
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
