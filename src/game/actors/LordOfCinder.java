package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Behaviour;

import java.util.ArrayList;


/**
 * The boss of Design o' Souls
 * it is an abstract class because there are two kinds of LordOfCinder on different map
 */
public abstract class LordOfCinder extends Actor {
    protected ArrayList<Behaviour> behaviours;
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints,Actor target) {
        super(name, displayChar, hitPoints );
        behaviours = new ArrayList<>();
        behaviours.add(new AttackBehaviour(target));
        behaviours.add(new FollowBehaviour(target));
        behaviours.add(new WanderBehaviour());
    }

    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for(Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    @Override
    public void hurt(int points) {
        hitPoints -= points;
        hitPoints = Math.max(hitPoints, 0);
    }


}
