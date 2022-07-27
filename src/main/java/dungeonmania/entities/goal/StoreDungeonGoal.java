package dungeonmania.entities.goal;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.Dungeon;

public class StoreDungeonGoal {
    private Dungeon dungeon;
    //private ComplexGoalLogic allGoals;

    public StoreDungeonGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void addGoals(JSONObject goal, ComplexGoalLogic allGoals) {
        Goal subGoal = null;
        if (goal.getString("goal").equals("exit")) {
            subGoal = new ExitGoal(this.dungeon);
            allGoals.addGoal(subGoal);
        } else if (goal.getString("goal").equals("treasure")) {
            subGoal = new TreasureGoal(this.dungeon);
            allGoals.addGoal(subGoal);
        } else if (goal.getString("goal").equals("boulders")) {
            subGoal = new BouldersGoal(this.dungeon);
            allGoals.addGoal(subGoal);
        } else if (goal.getString("goal").equals("enemies")) {
            subGoal = new EnemiesGoal(this.dungeon);
            allGoals.addGoal(subGoal);
        } else if (goal.getString("goal").equals("AND")) {
            JSONArray subGoals = goal.getJSONArray("subgoals");
            ComplexGoalLogic and = new AndGoal();
            for (int i = 0; i < subGoals.length(); i++) {
                addGoals(subGoals.getJSONObject(i), and);
            }
            allGoals.addGoal(and);
        } else if (goal.getString("goal").equals("OR")) {
            JSONArray subGoals = goal.getJSONArray("subgoals");
            ComplexGoalLogic or = new OrGoal();
            for (int i = 0; i < subGoals.length(); i++) {
                addGoals(subGoals.getJSONObject(i), or);
            }
            allGoals.addGoal(or);
        }
    }
}
