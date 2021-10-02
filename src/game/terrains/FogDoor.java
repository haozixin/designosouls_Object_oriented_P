package game.terrains;

import edu.monash.fit2099.engine.*;
import game.actions.TeleportAction;
import game.actors.Player;

public class FogDoor extends Ground {
    Location targetLocation;
    String targetLocationName;

    /**
     * Constructor.
     *
     */
    public FogDoor(Location targetLocation, String targetLocationName) {
        super('=');
        this.targetLocation = targetLocation;
        this.targetLocationName = targetLocationName;
    }

    /**
     * only player can enter(drop down)
     * @param actor the Actor to check
     * @return false or actor cannot enter.
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor instanceof Player ? true : false;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        // passive action -- Player will receive a lot of damage if the location contains him/her
        if(location.containsAnActor() && (actor instanceof Player ? true : false)){
            actions.add( new TeleportAction(targetLocation,targetLocationName));

        }
        return actions;
    }

}
