package dungeonmania.entities.goal;

public class OrGoal extends ComplexGoalLogic{
    public boolean goalAchieved(String curString) {
        boolean achieved = subGoals.get(0).goalAchieved(curString);
        for (Goal goal: subGoals) {
            achieved = achieved | goal.goalAchieved(curString);
        }
        return achieved;
    }

    public String update(String curString) {
        for (Goal goal: subGoals) {
            curString = goal.update(curString);
        }
        return curString;
    }
}
