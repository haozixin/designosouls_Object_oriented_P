package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class TeleportAction extends Action {

    Location targetLocation;
    String targetLocationName;


    public TeleportAction(Location targetLocation, String targetLocationName) {
        this.targetLocation = targetLocation;
        this.targetLocationName = targetLocationName;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
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
