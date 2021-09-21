package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.weapons.Broadsword;

/**
 *
 */
public class BSwordDealAction extends Action {

    private Broadsword broadsword;

    public BSwordDealAction() {

        broadsword = new Broadsword();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.subtractSouls(broadsword.getPrice());
        player.removeItemFromInventory((Item) (player.getWeapon()));
        player.addItemToInventory(broadsword);
        System.out.println("We(Vendor) have update your inventory successfully");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" buys " + broadsword +"("+broadsword.getPrice() + " Souls)";
    }

}
