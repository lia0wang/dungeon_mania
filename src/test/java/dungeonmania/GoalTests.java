package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static dungeonmania.TestUtils.getPlayer;

import java.util.ArrayList;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;


public class GoalTests {
    @Test
    @DisplayName("Test the player sucessfully achieved basic exit goal")
    void testBasicExitGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse initDungonRes = dmc.newGame("d_goalTest_basicExitGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":exit"));

        // move to exit
        res = dmc.tick(Direction.DOWN);

        // goal string should be empty means the goal has achieved
        assertEquals("", getGoals(res));
    }

    @Test
    @DisplayName("Test the player sucessfully achieved basic enemies goal")
    void testBasicEnemiesGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse initDungonRes = dmc.newGame("d_goalTest_basicEnemiesGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":enemies"));

        // move to exit
        res = dmc.tick(Direction.RIGHT);

        assertEquals("", getGoals(res));
    }

    @Test
    @DisplayName("Test the player sucessfully achieved basic treasures goal")
    void testBasicTreasureGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse initDungonRes = dmc.newGame("d_goalTest_basicTreasureGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":treasure"));

        // move to exit
        res = dmc.tick(Direction.DOWN);
        
        assertEquals("", getGoals(res));
    }

    @Test
    @DisplayName("Test the player sucessfully achieved basic boulders goal")
    void testBasicBouldersGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse initDungonRes = dmc.newGame("d_goalTest_basicBouldersGoal", "c_movementTest_testMovementDown");
        
        assertTrue(getGoals(res).contains(":boulders"));

        // move to exit
        res = dmc.tick(Direction.RIGHT);
        
        assertEquals("", getGoals(res));
    }
    
}


