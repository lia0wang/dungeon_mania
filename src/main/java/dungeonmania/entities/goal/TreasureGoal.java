package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class TreasureGoal implements Goal{
    private Dungeon dungeon;

    public  TreasureGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        if (this.dungeon.getTreasures().size() == 0) {
            return true;
        }
        return false;
    }
}
