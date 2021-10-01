package game.terrains;

import edu.monash.fit2099.engine.*;
import game.actions.UsePortalAction;
import game.actors.Player;
import game.interfaces.PlayerInterface;

public class FogDoor extends Ground {
    Location targetLocation;


    /**
     * Constructor.
     *
     */
    public FogDoor(Location targetLocation) {
        super('=');
        this.targetLocation = targetLocation;
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
            actions.add( new UsePortalAction(targetLocation));

        }
        return actions;
    }

}
