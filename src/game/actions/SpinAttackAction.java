package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;

public class SpinAttackAction extends WeaponAction {


    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public SpinAttackAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + weapon.verb() + weapon + " deals "+ weapon.damage();
    }
}
