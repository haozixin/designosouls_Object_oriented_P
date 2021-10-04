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
    MeleeWeapon initialWeapon;

    /**
     * Constructor.
     */
    public AldrichTheDevourer(Actor target) {
        super("Aldrich the Devourer", 'A', 350,target);
        initialWeapon = new DarkmoonLongbow();
        this.addItemToInventory(initialWeapon);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }


    @Override
    public void resetInstance() {

    }

    @Override
    public boolean isExist() {
        return false;
    }
}
