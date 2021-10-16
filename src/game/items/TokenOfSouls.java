package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.actions.PickUpTokenAction;
import game.interfaces.Soul;

/**
 * The Token of Souls
 * It will be used in soft-reset part
 */
public class TokenOfSouls extends PortableItem implements Soul {
    /**
     * the number of souls the token has/represents
     */
    int souls;

    public TokenOfSouls(int souls) {
        super("SoulsToken", '$');
        //souls maybe null or some invalid value
        this.souls = souls;
    }

    public TokenOfSouls() {
        super("SoulsToken", '$');
        this.souls = 0;
    }

    /**
     * setter - set the number of souls
     *
     * @param souls souls number
     */
    public void setSouls(int souls) {
        this.souls = souls;
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
    }

    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     * Override - getPickUpAction for the TokenOfSouls class will return PickUpTokenAction, actor will get souls from the item and will
     * not store it to his/her inventory.
     *
     * @param actor an actor that will interact with this item
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(portable)
            return new PickUpTokenAction(this);
        return null;
    }

    /**
     * Increase souls to current instance's souls.
     *
     * @param souls number of souls to be incremented.
     * @return transaction status. True if addition successful, otherwise False.
     */
    @Override
    public boolean addSouls(int souls) {
        boolean isValid = false;
        if (souls > 0) {
            this.souls += souls;
            isValid = true;
        }
        return isValid;
    }

    /**
     * Allow other classes to deduct the number of this instance's souls
     *
     * @param souls number souls to be deducted
     * @return transaction status. True if subtraction successful, otherwise False.
     */
    @Override
    public boolean subtractSouls(int souls) {
        boolean isValid = false;
        if (souls > 0) {
            this.souls -= souls;
            isValid = true;
        }
        return isValid;
    }

    @Override
    public String toString() {
        return souls+" souls";
    }
}

