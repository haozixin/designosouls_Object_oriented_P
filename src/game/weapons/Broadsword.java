package game.weapons;


import edu.monash.fit2099.engine.Weapon;
import game.interfaces.EnemyWeapon;

import java.util.Random;

public class Broadsword extends MeleeWeapon implements Weapon {
    private int price;
    private int successRate;

    public Broadsword() {
        super("Broadsword", 'S', 30, "slash", 80);
        this.price = 500;
        successRate = 20;
    }

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


    public int getPrice() {
        return price;
    }


}
