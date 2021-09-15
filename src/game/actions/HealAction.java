package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.Player;
import game.enums.Status;

import static game.enums.Status.*;

public class HealAction extends Action {
    private Player target;
    private float percentage;

    public HealAction(Player target, float percentage) {
        this.target = target;
        this.percentage = percentage;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        int heal_points = (int) (target.getMaxHitPoints()*percentage);
        actor.heal(heal_points);
        return null;
    }


    // it will shows console as menu options
    @Override
    public String menuDescription(Actor player) {
        return player + "drinks an Estus Flask";
    }
}
