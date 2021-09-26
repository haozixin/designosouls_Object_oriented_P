package game.weapons;

import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Weapon;
//import game.interfaces.EnemyWeapon;

import java.util.Random;

public class StormRuler extends MeleeWeapon implements Weapon {
    private int price;
    private int successRate;
    private int chargeCounter = 0;
    private boolean charged = false;

    public StormRuler() {
        super("Storm Ruler", '7', 70, "wind slash", 60);
        this.price = 2000;
        successRate = 20;
    }

    public int getPrice() {
        return price;
    }

    /**
     *
     * @return int extraDamage
     */
    public int doubleDamage() {
        Random r = new Random();
        if (r.nextInt() < successRate) {
            int extraDamage;
            extraDamage = this.damage()*2;
            return extraDamage;
        }
        else{
            return 0;
        }
    }

    /**
     * Storm Ruler deals half damage to undead or skeleton
     * @return int dullDamage
     */
    public int dull() {
        int dullDamage;
        dullDamage = this.damage()/2;
        return dullDamage;
    }

    public void chargeStormRuler() {
        chargeCounter++;
    }

    public boolean canSlash() {
        return chargeCounter >= 3;
    }

    public void doSlash() {
        chargeCounter = 0;
    }


    public void fullChargeStormRuler() {
        if (charged == true) {
            hitRate = 100;
            damage = damage*2;

        }
    }


}
