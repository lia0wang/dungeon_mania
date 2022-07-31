package dungeonmania.entities.moving;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.countEntityOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testBasicZombieMovement() {
        // First element in the array after shuffling should be 3
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random());

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_basicMovement", "c_spiderTest_basicMovement");

        // amount of times that zombie moved in the UP direction 
        int amount = 0;
        
        // After the first tick, the zombie should move up once
        for (int i = 0; i < 20; i++) {
            int x = getEntities(res, "zombie_toast").get(0).getPosition().getX();
            int y = getEntities(res, "zombie_toast").get(0).getPosition().getY();
            res = dmc.tick(Direction.UP);

            Position zombiePos = getEntities(res, "zombie_toast").get(0).getPosition();
            Position upPosition = new Position(x, y - 1);

            if (zombiePos.equals(upPosition)) {
                amount += 1;
            }
        }
        
        assertTrue(amount > 4);
    }

    @Test
    @DisplayName("Test zombie spawning rate 0")
    public void testZombieSpawnRate0() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_movementTest_testMovementNoWalls", "c_spiderTest_basicMovement");

        for (int i = 0; i < 20; i++) {
            res = dmc.tick(Direction.UP);
            assertEquals(countEntityOfType(res, "zombie_toast"), 0);
        }
    }

    @Test
    @DisplayName("Test zombie spawning rate 4")
    public void testZombieSpawnRate4() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_movementTest_testMovementNoWalls", "c_enemyTest_spawnRate4");
        int spawnCnt = 0;
        
        for (int i = 0; i <= 20; i++) {
            res = dmc.tick(Direction.UP);
            if (i % 4 == 0 && i != 0) {
                spawnCnt++;
                assertEquals(countEntityOfType(res, "zombie_toast"), spawnCnt);
            }
        }
    }

    @Test
    @DisplayName("Test zombie spawning rate 8")
    public void testZombieSpawnRate8() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_movementTest_testMovementNoWalls", "c_enemyTest_spawnRate8");
        int spawnCnt = 0;
        
        for (int i = 0; i <= 32; i++) {
            res = dmc.tick(Direction.UP);
            if (i % 8 == 0 && i != 0) {
                spawnCnt++;
                assertEquals(countEntityOfType(res, "zombie_toast"), spawnCnt);
            }
        }
    }
}
