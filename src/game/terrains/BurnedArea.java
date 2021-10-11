package game.terrains;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actors.Player;
import game.weapons.YhormGreatMachete;

public class BurnedArea extends Ground {
    private int burningTime;

    /**
     * Constructor.
     */
    public BurnedArea(int burningTime) {
        super('v');
        this.burningTime = burningTime;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }


    @Override
    public void tick(Location location) {
        --burningTime;
        if(burningTime<0){
            location.setGround(new Dirt());
        }
        super.tick(location);
        // actor is on the burned area but without holding that weapon
        if (location.containsAnActor() && !(location.getActor().getWeapon() instanceof YhormGreatMachete)) {
            int hurtPoint = 25;
            location.getActor().hurt(hurtPoint);
        }
    }
}
