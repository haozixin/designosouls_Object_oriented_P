package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actors.Player;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.PlayerInterface;
import game.utilities.Utility;
import game.weapons.DarkmoonLongbow;

import static game.weapons.DarkmoonLongbow.DETECT_RANGE;

public class AttackBehaviour extends Actions implements Behaviour {


    private Actor target;


    /**
     * Constructor.
     */
    public AttackBehaviour() {
    }


    public AttackBehaviour(Actor target) {
        this.target = target;
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {
        // "actor" refers to enemies here
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        // if actor holds the Darkmoon Longbow, the actor could attack as long as the enemy is within the range of 3 squares away
        //有BUG-可能需要判断在同一地图里
//        if (actor.getWeapon() instanceof DarkmoonLongbow) {
//            int distanceInX = Utility.distanceInX(here, there);
//            int distanceInY = Utility.distanceInY(here, there);
//            if (distanceInX > DETECT_RANGE || distanceInY > DETECT_RANGE) {
//            } else {
//                //get direction
//                return new AttackAction(target,"that direction");
//            }
//        }

        // else do following detection - but the maximum detect distance is one squares away
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if ((destination.getActor() instanceof Player) && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                target = destination.getActor();
                return new AttackAction(target, exit.getName());
            }
        }
        return null;
    }

}
