package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.BonfiresManager;
import game.interfaces.BonfireTerrain;

public class TeleportAction extends Action {

    BonfireTerrain bonfire;
    Location targetLocation;
    String targetLocationName;


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
