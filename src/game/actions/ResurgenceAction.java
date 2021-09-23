package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Skeleton;
import game.enums.Abilities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/** All actors who have the ability of RESURRECT can use the action, it might has a chance(success rate) to resurrect.
 * When the actor is going to die (i.e. hitPoint = 0), the Action will be invoked on his turn. The actor will be removed from map if his RESURRECT skill is not successful,
 * otherwise he will be healed and remove RESURRECT ability, and keep fighting.
*/
public class ResurgenceAction extends Action {
    Actor target;

    public ResurgenceAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Random r = new Random();
        if (r.nextInt(100)< Skeleton.resurrectRate){
            //healing the actor with a huge health point, which is bigger than maximum hitPoint.
            target.heal(1000);
            target.removeCapability(Abilities.RESURRECT);
            return menuDescription(actor);
        }else{
            map.removeActor(target);
            return target + "is killed! its skill is not active";
        }
    }

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
