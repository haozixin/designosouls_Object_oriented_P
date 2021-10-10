package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
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
        if(r.nextBoolean()){
            //replace its display character from "?" to be "M" and its name
            target.setDisplayChar('M');
            target.setName("Mimic");
            //give it back all normal behaviours
            target.setBehaviours();

            return CASE_1;
        }else {

            // the second case:

            return CASE_2;
        }
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the Chest";
    }


}
