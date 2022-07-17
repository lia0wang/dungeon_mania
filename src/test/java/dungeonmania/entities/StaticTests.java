package dungeonmania.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import dungeonmania.*;
import dungeonmania.response.models.*;

public class StaticTests {
    @Test
    @DisplayName("Test the static entities are created")
    public void testBasicStaticEntities() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_staticTest_basicEntities", "c_movementTest_testMovementDown");

        assertEquals(new ArrayList<>(), res.getAnimations());
        assertEquals(new ArrayList<>(), res.getBattles());
        assertEquals(new ArrayList<>(), res.getBuildables());
        assertEquals("d_staticTest_basicEntities", res.getDungeonName());
        assertEquals(7, res.getEntities().size());
        assertEquals("(:exit AND :treasure) AND (:boulders AND :enemies)", res.getGoals());
        assertEquals(new ArrayList<>(), res.getInventory());
    }
}