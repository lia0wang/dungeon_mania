package dungeonmania.entities.goal;

import java.util.ArrayList;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;
import dungeonmania.entities.staticEntity.Exit;

public class ExitGoal implements Goal{
    private Dungeon dungeon;

    public  ExitGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        Player player = this.dungeon.getPlayer();
        ArrayList<Entity> entitiesAtPlayer = this.dungeon.getAllEntitiesinPosition(player.getPositionX(), player.getPositionY());
			
        if (entitiesAtPlayer.stream().anyMatch(entity -> entity instanceof Exit)) {
            return true;
        }
        return false;
    }
}
