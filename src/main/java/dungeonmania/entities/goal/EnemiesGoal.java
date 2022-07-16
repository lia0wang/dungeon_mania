package dungeonmania.entities.goal;

public class EnemiesGoal implements Goal{
    private Dungeon dungeon;

    public  EnemiesGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        /*
        if (getDungeon.getEnemies().size() == 0) {}
            return true;
        }
        return false;
         */
    }
}
