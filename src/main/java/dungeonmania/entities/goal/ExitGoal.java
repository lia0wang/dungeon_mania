package dungeonmania.entities.goal;

import java.util.ArrayList;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;
import dungeonmania.entities.staticEntity.Exit;

public class ExitGoal extends LeafGoal{
    public  ExitGoal(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public boolean goalAchieved(String curString) {
        Player player = getDungeon().getPlayer();
        ArrayList<Entity> entitiesAtPlayer = getDungeon().getAllEntitiesinPosition(player.getPositionX(), player.getPositionY());
			
        if (entitiesAtPlayer.stream().anyMatch(entity -> entity instanceof Exit)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String update(String curString) {
        if (goalAchieved(curString)) {
            String newString = curString.replace(":exit", "");
            return newString;
        } else {
            return curString;
        }
    }
}
