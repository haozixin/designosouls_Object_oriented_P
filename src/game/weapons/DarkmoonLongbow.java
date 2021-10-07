package game.weapons;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.utilities.Utility;

import java.util.Random;

public class DarkmoonLongbow extends MeleeWeapon implements Weapon {
    /**
     * by holding the weapon, Aldrich the Devourer(or other actors) can get a bigger range to attack and follow the enemy
     */
    public static final int DETECT_RANGE = 3;
    private int doubleDamageRate;

    /**
     * Constructor.
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'D', 70, "shoots", 80);
        setDoubleDamageRate(15);
    }

    /**
     * Critical Strike - DarkmoonLongbow passive skill
     * it will be used in damage().
     */
    private int criticalHit() {
        Random r = new Random();
        if (r.nextInt(100) < doubleDamageRate) {
            return damage * 2;
        } else {
            return damage;
        }
    }

    @Override
    public int damage() {
        return criticalHit();
    }

    public boolean setDoubleDamageRate(int successRate) {
        boolean isValid = false;
        if (successRate >= 0) {
            this.doubleDamageRate = successRate;
            isValid = true;
        }
        return isValid;
    }


    /**
     * a passive skill
     * As required, the function is responsible when will the attack effect be considered miss
     * <p>
     * return false when there is wall between the Aldrich the Devourer and player -- means miss
     * return true when the attack can be blocked (since there is a wall between the Aldrich the Devourer and player)
     *
     * @param actor  actors who holds the weapon (should be Aldrich The Devourer here)
     * @param map    game map
     * @param target Target Actor (player here)
     * @return boolean value
     */
    private boolean rangedWeapon(Actor actor, GameMap map, Actor target) {

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        int distanceInX = Utility.distanceInX(here, there);
        int distanceInY = Utility.distanceInY(here, there);
        if (distanceInX == Utility.sameLine && distanceInY <= DETECT_RANGE) {
            //detect any wall there
        } else if (distanceInX <= DETECT_RANGE && distanceInY == Utility.sameLine) {

        }


        return false;
    }

}
