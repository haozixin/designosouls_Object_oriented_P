package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.BonfiresManager;
import game.interfaces.BonfireTerrain;

/**
 * Teleport Action
 * Player will call the action when the player besides on a bonfire
 */
public class TeleportAction extends Action {
    /**
     * The bonfire that can teleport player to another bonfire
     */
    BonfireTerrain bonfire;

    /**
     * The target location where the player can be sent to
     */
    Location targetLocation;

    /**
     * The target location name
     */
    String targetLocationName;


    /**
     * Constructor
     * @param targetBonfire target bonfire
     * @param bonfire current bonfire that can teleport player to another bonfire
     */
    public TeleportAction(BonfireTerrain targetBonfire, BonfireTerrain bonfire) {
        this.bonfire = bonfire;
        this.targetLocation = targetBonfire.getLocation();
        this.targetLocationName = targetBonfire.toString();
    }

    public TeleportAction(Location targetLocation, String targetLocationName) {
        this.targetLocation = targetLocation;
        this.targetLocationName = targetLocationName;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        BonfiresManager.getInstance().setLastBonfireToI(bonfire);
        map.moveActor(actor,targetLocation);
        return menuDescription(actor);
    }


    /**
     * it will shows message on console
     * @param player The actor performing the action.
     * @return a String that will shows console as menu options
     */
    @Override
    public String menuDescription(Actor player) {
        return player + " moves to "+targetLocationName;
    }

}
