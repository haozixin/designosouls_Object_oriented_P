package game.terrains;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.RestAction;
import game.enums.Abilities;

/**
 * A class that represents Bonfire.
 */
public class Bonfire extends Ground {

    /**
     * Constructor
     */
    public Bonfire() {
        super('B');

    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.REST)){
            actions.add(new RestAction());
        }

        return actions;
    }


}
