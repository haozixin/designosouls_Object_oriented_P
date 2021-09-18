package game.weapons;


import java.util.Random;

public class Broadsword extends MeleeWeapon implements game.interfaces.Broadsword {
    private int price;
    private int successRate;

    public Broadsword() {
        super("Broadsword", 'S', 30, "slash", 80);
        this.price=1000;
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


}
