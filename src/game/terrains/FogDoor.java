package game.terrains;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actors.Player;
import game.interfaces.PlayerInterface;

public class FogDoor extends Ground {
    /**
     * Constructor.
     *
     */
    public FogDoor() {
        super('=');
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
            PlayerInterface player = (Player)actor;

        }
        return actions;
    }

}
