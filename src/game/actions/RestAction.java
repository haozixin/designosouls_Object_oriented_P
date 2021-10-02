package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Player;
import game.interfaces.BonfireInterface;
import game.interfaces.PlayerInterface;
import game.terrains.Bonfire;

/**
 * RestAction - player take the action to reset health/hit points and refill Estus Flask to maximum charges
 */
public class RestAction extends Action {
    /**
     *target of the RestAction -- player
     */
    PlayerInterface target;
    //BonfireInterface bonfire;
    String bonfireName;


    public RestAction(String bonfireName) {
        //this.bonfire = bonfire;
        this.bonfireName=bonfireName;
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
        if (actor instanceof Player ? true:false){
            //Refill Player's health/hit points to the maximum
            //Refill Estus Flask to maximum charges
            target = (Player) actor;
            target.setHealthPotion(Player.getMaxHealthPotion());
            target.setHitPoints(target.getMaxHitPoints());
            return menuDescription(actor);
        }else{
            // other actors will not trigger the action
            return null;
        }
    }

    /**
     * it will shows message on console
     * @param actor The actor performing the action.
     * @return a String that will shows console as menu options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+"Rest at "+ bonfireName;
    }
}
