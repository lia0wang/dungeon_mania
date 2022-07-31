package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends MovingEntity {
    private Position spawnPoint;
    private MovementBehaviour movement;
    private boolean anticlockwise;
    private int attack;
    private double health;

    public Spider(int x, int y, String type, Dungeon dungeon, int attack, int health) {
        super(x, y, "spider", dungeon);
        this.spawnPoint = new Position(x, y);
        this.movement = new SpiderMovement();
        this.anticlockwise = false;
        this.attack = attack;
        this.health = health;
    }
    
    /**
     * Gets the spider_attack
     * @return attack
     */
    public double getAttack() {
        return attack;
    }

    /**
     * Gets the spider_health
     * @return health
     */
    public double getHealth() {
        return health;
    }

    /**
     * Sets the health
     * @return health
     */
    public void setHealth(double health) {
        this.health = health;
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
     * Gets the current spider movement direction
     * @return anticlockwise 
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
