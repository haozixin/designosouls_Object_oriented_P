package game.weapons;

public class GiantAxe extends MeleeWeapon {
    private int price;

    public GiantAxe() {
        super("GiantAxe", 'G', 50, "Split to", 80);
        this.price = 1000;
    }
}
