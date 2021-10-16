package game.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.List;

public interface ambiguousEnemy {
    void beOpened();
    void becomeMimic();
    int countToken();
    void dropTokens(Actor actor, GameMap map);

//    Location getLocation();
//    void setHitPoints(int hitPoints);
//    boolean isConscious();
//    List<Item> getInventory();
}
