package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Player;

public class HealAction extends Action {
    private Player target;
    private final int percentage= 40;


    public HealAction(Player target) {
        this.target = target;
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        int heal_points = (int) (target.getMaxHitPoints()*percentage*0.01);
        actor.heal(heal_points);
        target.subtractHPotion();
        return menuDescription(actor);
    }


    // it will shows console as menu options
    @Override
    public String menuDescription(Actor player) {
        return player + "drinks an Estus Flask("+target.getHealthPotion()+"/"+Player.getMaxHealthPotion()+")";
    }


}
