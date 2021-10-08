package game.weapons;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.utilities.Utility;

import java.util.Random;

public class DarkmoonLongbow extends MeleeWeapon {
    /**
     * by holding the weapon, Aldrich the Devourer(or other actors) can get a bigger range to attack and follow the enemy
     */
    public static final int DETECT_RANGE = 3;
    private int doubleDamageRate;
    private int lastHitRate;


    /**
     * Constructor.
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'D', 70, "shoots", 80);
        setDoubleDamageRate(15);
        lastHitRate = hitRate;
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
     * a passive skill - rangedWeapon
     * As required, the function is responsible when will the attack effect be considered miss
     * <p>
     * return true when there is wall between the Aldrich the Devourer and player -- means attack can be blocked
     * return false when the attack will not be blocked
     *
     * @param actor  actors who holds the weapon (should be Aldrich The Devourer here)
     * @param map    game map
     * @param target Target Actor (player here)
     * @return boolean value
     */
    public boolean detectedWall(Actor actor, GameMap map, Actor target) {

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        NumberRange xs, ys;
        if (here.x() == there.x() || here.y() == there.y()) {
            xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
            ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if(map.at(x, y).getGround().blocksThrownObjects()){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;

    }

    public void blockedByWall(){

        hitRate = 0;

    }

    public void originalHitRate(){
        hitRate = lastHitRate;
    }

}
