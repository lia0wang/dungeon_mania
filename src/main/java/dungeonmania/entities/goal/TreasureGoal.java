package dungeonmania.entities.goal;

public class TreasureGoal implements Goal{
    private Dungeon dungeon;

    public  TreasureGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        // wait for Dungeon class
        /*
        if (getDungeon().getTreasures().size() == 0) {
            return true;
        }
        return false;
        */
    }
}
