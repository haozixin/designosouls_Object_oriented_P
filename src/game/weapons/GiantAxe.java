package game.weapons;

import edu.monash.fit2099.engine.Weapon;
//import game.interfaces.EnemyWeapon;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;


public class GiantAxe extends MeleeWeapon{


    /**
     * Constructor to make Giant Axe
     */
    public GiantAxe() {
        super("GiantAxe", 'G', 50, "Split to", 80);
        setPrice(1000);
    }

    /**
     *
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * special skill where it deals AOE damage that deals half the damage to its adjacent square
     */
    public void areaDamage() {
        damage = damage / 2;
    }


}
