package game.interfaces;

/**
 * the interface is used by other classes(actors/actions) which need to interact with Player or get attributes of player.
 * Such as the HealAction.
 * The interface can protect Player Class
 */
public interface PlayerInterface {
    public int getMaxHitPoints();
    public void subtractHPotion();
    public int getHealthPotion();
    public int getHitPoints();
    public int getSoul();
    public void setHealthPotion(int healthPotion);
    public void setHitPoints(int hitPoints);
    public void hurt(int points);
    public boolean addSouls(int souls);
}
