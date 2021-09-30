package game.terrains;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.enums.Abilities;
import game.interfaces.PlayerInterface;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
		addCapability(Abilities.VALLEY_DAMAGE);
	}

	/**
	 * only player can enter(drop down)
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){

		return actor instanceof Player ? true : false;

	}

	/**
	 * Returns an Action list. (allowable actions that actors can do with the kind of ground)
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return actions to be executed
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {

		Actions actions = new Actions();

		// passive action -- Player will receive a lot of damage if the location contains him/her
		if(location.containsAnActor() && (actor instanceof Player ? true : false)){
			PlayerInterface player = (Player)actor;

		}
		return actions;
	}

}
