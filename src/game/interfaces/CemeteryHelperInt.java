package game.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.List;

public interface CemeteryHelperInt {
    List<String> getMap();
    ArrayList<Integer[]> getLocations();
    void findLocations();
    void replaceCemetery();
    GameMap getGameMap();
    Actor getPlayer();
}
