package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.actors.*;
import game.items.FogDoor;
import game.terrains.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static final String FIRELINK_SHRINE = "Firelink Shrine";
	public static final String ANOR_LONDO = "Anor Londo";
	public static final String PROFANE_CAPITAL = "Profane Capital";
	/**
	 * Aldrich's Initial location -X
	 */
	public static final int AldrichInitialX = 35;

	/**
	 * Aldrich's Initial location -Y
	 */
	public static final int AldrichInitialY = 20;

	public static void main(String[] args) {

			World world = new World(new Display());


			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery(),new Vendor(),new Bonfire(FIRELINK_SHRINE));

//"Firelink Shrine's Bonfire"
			// width = 80, height = 26
			List<String> profaneCapital = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					".......c............................................................c....+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#_______#......................+++........c...",
					"...........++.....................#__FB___#.......................+.............",
					".........+++......................________#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++........c.............+...............................+..+.....",
					"_..._....._._#.++......................+...........................c.......+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");

			GameMap gameMap = new GameMap(groundFactory, profaneCapital);
			world.addGameMap(gameMap);


			FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery(),new Vendor(),new Bonfire(ANOR_LONDO));
//"Anor Londo"
			List<String> anorLondo = Arrays.asList(
					"..++++++..+++..........................__.........###..........................",
					"........+++++......................+.___...B...###.................+++++.......",
					"...........+++.......................___.....###.....................+++++.c...",
					".....................................___#####..................c........++.....",
					".......c.............................___.................................+++...",
					"....................c................___..................................+++..",
					".....................................___.......................................",
					".....................................___.....................++++..............",
					".....................................___.....................+++++++...........",
					"..................................###___###...................+++..............",
					"...............................####_______####............c......+++###########",
					"...........++..................______F________....................__...........",
					".........+++...................####_______####....................__.......B...",
					"............+++...................####_####......................++++..........",
					"..............+......................#.#............................+++###___##",
					"..............++.....................#.#.......................................",
					"............+++................................................................",
					"+........................########___####################.......................",
					"+++.........#............_B.........._...#............__.......................",
					"............#............#...#....#........+..#..._....#.......................",
					"............#............#._...........................#..............++.......",
					".........###+###.........#...#................#........#.......................",
					".........................#.........#.......__..........#.......++..............",
					".........................##___##########################.......................",
					"...............................................................................");


			GameMap gameMap2 = new GameMap(groundFactory2,anorLondo);
			world.addGameMap(gameMap2);

			Actor player = new Player("Unkindled (Player)", '@', 1500);
			world.addPlayer(player, gameMap.at(36, 12));



			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new YhormTheGiant());
			//as the requirement said - manually place several Skeletons
			gameMap.at(38,4).addActor(new Skeleton("Skeleton",38,4));
			gameMap.at(28,17).addActor(new Skeleton("Skeleton",28,17));
			gameMap.at(70,25).addActor(new Skeleton("Skeleton",70,25));
			gameMap.at(55,15).addActor(new Skeleton("Skeleton",55,15));
			gameMap.at(0,0).addActor(new Skeleton("Skeleton",0,0));
			gameMap.at(68,3).addActor(new Skeleton("Skeleton",86,3));

			// FogDoor locations
			Location PortalInMap1 = gameMap.at(38,25);
			Location PortalInMap2 = gameMap2.at(38,0);

			// add a fogDoor(is not a portable item) in the first map
			// and add the allowable action - move actor to the destination
			FogDoor fogDoorP = new FogDoor(PROFANE_CAPITAL);
			PortalInMap1.addItem(fogDoorP);
			fogDoorP.addAction(new MoveActorAction(PortalInMap2, "to "+ANOR_LONDO));

			// add a fogDoor(is not a portable item) in the second map
			// and add the allowable action - move actor to the destination
			FogDoor fogDoorA = new FogDoor(ANOR_LONDO);
			PortalInMap2.addItem(fogDoorA);
			fogDoorA.addAction(new MoveActorAction(PortalInMap1, "to "+PROFANE_CAPITAL));

			//Place Aldrich the Devourer in the second map
			gameMap2.at(AldrichInitialX, AldrichInitialY).addActor(new AldrichTheDevourer(player));


			//Mimic/Chest
			Location location1 = gameMap.at(36,15);
			location1.addActor(new MimicOrChest(location1));

			world.run();

	}
}
