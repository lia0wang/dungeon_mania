package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class EnemiesGoal extends LeafGoal{
    public EnemiesGoal(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public boolean goalAchieved(String curString) {
        if (getDungeon().getEnemies().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String update(String curString) {
        if (goalAchieved(curString)) {
            return curString.replace(":enemies", "");
        } else {
            return curString;
        }
    }
}
