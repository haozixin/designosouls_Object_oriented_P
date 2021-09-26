package game.weapons;

import edu.monash.fit2099.engine.Weapon;
//import game.interfaces.EnemyWeapon;

import java.util.Random;

public class YhormGreatMachete extends MeleeWeapon{

    public YhormGreatMachete() {
        super("Yhorm's Great Machete", '.', 95, null, 60);
    }

    public void emberForm() {
        hitRate+=30;
    }

}
