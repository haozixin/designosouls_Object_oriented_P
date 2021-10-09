package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.List;

public class Chest extends Item {
    /***
     * Constructor.
     */
    public Chest() {
        super("Chest", '?', false);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }
}
