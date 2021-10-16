package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.BonfiresManager;
import game.enums.Status;
import game.interfaces.BonfireTerrain;

/**
 * Activate bonfire action
 */
public class ActivateBonfireAction extends Action {
    BonfireTerrain bonfire;

    /**
     * Constructor
     * @param bonfire the bonfire that need to be lighted
     */
    public ActivateBonfireAction(BonfireTerrain bonfire) {
        this.bonfire = bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // bonfireManager will record the last bonfire that the actor interacts with
        BonfiresManager.getInstance().setLastBonfireToI(bonfire);
        // lights the bonfire
        bonfire.lightTheBonfire();

        System.out.println(actor + " has lighted the Bonfire");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" lights "+ bonfire;
    }

}
