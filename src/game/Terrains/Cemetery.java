package game.Terrains;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

import java.util.Random;

public class Cemetery extends Ground {
    private int successRate;




    public Cemetery() {
        super('c');
        addCapability(Abilities.CREATE_UNDEAD);
        successRate=25;

    }

    private void createUndead(){

        //find
        //gameMap.at(32, 7).addActor(new Undead("Undead"));


    }

    @Override
    public void tick(Location location) {
        Random r = new Random();
        if (r.nextInt(100)<successRate){
            createUndead();
        }
    }

}
