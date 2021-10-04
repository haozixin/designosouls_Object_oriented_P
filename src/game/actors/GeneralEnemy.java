package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Behaviour;

import java.util.ArrayList;

public abstract class GeneralEnemy extends Actor {

    protected ArrayList<Behaviour> behaviours;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public GeneralEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        behaviours = new ArrayList<>();
        behaviours.add(new FollowBehaviour());
        behaviours.add(new AttackBehaviour());
        behaviours.add(new WanderBehaviour());
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // use for loop to get all actions in the behaviours arraylist,
        // the sequence would be: can do attackAction?-->can do followBehaviour? --> can do wanderBehaviour?

        for(Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Do some damage to enemies. But the hitPoints only can go down to 0 at most.
     *
     * If the enemies' hitPoints go down to zero, it will be knocked out.
     *
     * @param points number of hitPoints to deduct.
     */
    @Override
    public void hurt(int points) {
        hitPoints -= points;
        hitPoints = Math.max(hitPoints, 0);
    }



}
