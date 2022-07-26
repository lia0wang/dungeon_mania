package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class TreasureGoal extends LeafGoal{
    public  TreasureGoal(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public boolean goalAchieved(String curString) {
        if (getDungeon().getTreasures().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String update(String curString) {
        if (getDungeon().getTreasures().size() == 0) {
            String newString = curString.replace(":treasure", "");
            return newString;
        } else {
            return curString;
        }
    }
}
