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
	private final int intrinsicDamage = 10;
	private final String damageVerb = "kicks";
	private final int suicideRate = 10;
	private Random rand = new Random();

	/**
	 * Constructor.
	 */
	public Goomba() {
		this(null);
	}

	/**
	 * Constructor.
	 */
	public Goomba(Location spawnLocation) {
		super("Goomba", 'g', 50, spawnLocation);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(8, new AttackBehaviour());
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	/*
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}
	*/

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * By default, the Actor 'punches' for 5 damage. Override this method to create
	 * an Actor with more interesting descriptions and/or different damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 * @see Actor getIntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon(){
		return new IntrinsicWeapon(intrinsicDamage, damageVerb);
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		Location actorLocation = map.locationOf(this);
		super.playTurn(actions, lastAction, map, display);

		if (!(map.contains(this))){
			return new SuicideAction(actorLocation);
		}

		if (rand.nextInt(100) <= suicideRate || !this.isConscious()){
			map.removeActor(this);
			return new SuicideAction(actorLocation);
		}

		for(Behaviour Behaviour : behaviours.values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}

		return new DoNothingAction();
	}


}
