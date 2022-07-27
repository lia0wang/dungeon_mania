package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends MovingEntity {
    private Position spawnPoint;
    private MovementBehaviour movement;
    private boolean anticlockwise;

    public Spider(int x, int y, String type, Dungeon dungeon) {
        super(x, y, "spider", dungeon);
        this.spawnPoint = new Position(x, y);
        this.movement = new SpiderMovement();
        this.anticlockwise = false;
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
<<<<<<< HEAD
     * Gets the current tick of the spider entity
     * @return spawnPoint
=======
     * Gets the current spider movement direction
     * @return anticlockwise 
>>>>>>> master
     */
    public boolean isAnticlockwise() {
        return anticlockwise;
    }

    /**
     * Sets the direction of the spider
     * @param anticlockwise
     */
    public void setAnticlockwise(boolean anticlockwise) {
        this.anticlockwise = anticlockwise;
    }

    @Override
    public void move(Direction direction) {
        movement.move(direction, this);
    }
}
