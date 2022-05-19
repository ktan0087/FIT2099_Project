package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Status;

/**
 * Special Action for attacking other Actors.
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * The direction of incoming attack.
	 */
	protected String direction;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target    the Actor to attack
	 * @param direction the direction of target
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		//print actor missed target
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		//get damage from weapon
		int damage = weapon.damage();
		String output = "";

		//check if actor is Invincible
		if (actor.hasCapability(Status.INVINCIBLE)){
			//check if target can be attack by actor
			if(target.hasCapability(Status.HOSTILE_TO_PLAYER)){
				output = actor + " killed " + target ;
				//target gets instant killed
				target.hurt(1000);
			}
			else {
				output = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
				target.hurt(damage);
			}
		}
		//if actor in normal state without any buff
		else {
			//check if target is invincible
			if(target.hasCapability(Status.INVINCIBLE)){
				//print can't damage string
				output = actor + " " + weapon.verb() + " can't damage " + target;
			}
			else{
				output = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
				target.hurt(damage);
			}
		}

		if (actor.hasCapability(Status.CAN_ATTACK_WITH_FIRE)) {
			if (Math.random() < 0.5) {
				if (target.hasCapability(Status.HOSTILE_TO_ENEMY) || target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
					output = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
					target.hurt(damage);

					Location location = map.locationOf(target);
					boolean isBurning = location.getGround().hasCapability(Status.IS_BURNING);
					if (!isBurning) {
						location.getGround().addCapability(Status.IS_BURNING);
						output += System.lineSeparator() + actor + " attacks " + target + " with fire! ";
						output += System.lineSeparator() + "The ground is on fire!";
					}
				}
			}
		}

		//check if target is conscious
		if (!target.isConscious()) {
			//check if target is Koopa
			if (target.hasCapability(Status.CAN_ENTER_SHELL)){
				output += System.lineSeparator() + target + " retreated into its shell!";
			}
			else {
				//normal output String for enemies other than Koopa
				ActionList dropActions = new ActionList();
				//drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				//remove actor from map
				map.removeActor(target);
				//check if target is in dormant state
				output += System.lineSeparator() + target + " is killed.";
			}
		}
		return output;
	}

	/**
	 * Return descriptive String to console.
	 *
	 * @param actor The actor performing the action.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}

}
