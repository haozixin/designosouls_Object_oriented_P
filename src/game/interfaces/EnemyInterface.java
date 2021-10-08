package game.interfaces;


public interface EnemyInterface {
    /**
     * Add points to the current Actor's hitpoint total.
     *
     * This cannot take the hitpoints over the Actor's maximum. If there is an
     * overflow, hitpoints are silently capped at the maximum.
     *
     * Does not check for consciousness: unconscious Actors can still be healed
     * if the game client allows.
     *
     * @param points number of hitpoints to add.
     */
    void heal(int points);

    /** Remove a capability from this Actor.
     *
     * @param capability the Capability to remove
     */
    void removeCapability(Enum<?> capability);



}
