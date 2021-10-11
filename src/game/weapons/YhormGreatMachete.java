package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Abilities;
import game.enums.Status;
import game.weaponActions.BurnAction;
//import game.interfaces.EnemyWeapon;


public class YhormGreatMachete extends MeleeWeapon{

    private static final int extraHitRate = 30;
    private int burningTime;

    /**
     * Constructor to make Yhorm's Great Machete (Lord of Cinder's weapon)
     */
    public YhormGreatMachete() {
        super("Yhorm's Great Machete", 'A', 95, "slashes", 60);
        addCapability(Status.ONLY_FOR_YHORM);
        addCapability(Abilities.BURN_GROUND);
        setBurningTime(4);
    }

    public boolean setBurningTime(int burningTime) {
        boolean isValid = false;
        if (burningTime>=1){
            this.burningTime = burningTime;
            isValid=true;
        }

        return isValid;
    }

    /**
     * passive skill - increases the success hit rate
     */
    private int rageMode(){
        return hitRate + extraHitRate;
    }


    @Override
    public int chanceToHit() {
        if(hasCapability(Status.RAGE_MODE)){
            return rageMode();
        }else{
            return hitRate;
        }
    }


    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if(hasCapability(Status.RAGE_MODE)){
            return new BurnAction(this,burningTime);
        }
        return null;
    }
}
