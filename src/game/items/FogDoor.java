package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class FogDoor extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     */
    public FogDoor(String name) {
        super(name, '=', false);
    }

    /**
     * add allowable actions for the fog door
     * @param action the allowable action
     */
    public void addAction(Action action) {
        this.allowableActions.add(action);
    }


}
