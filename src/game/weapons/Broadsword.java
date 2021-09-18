package game.weapons;


import edu.monash.fit2099.engine.Weapon;
import game.interfaces.Weapon2;

import java.util.Random;

public class Broadsword extends MeleeWeapon implements Weapon2, Weapon {
    private int price;
    private int successRate;

    public Broadsword() {
        super("Broadsword", 'S', 30, "slash", 80);
        this.price=500;
        successRate=20;
    }
    public boolean BsPassiveSkill(){

        Random r = new Random();
        if (r.nextInt(100)<successRate){
            this.damage = this.damage()*2;
            return true;
        }
        return false;
    }


    public int getPrice() {
        return price;
    }
}
