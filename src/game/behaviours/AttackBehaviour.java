package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actors.Player;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.utilities.Utility;
import game.weapons.DarkmoonLongbow;

import static game.weapons.DarkmoonLongbow.DETECT_RANGE;

/**
 * The class' main responsibility is to detect when the enemy will attack the player
 */
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
        // and they have to in the same map
        if (actor.getWeapon() instanceof DarkmoonLongbow && (here.map() == there.map())) {
            DarkmoonLongbow darkmoonLongbow = (DarkmoonLongbow) actor.getWeapon();

            int distanceInX = Utility.distanceInX(here, there);
            int distanceInY = Utility.distanceInY(here, there);
            if (distanceInX > DETECT_RANGE || distanceInY > DETECT_RANGE) {

            } else {

                actor.addCapability(Abilities.FOLLOW_PLAYER);
                //When the player is within the range of attack
                // detect any wall on the path
                if(darkmoonLongbow.detectedWall(actor,map,target)){

                    darkmoonLongbow.blockedByWall();
                }
                else{
                    darkmoonLongbow.originalHitRate();
                }
                return new AttackAction(target,"that direction");
            }
        }


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
