package game.weaponActions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actors.Player;

/**
 * SpinAttackAction will damage opponent at surrounding adjacent
 */
public class SpinAttackAction extends Action {
    private int damage;

    /**
     * Constructor
     *
     * @param damage the weapon item that has capabilities
     */
    public SpinAttackAction(int damage) {
        this.damage = damage;
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
        Location actorLoc = map.locationOf(actor);
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if(i == 0 && j == 0) continue;
                Actor a = map.getActorAt(new Location(map, actorLoc.x() + i, actorLoc.y() + j));
                if(a != null)
                    a.hurt(damage/2);
            }
        }

        return menuDescription(actor);
    }

    /**
     * it will shows message on console
     * @param player The actor performing the action.
     * @return a String that will shows console as menu options
     */
    // it will shows console as menu options
    @Override
    public String menuDescription(Actor player) {
        return player + " spins around its axe!";
    }
}
