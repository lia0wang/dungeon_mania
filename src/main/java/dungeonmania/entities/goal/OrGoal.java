package dungeonmania.entities.goal;

public class OrGoal extends ComplexGoalLogic{
    public boolean goalAchieved() {
        boolean achieved = subGoals.get(0).goalAchieved();
        for (Goal goal: subGoals) {
            achieved = achieved | goal.goalAchieved();
        }
        return achieved;
    }
}
