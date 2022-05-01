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

	/**
	 * The intrinsice damage is set as a constant 10
	 */
	private static final int INTRINSIC_DAMAGE = 10;
	/**
	 * The damage verb is set as a constant String "kick"
	 */
	private static final String DAMAGE_VERB = "kick";
	/**
	 * The name of Goomba is set as a constant String "Goomba"
	 */
	public static final String NAME = "Goomba";
	/**
	 * The display char is set as a constant char 'g'
	 */
	public static final char DISPLAY_CHAR = 'g';
	/**
	 * The hitpoints is set as a constant 20
	 */
	public static final int HITPOINTS = 20;

	private final int SUICIDE_RATE = 10;
	private Random rand = new Random();

	/**
	 * Constructor.
	 */
	public Goomba() {
		super(NAME, DISPLAY_CHAR, HITPOINTS);
/*		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(8, new AttackBehaviour());*/
	}

	/**
	 * Constructor.
	 * @param spawnLocation
	 */
	public Goomba(Location spawnLocation) {
		super(NAME, DISPLAY_CHAR, HITPOINTS, spawnLocation);
/*		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(8, new AttackBehaviour());*/
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
	 * Figure out what to do next. Override playTurn method and add in
	 * reset method and suicideAction
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

		//method used for goomba to suicide
		if (rand.nextInt(100) <= SUICIDE_RATE || !this.isConscious()){
			//remove goomba from map
			map.removeActor(this);
			return new SuicideAction(actorLocation);
		}

		//return action in super class
		return super.playTurn(actions, lastAction, map, display);
	}
}
