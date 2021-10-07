package game.actors;

import edu.monash.fit2099.engine.*;
import game.ResetManager;
import game.TokenOfSouls;
import game.actions.AttackAction;
import game.actions.HealAction;
import game.behaviours.SoftResetBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.PlayerInterface;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.utilities.Utility;
import game.weapons.Broadsword;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.weapons.MeleeWeapon;
import game.weapons.PlayerIntrinsicWeapon;

import java.util.ArrayList;

/**
 * Class representing the Player on the gameMap.
 */
public class Player extends Actor implements Soul, PlayerInterface, Resettable {
	/**
	 * max health potion that belongs to the whole player class
	 * something like game setting - players could have how many health potion
	 */
	public static final int MAX_HEALTH_POTION = 3;

	/**
	 * Estus Flask
	 */
	private int healthPotion;
	/**
	 * souls(money) the player has
	 */
	private int soul;

	/**
	 * Menu to show options on player's turn
	 */
	private final Menu menu = new Menu();

	/**
	 * weapon that the player holds
	 */
	private MeleeWeapon weapon;



	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitPoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		soul = 0;
		healthPotion = MAX_HEALTH_POTION;
		addCapabilities();
		registerInstance();
		initializeWeapon();
	}

	private void initializeWeapon() {
		//as required the default initialWeapon is broadsword
		weapon = new Broadsword();
		addItemToInventory(weapon);
	}

	/**
	 * add all capabilities or status that the player could have
 	 */
	private void addCapabilities(){
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.DEAL);
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {



		// if the player is dead(hit points=0), the player will be reset.
		if(!this.isConscious()){
			SoftResetBehaviour softResetBehaviour = new SoftResetBehaviour();
			return softResetBehaviour.getAction(this,map);
		}
		// player could chose the option(healAction) on every turn
		actions.add(getHealAction());
		//it will show the status of the player at the top of menu on each turn
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
		//enemy can attack the player if the player has the status
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}

		return actions;
	}

	/**
	 * get healAction if the player still has enough healthPotion(Estus) - i.e. healthPotion > 0
 	 */
	public Action getHealAction(){
		if (healthPotion>0){
			return new HealAction(this);
		}
		return null;
	}


	/**
	 * Transfer current instance's souls to another Soul instance.
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		//soft-reset part
		//TODO: transfer Player's souls to another Soul's instance.
		TokenOfSouls tokenOfSouls = new TokenOfSouls(soul);
	}

	public void setHealthPotion(int healthPotion) {
		this.healthPotion = healthPotion;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public static int getMaxHealthPotion() {
		return MAX_HEALTH_POTION;
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


	/**
	 * Display the player's status
	 * @return a String contains the information(status) about HitPoint, Weapon, and Souls.
	 */
	private String displayStatus(){
		String displayHitPoint="Unkindled:(" + getHitPoints() + "/" + getMaxHitPoints() + ')';
		String displayWeapon = "Holding " + this.getWeapon().toString();
		String displaySouls = getSoul()+" Souls";
		return displayHitPoint+", "+displayWeapon+", "+displaySouls;
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new PlayerIntrinsicWeapon(5, "punches","fist");
	}

	/**
	 * Increase souls to current instance's souls(player's souls).
	 *
	 * @param souls number of souls to be incremented.
	 * @return transaction status. True if addition successful, otherwise False.
	 */
	@Override
	public boolean addSouls(int souls) {
		boolean successful = false;
		if(setSoul(this.soul+souls)){
			successful=true;
		};
		return successful;
	}

	/**
	 * Allow other classes to deduct the number of this instance's souls(player's souls)
	 * @param souls number souls to be deducted
	 * @return transaction status. True if subtraction successful, otherwise False.
	 */
	@Override
	public boolean subtractSouls(int souls) {
		boolean successful = false;
		if(setSoul(this.soul-souls)){
			successful=true;
		};
		return successful;
	}

	/**
	 * Setter for souls attribute
	 *
	 * if the parameter is valid, set soul with the number.
	 * Otherwise, set it to 0 as default.
	 * @param soul the value(int) of souls that should be set
	 * @return boolean value to judge if the parameter is valid
	 */
	private boolean setSoul(int soul) {
		boolean isValid=false;
		if(soul>=0){
			this.soul = soul;
			isValid=true;
		}else{
			this.soul=0;
		}
		return isValid;
	}

	/**
	 * subtract the number of Estus Flask
	 */
	public void subtractHPotion(){
		healthPotion = getHealthPotion()-1;
	}

	/**
	 * Do some damage to the player. But the hitPoints goes down to 0 at most
	 *
	 * If the player's hitpoints go down to zero, it will be knocked out.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		hitPoints = Math.max(hitPoints, 0);
	}

	@Override
	public void removeItemFromInventory(Item item) {
		super.removeItemFromInventory(item);
	}

	@Override
	public Weapon getWeapon() {
		return super.getWeapon();
	}

	@Override
	public void addItemToInventory(Item item) {
		super.addItemToInventory(item);
	}
// resettable interface functions
	/**
	 * Allows any classes that use this interface to reset abilities, attributes, and items.
	 *
	 */
	@Override
	public void resetInstance() {
		Utility.showDeadMessage();
		this.setHealthPotion(Player.getMaxHealthPotion());
		this.setHitPoints(this.getMaxHitPoints());
		setSoul(0);
	}

	@Override
	public boolean isExist() {
		return false;
	}


}
