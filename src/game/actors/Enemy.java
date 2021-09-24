package game.actors;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

import java.util.ArrayList;

public abstract class Enemy extends Actor {

    protected ArrayList<Behaviour> behaviours;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        behaviours = new ArrayList<>();
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
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
