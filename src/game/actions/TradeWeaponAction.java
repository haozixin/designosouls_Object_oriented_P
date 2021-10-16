package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.interfaces.PlayerInter;
import game.weapons.MeleeWeapon;

/**
 * The player trades with the Vendor by executed this class.
 * Based on what kind of weapon the vendor passes in, it can show different message and replace the player inventory with the new weapon.
 */
public class TradeWeaponAction extends Action {

    /**
     * the weapon that would be traded
     */
    private MeleeWeapon weapon;

    /**
     * Constructor
     * @param weapon MeleeWeapon
     */
    public TradeWeaponAction(MeleeWeapon weapon) {
        this.weapon = weapon;
    }



    /**
     * Execute the Action.
     * It will subtract Player's souls and replace player's weapon on inventory with the newest one
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the player.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        PlayerInter player = (PlayerInter) actor;
        // subtract souls(money) base on the weapon price
        player.subtractSouls(weapon.getPrice());
        // replace the current weapon with the new one
        player.replaceWeapon(weapon);
        System.out.println("We(Vendor) have update your inventory successfully");
        return menuDescription(actor);
    }


    /**
     * it will shows message on console
     * @param actor The actor performing the action.
     * @return a String that will shows console as menu options
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor+" buys " + weapon +"("+weapon.getPrice() + " Souls)";
    }

}
