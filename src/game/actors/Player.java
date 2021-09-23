package game.actors;

import edu.monash.fit2099.engine.*;
import game.TokenOfSouls;
import game.actions.AttackAction;
import game.actions.HealAction;
import game.behaviours.FollowBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.PlayerInterface;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.weapons.MeleeWeapon;
import game.weapons.PlayerIntrinsicWeapon;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, PlayerInterface {
	// max health potion should belong to the whole player class,
	// something like game setting - players could have how many health potion
	public static final int MAX_HEALTH_POTION = 3;
	private int healthPotion;
	private int soul;
	private final Menu menu = new Menu();
	MeleeWeapon weapon;

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
		addCapabilities();
		weapon = new Broadsword();
		addItemToInventory(weapon);
		setSoul(0);
	}

	// add all capabilities or status that the player could have
	private void addCapabilities(){
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.DEAL);
	}


	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

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

	// get healAction if the player still has healthPotion(Estus)
	public Action getHealAction(){
		if (healthPotion>0){
			return new HealAction(this);
		}
		return null;
	}

	@Override
	public void transferSouls(Soul soulObject) {
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
		if(setSoul(this.soul+souls)){
			successful=true;
		};
		return successful;
	}

	@Override
	public boolean subtractSouls(int souls) {
		boolean successful = false;
		if(setSoul(this.soul-souls)){
			successful=true;
		};
		return successful;
	}

	// if the parameter is valid, set soul with the number,
	// otherwise, set it to 0 as default.
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

	public void subtractHPotion(){
		healthPotion = getHealthPotion()-1;
	}

	@Override
	public void hurt(int points) {
		hitPoints -= points;
		hitPoints = Math.max(hitPoints, 0);
	}



}
