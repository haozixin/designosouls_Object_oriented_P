package game.terrains;

import edu.monash.fit2099.engine.*;
import game.actions.TradeWeaponAction;
import game.actors.Player;
import game.enums.Abilities;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

public class Vendor extends Ground {



    public Vendor() {
        super('F');

    }


    /**
     * Returns an Action list. (allowable actions that actors can do with the kind of ground)
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Returns an Action list.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        //if the actor is the player, it will check if he has the ability to trade with vendor
        //if yes, then it will check if the actor has enough money(souls) to buy something

        if (actor.hasCapability(Abilities.DEAL)){
            Player player = (Player) actor;
            if (player.getSoul()>=1000){
                actions.add(new TradeWeaponAction(new GiantAxe()));
                actions.add(new TradeWeaponAction(new Broadsword()));
            }
            else if (player.getSoul()<1000 && player.getSoul()>=500){
                actions.add(new TradeWeaponAction(new Broadsword()));
            }
            else if (player.getSoul()<500){
                System.out.println("Vendor: 'You cannot do any trade with me now, since your souls is not enough'");
            }
        }


        return actions;

    }




}
