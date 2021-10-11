package game.actors;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.weapons.YhormGreatMachete;

/**
 * The boss of Design o' Souls
 */
public class YhormTheGiant extends LordOfCinder {
    public static final int SOULS = 5000;

    /**
     * Constructor.
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'Y', 500, Status.FROM_YHORM);
        bossWeapon = new YhormGreatMachete();
    }



    /**
     *
     * @return SOULS
     */
    public static int getSOULS() {
        return SOULS;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(getHitPoint()<(getMaxHitPoints()/2) && !hasCapability(Status.SECOND_PHASE)){
            //the boss health reaches below half of its maximum hit points
            //it will enrages and its weapon becomes much more effective
            System.out.println(this+" is entering Second Phase(Ember Form)!");
            this.addCapability(Status.SECOND_PHASE);
            bossWeapon.addCapability(Status.RAGE_MODE);
            return bossWeapon.getActiveSkill(this,"all around");
        }

        return super.playTurn(actions, lastAction, map, display);
    }
}
