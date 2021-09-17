package game;

import game.interfaces.Soul;

//not useful for now
public class Money implements Soul {
    /**
     * Transfer current instance's souls to another Soul instance.
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {

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
