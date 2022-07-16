package dungeonmania.entities.goal;

public class EnemiesGoal implements Goal{
    private Dungeon dungeon;

    public  EnemiesGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        
    }
}
