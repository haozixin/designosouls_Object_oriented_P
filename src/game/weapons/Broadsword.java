package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponAction;


import java.util.Random;

/**
 * Broadsword is a kind of MeleeWeapon
 */
public class Broadsword extends MeleeWeapon implements Weapon{
    // successRate to get a double damage
    // used for passive skill
    private int doubleDamageRate;

    /**
     * Constructor
     *
     */
    public Broadsword() {
        super("Broadsword", 'S', 30, "slash", 80);
        setPrice(500);
        setDoubleDamageRate(20);
    }

    /**
     * Critical Strike - Broadsword passive skill
     * it will be used in damage().
     */
    private int passiveSkill() {
        Random r = new Random();
        if ( r.nextInt(100) < doubleDamageRate) {
            return damage*2;
        }else{
            return damage;
        }
    }

    /**
     * When passive skill is active, it will return double damage
     * When passive skill is not active, it will return the original numeric damage
     * @return damage
     */
    @Override
    public int damage() {
        return passiveSkill();
    }

    public boolean setDoubleDamageRate(int successRate) {
        boolean isValid = false;
        if (successRate >= 0) {
            this.doubleDamageRate = successRate;
            isValid = true;
        }
        return isValid;
    }


    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return null;
    }
}
