package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Status;

/**
 * Special Action for attacking other Actors.
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
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String output = "";

		// check if actor is Invincible
		if (actor.hasCapability(Status.INVINCIBLE)){

			if (target.hasCapability(Status.DORMANT) && actor.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)) {
				output = actor + " destroyed " + target + " with a wrench ";
				target.hurt(1000);
			}
			else if(target.hasCapability(Status.HOSTILE_TO_PLAYER)){
				output = actor + " killed " + target ;
				target.hurt(1000);
			}
			else {
				output = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
				target.hurt(damage);
			}
		}

		// if actor in normal state
		else {
			if (target.hasCapability(Status.DORMANT)){
				if (actor.hasCapability(Status.CAN_SMASH_KOOPA_SHELL) && target.hasCapability(Status.DESTROYED)){
					output = actor + " destroyed " + target + " with a wrench ";
					target.hurt(Integer.MAX_VALUE);
				}
				else{
					output = actor + " " + weapon.verb() + " can't damage " + target;
				}
			}
			else if(target.hasCapability(Status.INVINCIBLE)){
				output = actor + " " + weapon.verb() + " can't damage " + target;
			}
			else{
				output = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
				target.hurt(damage);
			}
		}

		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			output += System.lineSeparator() + target + " is killed.";
		}

		return output;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}

}
