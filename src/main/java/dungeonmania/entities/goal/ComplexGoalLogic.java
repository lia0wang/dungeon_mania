package dungeonmania.entities.goal;

import java.util.List;

public abstract class ComplexGoalLogic {
    private List<Goal> subGoals = new List<>();

    public void addGoal() {

    }

    public void removeGoal() {

    }

    public abstract boolean goalAchieved();

}
