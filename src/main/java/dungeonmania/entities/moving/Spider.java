package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends MovingEntity {
    private Position spawnPoint;
    private MovementBehaviour movement;
    private Integer currentTick = 0;

    public Spider(int x, int y, String type, Dungeon dungeon) {
        super(x, y, "spider", dungeon);
        this.spawnPoint = new Position(x, y);
        this.movement = new SpiderMovement();
    }

    /**
     * Gets the original spawn point of the spider entity
     * @return spawnPoint
     */
    public Position getSpawnPoint() {
        return spawnPoint;
    }

    /**
     * Gets the movement behaviour of the spider entity
     * @return movement 
     */
    public MovementBehaviour getMovement() {
        return movement;
    }  

    /**
     * Gets the current tick of the spider entity
     * @return spawnPoint
     */
    public Integer getCurrentTick() {
        return currentTick;
    }
    
    @Override
    public void move(Direction direction) {
        movement.move(direction, this);
        if (currentTick < 8) {
            currentTick++;
        } else {
            currentTick = 1;
        }
    }
    /**
     * [8] [1] [2]
     * [7] [0] [3]
     * [6] [5] [4]
     */
    
}
