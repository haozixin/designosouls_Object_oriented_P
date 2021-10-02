package game.interfaces;

import edu.monash.fit2099.engine.Location;

public interface BonfireInterface {
    String getName();
    void addCapability(Enum<?> capability);
    Location getLocation();
}
