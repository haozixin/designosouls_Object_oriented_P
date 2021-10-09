package game.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;

public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and items.
     */
    void resetInstance(GameMap map, Actor actor);

    /**
     * A useful method to clean up the list of instances in the ResetManager class
     * @return the existence of the instance in the game.
     * for example, true to keep it permanent, or false if instance needs to be removed from the reset list.
     */
    boolean isExist();

    /**
     * a default interface method that register current instance to the Singleton manager.
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
