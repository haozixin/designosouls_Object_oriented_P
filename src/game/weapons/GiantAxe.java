package game.weapons;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponAction;


public class GiantAxe extends MeleeWeapon implements Weapon{


    public GiantAxe() {
        super("GiantAxe", 'G', 50, "Split to", 80);
        setPrice(1000);
        setSuccessRate(80);
    }


    //Weapon active skill - spin Attack
    public void activeSkill(){
        //
    }


    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return null;
    }
}
