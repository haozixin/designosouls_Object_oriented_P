package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Skeleton;
import game.enums.Abilities;
import game.interfaces.SkeletonInterface;

import java.util.Random;


/**
 * When the Skeleton is going to die (i.e. hitPoint = 0), it can use its skill, the Action will be invoked on his turn.
 * The Skeleton will be removed from map if his RESURRECT skill is not successful,
 * otherwise he will be healed and remove RESURRECT ability, and keep fighting.
*/
public class ResurgenceAction extends Action {
    /**
     *target of the action -- enemies(only Skeleton for now)
     */
    SkeletonInterface target;

    public ResurgenceAction(SkeletonInterface target) {
        if (setTarget(target)){
        }
        else{
            System.out.println("Resurgence skill can only be used by Skeleton");;
        }
    }

    /**
     * setter
     * @param target target of the action
     * @return boolean value - judge if the parameter is valid
     */
    public boolean setTarget(SkeletonInterface target) {
        boolean isValid = false;
        if(target instanceof Skeleton ? true:false){
            this.target = target;
            isValid = true;
        }
        return isValid;
    }

    /**
     * Execute the Action.
     * It will heal the skeleton to Maximum health point and remove the ability(because the requirement is the skeleton can only use it once)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the player.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random r = new Random();
        if (r.nextInt(100)< Skeleton.RESURRECT_RATE){
            //healing the actor with a huge health point, which is bigger than maximum hitPoint.
            target.heal(1000);
            target.removeCapability(Abilities.RESURRECT);
            return menuDescription(actor);
        }else{
            map.removeActor((Skeleton)target);
            return target + "is killed! its skill is not active";
        }
    }
    /**
     * it will shows message on console
     * @param actor The actor performing the action.
     * @return a String that will shows console as menu options
     */
    @Override
    public String menuDescription(Actor actor) {
        return "The skeleton has resurrected by using skill -> "+ target;
    }



    // the function should be moved to other class and used when actor die
/*    private void showDeadMessage(){
        List<String> message = Arrays.asList(
                "++++......++++...........+++............++++.........++++................................................................................................",
                ".++++....++++........+++.....+++........++++.........++++...................######........############............####..............######...............",
                "..++++..++++.......+++........+++.......++++.........++++...................##.....#......##.....................##..##.............##.....#.............",
                "....+++++++........+++.........+++......++++.........++++...................##......#.....##....................###..###............##......#............",
                ".....+++++.........+++.........+++......++++.........++++...................##.......#....##...................###....###...........##.......#...........",
                "......+++..........+++.........+++......++++.........++++.....###########...##.......#....############........###......###..........##.......#...........",
                "......+++..........+++.........+++......++++.........++++...................##.......#....##.................##############.........##.......#...........",
                "......+++...........+++........+++......++++.........++++...................##.......#....##................###..........###........##.......#...........",
                "......+++............+++......+++.......++++.........++++...................##......#.....##...............###............###.......##......#............",
                "......+++.............+++...+++...........+++........+++....................##.....#......##...............###............###.......##.....#.............",
                "......+++...............+++++..............++++++.+++++.....................######........############.....##..............##.......######...............",
                "...............................................++++++....................................................................................................");

        for (int x=0; x<message.size();x++) {
            System.out.println(message.get(x));
        }
    }*/
}
