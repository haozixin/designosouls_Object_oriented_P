package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actors.Player;
import game.weapons.GiantAxe;
import game.weapons.MeleeWeapon;

public class WeaponDealAction extends Action {

    private MeleeWeapon weapon;

    public WeaponDealAction(MeleeWeapon weapon) {

        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.subtractSouls(weapon.getPrice());
        player.removeItemFromInventory((Item) (player.getWeapon()));
        player.addItemToInventory(weapon);
        System.out.println("We(Vendor) have update your inventory successfully");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" buys " + weapon +"("+weapon.getPrice() + " Souls)";
    }

}
