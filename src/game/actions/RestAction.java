package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.BonfiresManager;
import game.actors.Player;
import game.interfaces.BonfireTerrain;
import game.interfaces.PlayerInter;
import game.terrains.Bonfire;

/**
 * RestAction - player take the action to reset health/hit points and refill Estus Flask to maximum charges
 */
public class RestAction extends Action {
//    /**
//     *target of the RestAction -- player
//     */
//    PlayerInter target;
    //BonfireInterface bonfire;
    BonfireTerrain bonfire;


    public RestAction(Bonfire bonfire) {
        //this.bonfire = bonfire;
        this.bonfire=bonfire;
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
        // record the last bonfire that the player to interact with
        BonfiresManager.getInstance().setLastBonfireToI(bonfire);

        if (actor instanceof Player){
            ((PlayerInter)actor).refill();
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
        return actor+"Rest at "+ bonfire;
    }

    @Override
    public Action getNextAction() {
        return super.getNextAction();
    }
}
