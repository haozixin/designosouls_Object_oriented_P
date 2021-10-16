package game.actions;

import edu.monash.fit2099.engine.*;
import game.interfaces.Soul;
import game.items.TokenOfSouls;

/**
 * an action that will be used when the player picks up a souls of Token
 * override PickUpItemAction
 */
public class PickUpTokenAction extends PickUpItemAction {


    public PickUpTokenAction(TokenOfSouls soulsToken) {
        super(soulsToken);

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        ((TokenOfSouls)item).transferSouls((Soul)actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + item;
    }
}
