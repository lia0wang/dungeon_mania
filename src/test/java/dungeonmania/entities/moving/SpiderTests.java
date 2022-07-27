package dungeonmania.entities.moving;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static dungeonmania.TestUtils.getEntities;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticEntity.Boulder;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SpiderTests {
    @Test 
    @DisplayName("Test spider moves to position 1 on first tick")
    public void firstTickMovementNoBoulder() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_basicMovement", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();

        res = dmc.tick(Direction.UP);

        assertEquals(new Position(x, y - 1), getEntities(res, "spider").get(0).getPosition());
    }

    @Test 
    @DisplayName("Test spider remains at spawnpoint if there is a boulder at position 1")
    public void firstTickMovementWithBoulder() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_boulderAtPos1", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();

        res = dmc.tick(Direction.UP);

        assertEquals(new Position(x, y), getEntities(res, "spider").get(0).getPosition());
        
    }

    @Test
    @DisplayName("Test basic movement of spiders")
    public void basicMovement() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_basicMovement", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        List<Position> movementTrajectory = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        movementTrajectory.add(new Position(x  , y-1));
        movementTrajectory.add(new Position(x+1, y-1));
        movementTrajectory.add(new Position(x+1, y));
        movementTrajectory.add(new Position(x+1, y+1));
        movementTrajectory.add(new Position(x  , y+1));
        movementTrajectory.add(new Position(x-1, y+1));
        movementTrajectory.add(new Position(x-1, y));
        movementTrajectory.add(new Position(x-1, y-1));

        // Assert Circular Movement of Spider
        for (int i = 0; i < movementTrajectory.size(); ++i) {
            res = dmc.tick(Direction.UP);
            assertEquals(movementTrajectory.get(i), getEntities(res, "spider").get(0).getPosition());
        }
    }

    @Test 
    @DisplayName("Test movement reverses when boulder is in path")
    public void boulderInPath() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_boulderInPath", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        List<Position> movementTrajectory = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        movementTrajectory.add(new Position(x  , y-1));
        movementTrajectory.add(new Position(x-1, y-1));
        movementTrajectory.add(new Position(x-1, y));
        movementTrajectory.add(new Position(x-1, y+1));
        movementTrajectory.add(new Position(x  , y+1));
        movementTrajectory.add(new Position(x+1, y+1));
        movementTrajectory.add(new Position(x+1, y));
        movementTrajectory.add(new Position(x+1, y+1));

        // Assert Circular Movement of Spider
        for (int i = 0; i < movementTrajectory.size(); ++i) {
            res = dmc.tick(Direction.UP);
            assertEquals(movementTrajectory.get(i), getEntities(res, "spider").get(0).getPosition());
        }   
    }

    @Test 
    @DisplayName("Test spider remains still between two boulders") 
    public void spiderInBetweenBoulders() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_betweenTwoBoulders", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        int x = pos.getX();
        int y = pos.getY();
        new Boulder(4, 4, "boulder");
        
        // Spider moves up one position in between two boulders
        res = dmc.tick(Direction.UP);
        Position newPos = new Position(x  , y - 1);

        // Assert that the spider is now between two boulders

        assertEquals(newPos, getEntities(res, "spider").get(0).getPosition());

        // Assert that the spider remains still between two boulders
        res = dmc.tick(Direction.UP);
        assertEquals(newPos, getEntities(res, "spider").get(0).getPosition());
    }
}