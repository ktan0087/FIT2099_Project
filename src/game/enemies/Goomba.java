package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
	// const variables for getIntrinsicWeapon method
	private final int INTRINSIC_DAMAGE = 10;
	private final String DAMAGE_VERB = "kicks";
	// const variable for suicide rate
	private final int SUICIDE_RATE = 10;
	private Random rand = new Random();

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 50);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(8, new AttackBehaviour());
	}

	/**
	 * Constructor.
	 * @param spawnLocation
	 */
	public Goomba(Location spawnLocation) {
		super("Goomba", 'g', 50, spawnLocation);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(8, new AttackBehaviour());
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * By default, the Actor 'punches' for 5 damage. Override this method to create
	 * an Actor with description and damage using constant variable declared
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 * @see Actor getIntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon(){
		return new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
	}

	/**
	 * Figure out what to do next.
	 *
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		Location actorLocation = map.locationOf(this);

		//method used to remove Goomba when reset
		if (!(map.contains(this))){
			return new SuicideAction(actorLocation);
		}

		//method used for
		if (rand.nextInt(100) <= SUICIDE_RATE || !this.isConscious()){
			map.removeActor(this);
			return new SuicideAction(actorLocation);
		}

		return super.playTurn(actions, lastAction, map, display);
	}
}
