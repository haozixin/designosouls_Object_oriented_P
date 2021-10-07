package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.BonfiresManager;
import game.enums.Status;
import game.interfaces.BonfireInterface;

public class ActivateBonfireAction extends Action {
    BonfireInterface bonfire;

    public ActivateBonfireAction(BonfireInterface bonfire) {
        this.bonfire = bonfire;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        BonfiresManager.getInstance().setLastBonfireToI(bonfire);

        bonfire.addCapability(Status.LIGHTED);
        System.out.println(actor + " has lighted the Bonfire");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" lights "+ bonfire.getName();
    }

}
