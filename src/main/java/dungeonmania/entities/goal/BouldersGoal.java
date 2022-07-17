package dungeonmania.entities.goal;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticEntity.FloorSwitch;

public class BouldersGoal implements Goal{
    private Dungeon dungeon;

    public BouldersGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        if (this.dungeon.getFloorSwitches().stream().allMatch(s->((FloorSwitch) s).isTriggered())) {
            return true;
        }
        return false;
    }
}
