package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.items.CindersOfLord;
import game.weapons.MeleeWeapon;


/**
 * The boss of Design o' Souls
 * it is an abstract class because there are two kinds of LordOfCinder on different map
 */
public abstract class LordOfCinder extends Enemy {
    protected MeleeWeapon bossWeapon;
    protected CindersOfLord cindersOfLord;
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints,Status host) {
        super(name, displayChar, hitPoints );
        cindersOfLord = new CindersOfLord("Cinders of a Lord(from "+name+")",host);
        addItemToInventory(cindersOfLord);
    }


    @Override
    protected void setBossBehaviours(Actor target) {
        behaviours.add(new AttackBehaviour(target));
        behaviours.add(new FollowBehaviour(target));
    }

    /**
     * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
     * You can do something else with this method.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public Weapon getWeapon() {
        return bossWeapon;
    }

    /**
     * override toString to show some basic information for each Undead, such as hitPoints, weapon that the skeleton holds and so on
     * @return
     */
    @Override
    public String toString() {
        return name+" ("+hitPoints+"/"+maxHitPoints+")"+" (holding "+bossWeapon+")";
    }


}
