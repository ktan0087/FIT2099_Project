package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.actions.ResetAction;
import game.enums.Status;
import game.interfaces.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private int turn = 10;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.registerInstance();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		display.println(this + "'s current balance: $" + Wallet.getBalance());

		if (!(ResetAction.getResetFlag())){
			actions.add(new ResetAction());
		}

		if (hasCapability(Status.INVINCIBLE)){
			display.println("Mario is INVINCIBLE!");
			display.println("Mario consumes Power Star - " + turn +" turns remaining");

			turn -= 1;
		}

		if (turn < 1){
			removeCapability(Status.INVINCIBLE);
		}

		//add on here

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	@Override
	public void resetInstance() {
		if (this.hasCapability(Status.TALL)) {
			this.setDisplayChar(Character.toLowerCase(super.getDisplayChar()));
			this.removeCapability(Status.TALL);
		}

		if (this.hasCapability(Status.INVINCIBLE)) {
			this.removeCapability(Status.INVINCIBLE);
		}

		this.resetMaxHp(this.getMaxHp());
	}

}
