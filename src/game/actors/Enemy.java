package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.ArrayList;

/**
 * An abstract class represents all Enemy
 */
public abstract class Enemy extends Actor implements game.interfaces.Enemy {
    /**
     * SOULS - how many souls the Undead could yield when it's killed / how many souls the player could get
     * from the skeleton after killing it
     */
    protected int souls;

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

    protected void addCapabilities(){};

    /**
     * set enemies behaviours
     * if the enemy is not locked, it will have these default behaviours in the specific sequence.
     */
    protected void setBehaviours(){
        if(!hasCapability(Status.LOCKED)){
            behaviours.add(new FollowBehaviour());
            behaviours.add(new AttackBehaviour());
            behaviours.add(new WanderBehaviour());
        }
    }


    protected void setBossBehaviours(Actor target){}

    /**
     * some enemies could use this method to get LOCKED status,
     * that enemy will lose all behaviours until it is unlocked(opened)
     */
    protected void lockBehaviours(){
        addCapability(Status.LOCKED);
        behaviours.clear();
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

    protected void setHitPoint(int hitPoint){
        this.hitPoints = hitPoint;
    }

    protected int getMaxHitPoints() {
        return maxHitPoints;
    }

    protected int getHitPoint(){return hitPoints;}

    public int getSouls(){return souls;}

}
