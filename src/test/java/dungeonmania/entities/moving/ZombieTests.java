package dungeonmania.entities.moving;

import static dungeonmania.TestUtils.getEntities;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("Test basic zombie movement w/o obstructions")
    public void basicZombieMovementNoWalls() {
        // First element in the array after shuffling should be 3
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_basicMovement", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();
        
        // After the first tick, the zombie should move up once
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(x+1, y), getEntities(res, "zombie_toast").get(0).getPosition());
    }

    @Test
    @DisplayName("Test basic zombie movement w/ 4 obstructions")
    public void basicZombieMovementFourWalls() {
        /**
         * Layout of the map z = zombie and w = wall
         * 
         *   * w *
         *   w z w 
         *   * w * 
         * 
         */
        // First element in the array after shuffling should be 3
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_fourWalls", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();
        
        // After the first tick, the zombie should remain still
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(x, y), getEntities(res, "zombie_toast").get(0).getPosition());
    }
    
    @Test
    @DisplayName("Test basic zombie movement w/ 3 obstructions")
    public void basicZombieMovementThreeWalls() {
        // Order of directions that move() will check
        /**
         * Layout of the map z = zombie, w = wall, e = expected position
         * 
         *   * w *
         *   e z w 
         *   * w * 
         * 
         */
        // First element in the array after shuffling should be 3
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_threeWalls", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();
        
        // After the first tick, the zombie should move left one space
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(x - 1, y), getEntities(res, "zombie_toast").get(0).getPosition());
    }

    @Test
    @DisplayName("Test basic zombie movement w/ 2 obstructions")
    public void basicZombieMovementTwoWalls() {
        // Order of directions that move() will check RIGHT -> UP -> DOWN -> LEFT
        /**
         * Layout of the map z = zombie, w = wall, e = expected position
         * 
         *   * w *
         *   * z w 
         *   * e * 
         * 
         */
        // First element in the array after shuffling should be 3
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_twoWalls", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();
        
        // After the first tick, the zombie should move down one space
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(x, y + 1), getEntities(res, "zombie_toast").get(0).getPosition());
    }

    @Test
    @DisplayName("Test basic zombie movement w/ 1 obstructions")
    public void basicZombieMovementOneWalls() {
        // Order of directions that move() will check RIGHT -> UP -> DOWN -> LEFT
        /**
         * Layout of the map z = zombie, w = wall, e = expected position
         * 
         *   * e *
         *   * z w 
         *   * * * 
         * 
         */
        // First element in the array after shuffling should be 3
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_oneWalls", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();
        
        // After the first tick, the zombie should move up one space
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(x, y - 1), getEntities(res, "zombie_toast").get(0).getPosition());
    }

}