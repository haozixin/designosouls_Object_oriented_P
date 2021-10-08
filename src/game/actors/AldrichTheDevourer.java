package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Resettable;
import game.weapons.DarkmoonLongbow;
import game.weapons.MeleeWeapon;

public class AldrichTheDevourer extends LordOfCinder implements Resettable {

    public static final int SOULS = 5000;


    /**
     * Constructor.
     */
    public AldrichTheDevourer(Actor target) {
        super("Aldrich the Devourer", 'A', 350,target);
        bossWeapon = new DarkmoonLongbow();
    }


    @Override
    public void resetInstance() {

    }

    @Override
    public boolean isExist() {
        return false;
    }
}
