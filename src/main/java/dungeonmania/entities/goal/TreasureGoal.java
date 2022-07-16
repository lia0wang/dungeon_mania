package dungeonmania.entities.goal;

public class TreasureGoal implements Goal{
    private Dungeon dungeon;

    public  TreasureGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        
    }
}
