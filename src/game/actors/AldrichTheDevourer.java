package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.items.CindersOfLord;
import game.weapons.DarkmoonLongbow;
import game.weapons.MeleeWeapon;

import static game.Application.AldrichInitialX;
import static game.Application.AldrichInitialY;

/**
 *  Aldrich The Devourer -- a kind of enemy (boss)
 */
public class AldrichTheDevourer extends LordOfCinder implements Resettable {
    /**
     * Constructor.
     */
    public AldrichTheDevourer(Actor target) {
        super("Aldrich the Devourer", 'A', 350, Status.FROM_ALDRICH);
        setBossBehaviours(target);
        bossWeapon = new DarkmoonLongbow();
        souls = 5000;
        //register instance for soft reset
        registerInstance();
    }


    @Override
    public void resetInstance(GameMap map, Actor actor) {
        setHitPoint(getMaxHitPoints());
        map.moveActor(this, map.at(AldrichInitialX, AldrichInitialY));
        removeCapability(Abilities.FOLLOW_PLAYER);
    }

    @Override
    public boolean isExist() {
        return true;
    }


}
