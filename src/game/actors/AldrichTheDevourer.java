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

public class AldrichTheDevourer extends LordOfCinder implements Resettable {

    /**
     * SOULS - how many souls the skeleton could yield when it's killed / how many souls the player could get
     * from the skeleton after killing it
     */
    public static final int SOULS = 5000;


    /**
     * Constructor.
     */
    public AldrichTheDevourer(Actor target) {
        super("Aldrich the Devourer", 'A', 350, Status.FROM_ALDRICH);
        setBossBehaviours(target);
        bossWeapon = new DarkmoonLongbow();
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



    public static int getSOULS() {
        return SOULS;
    }


}
