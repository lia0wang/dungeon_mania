package dungeonmania.entities.goal;

public class ExitGoal implements Goal{
    private Dungeon dungeon;

    public  ExitGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        
    }
}
