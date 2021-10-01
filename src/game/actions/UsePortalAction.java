package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class UsePortalAction extends Action {

    Location targetLocation;


    public UsePortalAction(Location targetLocation) {
        this.targetLocation = targetLocation;
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
        return "Pass " + player + " to the Anor Londo game map!";
    }

}
