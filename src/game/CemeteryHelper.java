package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.CemeteryHelperInt;
import game.terrains.Cemetery;

import java.util.ArrayList;
import java.util.List;

/**
 * The class is to help Cemetery to generate Undead automatically each turn
 * by getting the gameMap that created on Application Class and locations of Terrains
 *
 */
public class CemeteryHelper implements CemeteryHelperInt {
    List<String> map;
    private static GameMap gameMap;
    private ArrayList<Integer[]> locations;
    Actor player;

    /**
     * Constructor
     * @param map  the one we created on Application Class
     * @param gameMap the one we created on Application Class
     * @param player the one we created on Application Class
     */
    public CemeteryHelper(List<String> map, GameMap gameMap, Actor player) {
        this.map = map;
        this.gameMap = gameMap;
        locations = new ArrayList<>();
        this.player = player;
    }

    public List<String> getMap() {
        return map;
    }

    public ArrayList<Integer[]> getLocations() {
        return locations;
    }

    /**
     * find locations of 'c'(Cemetery) on the map and store it in a List
     *
     */
    public void findLocations() {
        Integer[] point;
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).length(); x++) {
                if (map.get(y).charAt(x) == Cemetery.CEMETERY_CHAR) {
                    point = new Integer[]{x, y};
                    locations.add(point);
                }
            }
        }

    }

    /**
     *  replace those 'c' with the one created by this class
     *  since we cannot change code that is on engine, and each cemetery must get gameMap to know the location of creating Undead
     */

    public void replaceCemetery() {
        Integer[] point;

        for (int i=0 ;i<locations.size();i++){
            point = locations.get(i);
            int x = point[0];
            int y = point[1];
            gameMap.at(x, y).setGround(new Cemetery(this));
        }

    }


    public GameMap getGameMap() {
        return gameMap;
    }

    public Actor getPlayer() {
        return player;
    }
}
