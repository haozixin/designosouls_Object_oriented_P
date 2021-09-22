package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponAction;


import java.util.Random;

public class Broadsword extends MeleeWeapon implements Weapon{


    public Broadsword() {
        super("Broadsword", 'S', 30, "slash", 80);
        setPrice(500);
        setSuccessRate(80);
    }

    //Critical Strike - Broadsword passive skill
    public int passiveSkill() {

        Random r = new Random();
        if (r.nextInt(100) < successRate) {
            int extraDamage;
            extraDamage = this.damage();
            return extraDamage;
        }else{
            return 0;
        }
    }




    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return null;
    }
}
