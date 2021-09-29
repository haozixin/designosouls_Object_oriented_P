package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actors.Player;
import game.interfaces.Behaviour;
import game.interfaces.PlayerInterface;

public class AttackBehaviour extends Actions implements Behaviour {

    /**
     * Player - target
     *
     * enemies target is only the player
     */
    private Actor target;


    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }



    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor)){
            return null;
        }

        // actor refers to enemies here
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        //if the player is in the adjacent place-(<=2)
        if (currentDistance<=2){
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getActor() instanceof Player ? true:false) {
                    System.out.println("------------------------hhh");
                    return new AttackAction(target,exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
