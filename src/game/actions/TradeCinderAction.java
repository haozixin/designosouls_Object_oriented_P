package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.PlayerInter;
import game.items.CindersOfLord;
import game.weapons.MeleeWeapon;

/**
 * Trade action - trade weapons with Vendor using cindersOfLord
 *
 */
public class TradeCinderAction extends Action {
    CindersOfLord cindersOfLord;

    /**
     * the weapon that would be traded
     */
    private MeleeWeapon weapon;

    public TradeCinderAction(MeleeWeapon weapon, CindersOfLord cindersOfLord) {
        this.weapon = weapon;
        this.cindersOfLord = cindersOfLord;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //trade logic - replace weapon with cindersOfLord
        PlayerInter player = (PlayerInter) actor;
        player.replaceWeaponByC(weapon,cindersOfLord);
        System.out.println("We(Vendor) have update your inventory successfully");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" trades "+cindersOfLord;
    }
}
