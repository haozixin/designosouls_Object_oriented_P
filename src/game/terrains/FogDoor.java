package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.actors.Player;

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
}
