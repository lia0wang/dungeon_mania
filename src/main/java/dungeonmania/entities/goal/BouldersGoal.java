package dungeonmania.entities.goal;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticEntity.FloorSwitch;

public class BouldersGoal extends LeafGoal{
    public BouldersGoal(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public boolean goalAchieved(String curString) {
        if (getDungeon().getFloorSwitches().stream().allMatch(s->((FloorSwitch) s).isTriggered())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String update(String curString) {
        if (getDungeon().getFloorSwitches().stream().allMatch(s->((FloorSwitch) s).isTriggered())) {
            String newString = curString.replace(":boulders", "");
            return newString;
        } else {
            return curString;
        }
    }
}
