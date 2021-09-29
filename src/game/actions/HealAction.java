package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Player;
import game.interfaces.PlayerInterface;

/**
 * The class is for requirement1 - The player drinks Estus to heal himself
 *
 */
public class HealAction extends Action {
    /**
     *target of heal action -- player
     */
    private PlayerInterface target;

    /**
     * how much (in percentage) it would heal the target
     */
    private static final int PERCENTAGE = 40;

    /**
     * constructor
     * @param target the object of the target
     */
    public HealAction(Player target) {
        if (setTarget(target)){
        }
        else{
            System.out.println("Only Player can do healAction");
        }

    }

    /**
     * setter for the "target" attribute
     * @param target target of heal action -- player
     * @return boolean value -- if it sets target successful
     */
    public boolean setTarget(Player target) {
        boolean isPlayer = false;
        if (target instanceof Player ? true:false){
            this.target = target;
            isPlayer = true;
            return isPlayer;
        }
        return isPlayer;
    }

    /**
     * Execute the Action.
     * It will Restore hitPoints and reduces the number of Estus
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Restores hitPoints and reduces the number of Estus

        int heal_points = (int) (target.getMaxHitPoints()*PERCENTAGE*0.01);
        actor.heal(heal_points);
        target.subtractHPotion();
        return menuDescription(actor);
    }


    /**
     * it will shows message on console
     * @param player The actor performing the action.
     * @return a String that will shows console as menu options
     */
    @Override
    public String menuDescription(Actor player) {
        return player + "drinks an Estus Flask("+target.getHealthPotion()+"/"+Player.getMaxHealthPotion()+")";
    }

    @Override
    public String hotkey() {
        return "a";
    }
}
