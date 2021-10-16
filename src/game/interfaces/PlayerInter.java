package game.interfaces;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.items.CindersOfLord;
import game.weapons.MeleeWeapon;

/**
 * the interface is used by other classes(actors/actions) which need to interact with Player or get attributes of player.
 * Such as the HealAction.
 * The interface can protect Player Class
 */
public interface PlayerInter {


    void drinkFlask(int healPercentage);


    String HealthPotionStatus();

    /**
     * Refill Player's health/hit points to the maximum
     * 	Refill Estus Flask to maximum charges
     */
    void refill();


    /**
     * add player's souls number
     * @param souls number of souls
     * @return boolean value to judge if it's successful
     */
    boolean addSouls(int souls);

    void replaceWeaponByC(MeleeWeapon weapon, CindersOfLord cindersOfLord);

    void replaceWeapon(MeleeWeapon weapon);

    boolean subtractSouls(int souls);

}
