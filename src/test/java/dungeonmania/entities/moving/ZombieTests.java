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
    @DisplayName("Test basic zombie movement")
    public void basicZombieMovement() {
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_basicMovement", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();

        res = dmc.tick(Direction.UP);

        assertEquals(new Position(x, y - 1), getEntities(res, "spider").get(0).getPosition());
    }


    public static void main(String[] args) {
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        for (int i = 0; i < 4; i++) {
            System.out.println(indexList.get(i));
        }
    }
}
