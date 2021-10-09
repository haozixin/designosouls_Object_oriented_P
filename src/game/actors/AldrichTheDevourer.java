package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
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
    private CindersOfLord cindersOfLord;

    /**
     * Constructor.
     */
    public AldrichTheDevourer(Actor target) {
        super("Aldrich the Devourer", 'A', 350);
        setBossBehaviours(target);

        bossWeapon = new DarkmoonLongbow();
        cindersOfLord = new CindersOfLord();
        addItemToInventory(cindersOfLord);

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

    /**
     * override toString to show some basic information for each Undead, such as hitPoints, weapon that the skeleton holds and so on
     * @return
     */
    @Override
    public String toString() {
        return name+" ("+hitPoints+"/"+maxHitPoints+")"+" (holding "+bossWeapon+")";
    }

    public static int getSOULS() {
        return SOULS;
    }


}
