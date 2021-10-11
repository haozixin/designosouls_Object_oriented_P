package game.weaponActions;

import edu.monash.fit2099.engine.*;
import game.terrains.BurnedArea;
import game.utilities.Utility;

/**
 * Any weapon has the ability of Burn Ground(same active skill) can invoke the Action
 * For now, it is for Yhorm's Great Machete
 */
public class BurnAction extends WeaponAction {
	private int burningTime;

	/**
	 * Constructor
	 *
	 * @param weaponItem the weapon item that has capabilities
	 */
	public BurnAction(WeaponItem weaponItem,int burningTime) {
		super(weaponItem);
		this.burningTime = burningTime;
	}


	/**
	 * Burning method to deal damage to player by 25 hp
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.getGround().getDisplayChar()=='.') {
				destination.setGround(new BurnedArea(burningTime));
			}
		}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor+"burns the area and skin is engulfed with fire(Ember Form)!";
	}

}
