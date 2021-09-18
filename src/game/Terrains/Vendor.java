package game.Terrains;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.BSwordDealAction;
import game.actions.GiantAxeDealAction;
import game.enums.Abilities;

public class Vendor extends Ground {



    public Vendor() {
        super('F');

    }


    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if(actor instanceof Player ? true : false) {
            Player player = (Player) actor;
            if (actor.hasCapability(Abilities.DEAL)){
                if (player.getSoul()>=1000){
                    actions.add(new GiantAxeDealAction());
                    actions.add(new BSwordDealAction());
                }
                else if (player.getSoul()<1000 && player.getSoul()>=500){
                    actions.add(new BSwordDealAction());
                }
                else if (player.getSoul()<500){
                    System.out.println("Vendor: 'You cannot do any trade with me now, since your souls is not enough'");
                }
            }
        }

        return actions;

    }




}
