package dungeonmania.entities.goal;

public class BouldersGoal implements Goal{
    private Dungeon dungeon;

    public BouldersGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        // wait for Dungeon class
        /*
        if (getDungeon().getSwitches().stream().allMatch(FloorSwitch::isTriggered))) {
            return true;
        }
        return false;
         */
    }
}
