package game.weapons;

import edu.monash.fit2099.engine.Weapon;
import game.interfaces.Weapon2;

public class GiantAxe extends MeleeWeapon implements Weapon, Weapon2 {
    private int price;

    public GiantAxe() {
        super("GiantAxe", 'G', 50, "Split to", 80);
        this.price = 1000;
    }

    public int getPrice() {
        return price;
    }
}
