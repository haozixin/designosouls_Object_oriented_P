package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

public class MeleeWeapon extends WeaponItem implements Weapon {
    protected int price;
    protected int successRate;
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public MeleeWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    // player cannot drop weapons because of the function return null
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    public boolean setPrice(int price) {
        boolean isValid = false;
        if (successRate >= 0) {
            this.price = price;
            isValid = true;
        }
        return isValid;
    }

    public boolean setSuccessRate(int successRate) {
        boolean isValid = false;
        if (successRate >= 0) {
            this.successRate = successRate;
            isValid = true;
        }
        return isValid;
    }
    public int getPrice() {
        return price;
    }

    public int getSuccessRate() {
        return successRate;
    }

}
