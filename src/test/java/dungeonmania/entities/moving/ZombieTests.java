package dungeonmania.entities.moving;

import static dungeonmania.TestUtils.getEntities;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;



public class ZombieTests {
    @Test
    @DisplayName("Test basic zombie movement")
    public void basicZombieMovement() {
        // Index order with seed 1 is 3 -> 0 -> 1 -> 2
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_basicMovement", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();

        // Create list of the correct position order
        List<Position> movementTrajectory = new ArrayList<Position>();
        movementTrajectory.add(new Position(x + 1, y));
        movementTrajectory.add(new Position(x + 1, y - 1));
        movementTrajectory.add(new Position(x + 1, y));
        movementTrajectory.add(new Position(x, y));
        
    
        for (int i = 0; i < movementTrajectory.size(); i++) {
            res = dmc.tick(Direction.UP);
            assertEquals(movementTrajectory.get(i), getEntities(res, "zombie_toast").get(0).getPosition());
        }
    }
}
