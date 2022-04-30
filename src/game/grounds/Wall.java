package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;

public class Wall extends HighGround {
	private static final double JUMP_CHANCE = 0.8;
	private static final int FALL_DAMAGE = 20;

	public Wall() {
		super('#');
		setJumpChance(JUMP_CHANCE);
		setFallDamage(FALL_DAMAGE);
	}

	@Override
	public String toString() {
		return "Wall";
	}
}
