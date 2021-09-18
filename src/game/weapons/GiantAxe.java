package game.weapons;

import edu.monash.fit2099.engine.Weapon;

public class GiantAxe extends GameWeaponItem implements Weapon{
    private int price;

    public GiantAxe() {
        super("GiantAxe", 'G', 50, "Split to", 80);
        this.price = 1000;
    }

    public int getPrice() {
        return price;
    }
}
