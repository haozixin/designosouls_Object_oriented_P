package game.Terrains;

import edu.monash.fit2099.demo.mars.Wall;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Weapon;
import game.Player;
import game.enums.Abilities;
import jdk.swing.interop.SwingInterOpUtils;

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



}
