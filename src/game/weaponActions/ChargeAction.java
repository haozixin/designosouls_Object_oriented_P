package game.weaponActions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.weapons.StormRuler;

public class ChargeAction extends Action {
    private StormRuler weapon;


    public ChargeAction(StormRuler weapon) {
        this.weapon = weapon;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
    	weapon.chargeStormRuler();
        return menuDescription(actor);
    }


    // it will shows console as menu options
    @Override
    public String menuDescription(Actor player) {
        return player + " charged its StormRuler";
    }


}
