package game.Terrains;

import edu.monash.fit2099.demo.mars.Wall;
import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.AttackAction;
import game.actions.ResurgenceAction;
import game.enums.Abilities;
import game.enums.Status;
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

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {

		Actions actions = new Actions();

		// passive action
		if(location.containsAnActor()){
			//you the actor can come in, it must be "Player"
			Player player = (Player)actor;
			int hurtPoint = player.getHitPoints();
			player.hurt(hurtPoint);

		}
		return actions;
	}
}
