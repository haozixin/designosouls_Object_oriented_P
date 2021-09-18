package game;

import edu.monash.fit2099.engine.Item;
import game.actors.Player;
import game.interfaces.Soul;

//not useful for now
public class TokenOfSouls extends PortableItem implements Soul {
    int souls;

    public TokenOfSouls(int souls) {
        super("SoulsToken", '$');
        //souls maybe null or some invalid value
        this.souls = souls;

    }

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
        //the soulObject should be Play type object, then it will go to addSouls() that is implemented in Player class,
        // otherwise the function will return false
        //when player pick up the item(souls), it should transfer Souls instance to player's souls(attribute)
        soulObject.addSouls(souls);
    }

    /**
     * Increase souls to current instance's souls.
     * By default, it cannot increase the souls.
     * You may override this method to implement its functionality.
     *
     * @param souls number of souls to be incremented.
     * @return transaction status. True if addition successful, otherwise False.
     */
    @Override
    public boolean addSouls(int souls) {

        return false;
    }

    /**
     * Allow other classes to deduct the number of this instance's souls
     * By default, an instance cannot get its own souls subtracted.
     * You may override this method to conduct subtraction on current souls.
     *
     * @param souls number souls to be deducted
     * @return transaction status. True if subtraction successful, otherwise False.
     */
    @Override
    public boolean subtractSouls(int souls) {

        return false;
    }
}
