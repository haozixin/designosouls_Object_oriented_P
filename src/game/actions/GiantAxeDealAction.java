package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.weapons.GiantAxe;

public class GiantAxeDealAction extends Action {

    private GiantAxe giantAxe;

    public GiantAxeDealAction() {

        giantAxe = new GiantAxe();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.subtractSouls(giantAxe.getPrice());
        player.removeItemFromInventory((Item) (player.getWeapon()));
        player.addItemToInventory(giantAxe);
        System.out.println("We(Vendor) have update your inventory successfully");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" buys " + giantAxe +"("+giantAxe.getPrice() + " Souls)";
    }




}
