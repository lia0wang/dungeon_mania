package dungeonmania.entities.goal;

public class ExitGoal implements Goal{
    private Dungeon dungeon;

    public  ExitGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean goalAchieved() {
        // wait for Dungeon class
        /*
        Player player = getDungeon().getPlayer();
        Exit exit = getDungeon().getExit();
        if (player.isAtSamePosition(exit)) {
            return true;
        }
        return false;
        */
    }
}
