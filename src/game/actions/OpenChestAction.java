package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.MimicOrChest;
import game.enums.Status;
import game.interfaces.ambiguousEnemy;

import java.util.Random;

public class OpenChestAction extends Action {
    public static final String CASE_1 = "The Chest monster become an enemy!";
    public static final String CASE_2 = "The Chest monster drops some token of souls!";
    ambiguousEnemy target;
    private String direction;

    public OpenChestAction(ambiguousEnemy target,String direction) {
        this.target = target;
        this.direction = direction;
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
        target.beOpened();

        Random r = new Random();
        // first case:
        if (r.nextBoolean()) {
            //replace its display character from "?" to be "M" and its name
            //give it back all normal behaviours
            target.becomeMimic();
            return CASE_1+"(with "+(target.countToken())+" tokens)";
        } else {
            // case II
            target.dropTokens(actor,map);
            return CASE_2+"(number of token: "+(target.countToken())+" )";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the Chest at " + direction;
    }



}
