package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * SpinAttackAction will
 */
public class SpinAttackAction extends WeaponAction {


    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public SpinAttackAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Execute the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the player.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return menuDescription(actor);
    }

    /**
     * it will shows message on console
     * @param actor The actor performing the action.
     * @return a String that will shows console as menu options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + weapon.verb() + weapon + " deals "+ weapon.damage();
    }
}
