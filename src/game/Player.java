package game;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.HealAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import game.weapons.MeleeWeapon;
import edu.monash.fit2099.engine.IntrinsicWeapon;

import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul {
	private static final int MAX_HEALTH_POTION = 3;
	private int healthPotion;
	private int soul;


	private final Menu menu = new Menu2();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		setHealthPotion(MAX_HEALTH_POTION);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		addItemToInventory(new Broadsword("Broadsword",'S',30,"slash",20));
		soul = 0;

	}


	@Override
	public void addItemToInventory(Item item) {
		super.addItemToInventory(item);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		System.out.println(displayStatus());
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);

	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();

		return actions;
	}

	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
	}

	public void setHealthPotion(int healthPotion) {
		this.healthPotion = healthPotion;
	}

	@Override
	public char getDisplayChar() {
		return super.getDisplayChar();
	}

	public int getHealthPotion() {
		return healthPotion;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}


	public int getSoul() {
		return soul;
	}



	private String displayStatus(){
		String displayHitPoint="Unkindled:(" + getHitPoints() + "/" + getMaxHitPoints() + ')';

		String displayWeapon = "Holding " + this.getWeapon().toString();
		String displaySouls = getSoul()+" Souls";
		return displayHitPoint+", "+displayWeapon+", "+displaySouls;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {


		return new IntrinsicWeapon(5, "punches");
	}
}
