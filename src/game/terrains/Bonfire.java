package game.terrains;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.BonfiresManager;
import game.actions.ActivateBonfireAction;
import game.actions.RestAction;
import game.actions.TeleportAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.BonfireInterface;

import static game.Application.FIRELINK_SHRINE;

/**
 * A class that represents Bonfire.
 */
public class Bonfire extends Ground implements BonfireInterface {
    private static int tempNumber = 0;
    private int id;

    private String name;
    /**
     * temp name - get the value from a constructor and pass it to default
     * constructor
     */
    static String tempName;

    private Location location;


    /**
     * Each instance created in FancyGroundFactory will used default constructor finally
     * So, in order to pass in parameters, we have to build another constructor to same a temp parameter value.
     */
    public Bonfire() {
        super('B');
        setId();
        setName(tempName);
        initializeStatus();
        BonfiresManager.getInstance().collectLocation(this);
    }

    private void setId() {
        tempNumber += 1;
        id = tempNumber;
    }

    /**
     * Constructor - with parameter
     *
     * @param name
     */
    public Bonfire(String name) {
        super('B');
        setTempName(name);
    }

    /**
     * initialize Bonfire's status
     * Bonfire that doesn't have LIGHTED cannot be interacted directly
     */
    private void initializeStatus() {
        if (name == FIRELINK_SHRINE) {
            this.addCapability(Status.LIGHTED);
        }
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if (this.location == null) {
            setLocation(location);
        }
    }


    /**
     * Returns an Action list. (allowable actions that actors can do with the kind of ground)
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions to be executed
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        if (!this.hasCapability(Status.LIGHTED)) {
            actions.add(new ActivateBonfireAction(this));
        } else {
            if (actor.hasCapability(Abilities.REST)) {
                actions.add(new RestAction(this));

            }
            addTeleportAction(actions);
        }
        return actions;
    }

    private void addTeleportAction(Actions actions) {
        for (Bonfire bonfire : BonfiresManager.getInstance().getBonfires()) {
            if (bonfire != this) {
                actions.add(new TeleportAction(bonfire,this));
            }
        }
    }


    public String getName() {
        return name + "'s Bonfire" + id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static void setTempName(String tempName) {
        Bonfire.tempName = tempName;
    }

    public void setName(String name) {
        this.name = name;
    }
}
