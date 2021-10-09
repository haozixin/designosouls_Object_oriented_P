package game.actors;

import game.enums.Status;

public class MimicOrChest extends Enemy{
    /**
     * Constructor.
     *
     */
    public MimicOrChest() {
        super("Chest", '?', 0);
        lockBehaviours();
    }


    @Override
    public void addCapabilities() {

    }
}
