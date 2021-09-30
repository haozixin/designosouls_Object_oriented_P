package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actors.Player;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.PlayerInterface;

public class AttackBehaviour extends Actions implements Behaviour {


    private Actor target;


    /**
     * Constructor.
     */
    public AttackBehaviour() {
    }



    @Override
    public Action getAction(Actor actor, GameMap map) {

        // actor refers to enemies here
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor() instanceof Player ? true:false && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                target = destination.getActor();
                return new AttackAction(target,exit.getName());
            }
        }
        return null;
    }


}
