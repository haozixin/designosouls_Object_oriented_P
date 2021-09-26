package game.weapons;

import edu.monash.fit2099.engine.Weapon;
//import game.interfaces.EnemyWeapon;

import java.util.Random;

public class YhormGreatMachete extends MeleeWeapon{
    /**
     * Constructor to make Yhorm's Great Machete (Lord of Cinder's weapon)
     */
    public YhormGreatMachete() {
        super("Yhorm's Great Machete", '.', 95, null, 60);
    }

    /**
     * A method to increase the weapon's hit rate
     */
    public void emberForm() {
        hitRate+=30;
    }

}
