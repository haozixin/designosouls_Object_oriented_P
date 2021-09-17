package game.weapons;


public class Broadsword extends MeleeWeapon implements game.interfaces.Broadsword {
    private int price;
    private int successRate;

    public Broadsword() {
        super("Broadsword", 'S', 30, "slash", 80);
        this.price=1000;
        successRate=20;
    }


    //TODO: write weapon skills(interface) here
}
