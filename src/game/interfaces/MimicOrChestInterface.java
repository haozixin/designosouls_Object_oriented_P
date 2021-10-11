package game.interfaces;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.List;

public interface MimicOrChestInterface {
    void removeCapability(Enum<?> capability);
    void setDisplayChar(char newDisplayChar);
    void setName(String newName);
    void setBehaviours();
    Location getLocation();
    void setHitPoints(int hitPoints);
    boolean isConscious();
    List<Item> getInventory();
}
