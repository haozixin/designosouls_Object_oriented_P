package game;

import edu.monash.fit2099.engine.*;
import game.actions.HealAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.weapons.PlayerIntrinsicWeapon;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul {
	public static final int MAX_HEALTH_POTION = 3;
	private int healthPotion;
	private int soul;

	//replace Menu() with Menu2()
	private final Menu menu = new Menu2();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitPoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		setHealthPotion(MAX_HEALTH_POTION);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.DEAL);
		addItemToInventory(new Broadsword());
		soul = 5000;

	}


	@Override
	public void addItemToInventory(Item item) {
		super.addItemToInventory(item);
	}

	@Override
	public void removeItemFromInventory(Item item) {

		super.removeItemFromInventory(item);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		actions.add(getHealAction());
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

		//actions.add(getHealAction());
		return actions;
	}

	public HealAction getHealAction(){
		if (healthPotion>0){
			return new HealAction(this);
		}
		//
		return null;
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
		return new PlayerIntrinsicWeapon(5, "punches","fist");

	}

	@Override
	public boolean addSouls(int souls) {
		boolean successful = false;
		if(setSoul(getSoul()+souls)){
			successful=true;
		};
		return successful;
	}

	@Override
	public boolean subtractSouls(int souls) {
		boolean successful = false;
		if(setSoul(getSoul()-souls)){
			successful=true;
		};
		return successful;
	}

	public boolean setSoul(int soul) {
		boolean isValid=false;
		if(soul>=0){
			this.soul = soul;
			isValid=true;
		}
		return isValid;
	}

	public void subtractHPotion(){
		healthPotion = getHealthPotion()-1;
	}


}
