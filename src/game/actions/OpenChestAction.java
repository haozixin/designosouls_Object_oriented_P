package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.MimicOrChest;
import game.enums.Status;
import game.interfaces.MimicOrChestInterface;

import java.util.Random;

public class OpenChestAction extends Action {
    public static final String CASE_1 = "The Chest monster become an enemy!";
    public static final String CASE_2 = "The Chest monster drops some token of souls!";
    MimicOrChestInterface target;

    public OpenChestAction(MimicOrChestInterface target) {
        this.target = target;
    }

    /**
     *
     * @param actor The actor performing the action, the player
     * @param map The map the actor is on.
     * @return A string message shown on the console
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // open the chest
        // remove the Lock on the ambiguous enemy first
        target.removeCapability(Status.LOCKED);

        Random r = new Random();
        // first case:
        if (r.nextBoolean()) {

            //replace its display character from "?" to be "M" and its name
            target.setDisplayChar('M');
            target.setName("Mimic");
            //give it back all normal behaviours
            target.setBehaviours();
            return CASE_1+"(with "+(target.getInventory().size())+" tokens)";
        } else {
            target.setHitPoints(0);
            int tokenNumber = target.getInventory().size();
            if (!target.isConscious()) {
                Actions dropActions = new Actions();
                // drop all items
                for (Item item : target.getInventory())
                    dropActions.add(item.getDropAction(actor));
                for (Action drop : dropActions)
                    drop.execute((MimicOrChest)target, map);
                map.removeActor((MimicOrChest)target);
            }
            return CASE_2+"(number of token: "+tokenNumber+" )";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the Chest";
    }



}
