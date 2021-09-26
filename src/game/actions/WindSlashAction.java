package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.LordOfCinder;
import game.weapons.StormRuler;

public class WindSlashAction extends Action {
    private StormRuler weapon;
    private LordOfCinder target;


    public WindSlashAction(StormRuler weapon, LordOfCinder target) {
        this.weapon = weapon;
        this.target = target;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
    	weapon.doSlash();
    	target.hurt(weapon.damage() * 2);

        return menuDescription(actor);
    }


    // it will shows console as menu options
    @Override
    public String menuDescription(Actor player) {
        return player + " charged its StormRuler";
    }


}
