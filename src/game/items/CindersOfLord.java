package game.items;

import edu.monash.fit2099.engine.Item;
import game.enums.Status;

/**
 * Corpse for Aldrich the Devourer
 *
 */
public class CindersOfLord extends Item {

	/**
	 * Constructor class to make an item called Cinders of Lord
	 */
	public CindersOfLord(String name, Status status) {
		super(name, '%', true);
		addCapability(status);
	}


}
