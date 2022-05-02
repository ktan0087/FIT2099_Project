package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.Toad;
import game.enemies.Goomba;
import game.enemies.Koopa;
import game.grounds.*;
import game.items.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			addSprouts(map);

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

		/**
		 * Add actor and items in map
		 */
			gameMap.at(42,11).addActor(new Toad());
			gameMap.at(43,10).addItem(new SuperMushroom());
			gameMap.at(41,10).addItem(new PowerStar());
			world.run();

	}


	/**
	 * Place some sprouts randomly in map
	 *
	 * @param map current game map
	 */
	public static void addSprouts(List<String> map) {
		for (int i = 0; i <  map.size(); i++) {
			for (int j = 0; j < map.get(i).length(); j++) {
				if (Math.random() < 0.01 && map.get(i).charAt(j) == '.') {
					char[] newString = map.get(i).toCharArray();
					newString[j] = '+';
					map.set(i, String.valueOf(newString));
				}
			}
		}
	}
}
