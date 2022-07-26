package dungeonmania.entities.goal;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexGoalLogic implements Goal{
    List<Goal> subGoals = new ArrayList<>();

    public void addGoal(Goal goal) {
        this.subGoals.add(goal);
    }
    
    public void removeGoal(Goal goal) {
        this.subGoals.remove(goal);
    }

    public abstract boolean goalAchieved(String curString);
    public abstract String update(String curString);

}
