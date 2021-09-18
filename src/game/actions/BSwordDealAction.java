package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Weapon2;
import game.weapons.Broadsword;

public class BSwordDealAction extends Action {

    private Weapon2 broadsword;

    public BSwordDealAction() {

        broadsword = new Broadsword();
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        //actor get the weapon and subtract his souls
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" buys " + broadsword.toString()+"("+broadsword.getPrice() + " Souls)";
    }

}
