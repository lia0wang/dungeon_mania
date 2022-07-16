package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class EnemiesGoal implements Goal{
    private Dungeon dungeon;

    public  EnemiesGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        if (this.dungeon.getEnemies().size() == 0) {
            return true;
        }
        return false;
    }
}
