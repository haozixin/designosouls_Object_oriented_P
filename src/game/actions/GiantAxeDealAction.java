package game.actions;

import edu.monash.fit2099.engine.*;
import game.interfaces.Weapon2;
import game.weapons.GiantAxe;

public class GiantAxeDealAction extends Action {

    private Weapon2 giantAxe;

    public GiantAxeDealAction() {

        giantAxe = new GiantAxe();
    }

    @Override
    public String execute(Actor actor, GameMap map) {


        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" buys " + giantAxe.toString()+"("+giantAxe.getPrice() + " Souls)";
    }




}
