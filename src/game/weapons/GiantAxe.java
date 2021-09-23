package game.weapons;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponAction;
import game.actions.SpinAttackAction;


public class GiantAxe extends MeleeWeapon implements Weapon{
    private int successRate;


    public GiantAxe() {
        super("GiantAxe", 'G', 50, "Split to", 80);
        setPrice(1000);

    }




    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new SpinAttackAction(this);
    }
}
