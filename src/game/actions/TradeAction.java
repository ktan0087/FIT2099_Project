package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.enums.Status;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

public class TradeAction extends Action {
    private static final String FAIL_PURCHASE = "You don't have enough coins!";

    @Override
    public String execute(Actor actor, GameMap map) {
        Display display = new Display();

        boolean flag = true;

        while (flag){
            int balance = Wallet.getBalance();
            int price = 0;
            display.println("Current " + actor + "'s balance: " + balance);
            display.println("Choose options: " +
                    "\np: Mario buys Power Star ($600)" +
                    "\ns: Mario buys Super Mushroom ($400)" +
                    "\nw: Mario buys Wrench ($200)" +
                    "\nq: Quit");
            char input = display.readChar();
            display.println(actor + " entered: " + input);

            // power star
            if (input == 'p'){
                PowerStar powerStar = new PowerStar();
                price = powerStar.getPrice();
                if (balance >= price){
                    Wallet.subtractBalance(price);
                    powerStar.addCapability(Status.UNDROPPABLE);
                    actor.addItemToInventory(powerStar);
                    display.println(powerStar + " has been added to the inventory successfully");
                } else {
                    display.println(FAIL_PURCHASE);
                }
            }

            // super mushroom
            else if (input == 's'){
                SuperMushroom superMushroom = new SuperMushroom();
                price = superMushroom.getPrice();
                if (balance >= price){
                    Wallet.subtractBalance(price);
                    superMushroom.addCapability(Status.UNDROPPABLE);
                    actor.addItemToInventory(superMushroom);
                    display.println(superMushroom + " has been added to the inventory successfully");
                } else {
                    display.println(FAIL_PURCHASE);
                }
            }

            // wrench
            else if (input == 'w'){
                Wrench wrench = new Wrench();
                price = wrench.getPrice();
                if (balance >= price){
                    Wallet.subtractBalance(price);
                    wrench.addCapability(Status.CAN_SMASH_KOOPA_SHELL);
                    actor.addItemToInventory(wrench);
                    display.println(wrench + " has been added to the inventory successfully");
                } else {
                    display.println(FAIL_PURCHASE);
                }
            }

            // quit
            else if (input == 'q'){
                flag = false;
            }

            // invalid input
            else{
                display.println("Invalid input. Please try again");
            }
        }

        return "Thank you. Please come again";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades with Toad";
    }
}

