package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.interfaces.CemeteryHelperInt;
import game.terrains.*;
import game.actors.LordOfCinder;
import game.actors.Player;
import game.actors.Skeleton;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());


			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery(),new Vendor(),new Bonfire());

			// width = 80, height = 26
			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					".........................................................................+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.c.....+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#__FB___#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));
			//as the requirement said - manually place several Skeletons
			gameMap.at(38,4).addActor(new Skeleton("Skeleton",38,4,player));
			gameMap.at(28,17).addActor(new Skeleton("Skeleton",28,17,player));
			gameMap.at(70,25).addActor(new Skeleton("Skeleton",70,25,player));
			gameMap.at(55,15).addActor(new Skeleton("Skeleton",55,15,player));
			gameMap.at(0,0).addActor(new Skeleton("Skeleton",0,0,player));
			gameMap.at(68,3).addActor(new Skeleton("Skeleton",86,3,player));




			// it could help cemetery generate Undead automatically. the class is a kind of helper
			CemeteryHelperInt cemeteryHelper =new CemeteryHelper(map,gameMap,player);
			cemeteryHelper.findLocations();
			cemeteryHelper.replaceCemetery();






			world.run();

	}
}
