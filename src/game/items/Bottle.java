package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.enums.Status;
import game.interfaces.Consumable;
import game.waters.Water;

import java.util.ArrayList;

public class Bottle extends Item implements Consumable {
    private static final String NAME = "Bottle";
    private static final char DISPLAY_CHAR = 'b';
    private static ArrayList<Water> waters = new ArrayList<>();

    /***
     * Constructor.
     */
    public Bottle() {
        super(NAME, DISPLAY_CHAR, false);
        this.addCapability(Status.NON_REMOVABLE_FROM_INVENTORY);
        this.addToConsumableItemManager();
    }

    public static void fillWater(Water water){
        waters.add(water);
    }

    public static Water drinkWater(){
        Water lastWater = waters.get(waters.size() - 1);
        waters.remove(waters.size() - 1);
        return lastWater;
    }

    public static int getNumOfWaters(){
        return waters.size();
    }

    @Override
    public void consumeMagicalItems(Actor actor) {
        waters.get(waters.size() - 1).drink(actor);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public String toString(){
        String res = "";

        for (int i = 0; i < waters.size(); i++){
            if (i > 0){
                res += ", ";
            }

            res += waters.get(i);
        }

        return "Bottle[" + res + "]";
    }
}

