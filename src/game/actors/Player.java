package game.actors;

import edu.monash.fit2099.engine.*;
import game.BonfiresManager;
import game.items.CindersOfLord;
import game.items.TokenOfSouls;
import game.actions.HealAction;
import game.behaviours.SoftResetBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.PlayerInter;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.terrains.Bonfire;
import game.utilities.Utility;
import game.weapons.Broadsword;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.weapons.MeleeWeapon;
import game.weapons.PlayerIntrinsicWeapon;

/**
 * Class representing the Player on the gameMap.
 */
public class Player extends Actor implements Soul, PlayerInter, Resettable {
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
		MeleeWeapon weapon = new Broadsword();
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

	public String HealthPotionStatus(){
		return "("+this.getHealthPotion()+"/"+Player.getMaxHealthPotion()+")";
	}

	public void drinkFlask(int healPercentage){
		int heal_points = (int) (this.getMaxHitPoints()*healPercentage*0.01);
		this.heal(heal_points);
		this.subtractHPotion();
	}

	public void refill(){
		//Refill Player's health/hit points to the maximum
		//Refill Estus Flask to maximum charges
		this.setHealthPotion(Player.getMaxHealthPotion());
		this.setHitPoints(this.getMaxHitPoints());
	}

	public void replaceWeaponByC(MeleeWeapon weapon, CindersOfLord cindersOfLord){
		this.removeItemFromInventory(cindersOfLord);
		replaceWeapon(weapon);
	}

	public void replaceWeapon(MeleeWeapon weapon){
		this.removeItemFromInventory((Item) (this.getWeapon()));
		this.addItemToInventory(weapon);
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
	public void resetInstance(GameMap map, Actor actor) {
		Utility.showDeadMessage();
		this.setHealthPotion(Player.getMaxHealthPotion());
		this.setHitPoints(this.getMaxHitPoints());
		setSoul(0);

		// move player to the latest bonfire that he interact with
		// if player has do interaction with a bonfire, the last bonfire will be recorded
		// and the player will spawn at that bonfire
		if (BonfiresManager.getInstance().getLastBonfireToI() != null) {
			map.moveActor(actor, BonfiresManager.getInstance().getLastBonfireToI().getLocation());
		} else {
			// if he haven't interacted with any bonfire, go to the first one which is on the same game map
			for (Bonfire bonfire : BonfiresManager.getInstance().getBonfires()) {
				if (bonfire.getLocation().map() == map) {
					map.moveActor(actor, (bonfire.getLocation()));
				}
			}
		}
	}

	@Override
	public boolean isExist() {
		return true;
	}


}
