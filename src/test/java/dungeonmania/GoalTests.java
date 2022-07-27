package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import static dungeonmania.TestUtils.getGoals;

public class GoalTests {
    @Test
    @DisplayName("Test the player sucessfully achieved basic exit goal")
    public void testBasicExitGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_goalTest_basicExitGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":exit"));

        // move to exit
        res = dmc.tick(Direction.DOWN);

        // goal string should be empty means the goal has achieved
        assertEquals("", getGoals(res));
    }

    @Test
    @DisplayName("Test the player sucessfully achieved basic enemies goal")
    public void testBasicEnemiesGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_goalTest_basicEnemiesGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":enemies"));

        // move to exit
        res = dmc.tick(Direction.RIGHT);

        assertEquals("", getGoals(res));
    }

    @Test
    @DisplayName("Test the player sucessfully achieved basic treasures goal")
    public void testBasicTreasureGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_goalTest_basicTreasureGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":treasure"));

        // move to exit
        res = dmc.tick(Direction.DOWN);
        
        assertEquals("", getGoals(res));
    }

    @Test
    @DisplayName("Test the player sucessfully achieved basic boulders goal")
    public void testBasicBouldersGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_goalTest_basicBouldersGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":boulders"));

        // move to exit
        res = dmc.tick(Direction.RIGHT);
        
        assertEquals("", getGoals(res));
    }

    @Test
    @DisplayName("Test the player sucessfully achieved advanced boulders goal")
    public void testAdvancedBouldersGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_goalAdvancedBoulders", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":boulders"));

        // move to exit
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.LEFT);
        
        assertEquals("", getGoals(res));
    }
}


