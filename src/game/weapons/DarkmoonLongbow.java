package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Abilities;
import game.utilities.Utility;

import java.util.Random;

/**
 * Boss weapon
 */
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

    /**
     * passive skill - detect if the actor(holding the weapon) can attack enemy/player
     * @param actor who holding the weapon
     * @param map game map
     * @param target target actor
     * @return if yes return attackAction, if no, return null
     */
    public AttackAction attackDetect(Actor actor,GameMap map,Actor target) {

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int distanceInX = Utility.distanceInX(here, there);
        int distanceInY = Utility.distanceInY(here, there);
        if (distanceInX > DETECT_RANGE || distanceInY > DETECT_RANGE) {
            // do nothing
            return null;
        } else {

            actor.addCapability(Abilities.FOLLOW_PLAYER);
            //When the player is within the range of attack
            // detect any wall on the path
            if (this.detectedWall(actor, map, target)) {

                this.blockedByWall();
            } else {
                this.originalHitRate();
            }
            return new AttackAction(target, "that direction");
        }

    }

    /**
     * the weapon has a bigger attack range
     * @param actor
     * @param map
     * @param target
     * @return
     */
    public boolean largerRangeDetect(Actor actor, GameMap map, Actor target){
        // the boss knows its target is the player but the player haven't been detected yet

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        int distanceInX = Utility.distanceInX(here,there);
        int distanceInY = Utility.distanceInY(here,there);
        if(distanceInX> DETECT_RANGE || distanceInY>DETECT_RANGE){
            return false;
        }else {
            return true;
        }
    }

}
