package game.interfaces;

/**
 * the interface is used by other classes(actors/actions) which need to interact with Player or get attributes of player.
 * Such as the HealAction.
 * The interface can protect Player Class
 */
public interface PlayerInterface {
    /**
     * Get the player's Maximum hitPoints
     * @return player's Maximum hitPoints
     */
    int getMaxHitPoints();

    /**
     * Subtract the number of Estus Flask
     */
    void subtractHPotion();

    /**
     * get the number of Estus Flask that the player holds now
     * @return the number of Estus Flask
     */
    int getHealthPotion();

    /**
     * get player's hit points
     * @return the player's hit points
     */
    int getHitPoints();

    /**
     * get player's souls number
     * @return number of souls
     */
    int getSoul();

    /**
     * set plyaer's healthPotion (Estus Flask)
     * @param healthPotion the number of Estus Flask
     */
    void setHealthPotion(int healthPotion);

    /**
     * set player's hitPoints
     * @param hitPoints hitPoints
     */
    void setHitPoints(int hitPoints);

    /**
     * Do some damage to the player.
     *
     * @param points number of hitpoints to deduct.
     */
    void hurt(int points);

    /**
     * add player's souls number
     * @param souls number of souls
     * @return boolean value to judge if it's successful
     */
    boolean addSouls(int souls);
}
