package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.weapons.MeleeWeapon;


/**
 * The boss of Design o' Souls
 * it is an abstract class because there are two kinds of LordOfCinder on different map
 */
public abstract class LordOfCinder extends Enemy {
    protected MeleeWeapon bossWeapon;
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints,Actor target) {
        super(name, displayChar, hitPoints );
        behaviours.clear();
        behaviours.add(new AttackBehaviour(target));
        behaviours.add(new FollowBehaviour(target));



        //behaviours.add(new WanderBehaviour());
    }

    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }


    @Override
    public Weapon getWeapon() {
        return bossWeapon;
    }
}
