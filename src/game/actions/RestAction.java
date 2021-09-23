package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Player;
import game.interfaces.PlayerInterface;

/**
 * RestAction - player take the action to reset health/hit points and refill Estus Flask to maximum charges
 */
public class RestAction extends Action {
    PlayerInterface target;
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
            return null;
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Rest at FireLink Shrine Bonfire";
    }
}
