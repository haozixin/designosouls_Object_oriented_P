package game.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for CemeteryHelper
 * Cemetery will use the methods that from this interface to generate Undead Automatically
 */
public interface CemeteryHelperInt {
    List<String> getMap();
    ArrayList<Integer[]> getLocations();
    void findLocations();
    void replaceCemetery();
    GameMap getGameMap();
    Actor getPlayer();
}
