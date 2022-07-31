package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;

public class ZombieToast extends MovingEntity {
    private MovementBehaviour movement;
    private int attack;
    private double health;
    
    public ZombieToast(int x, int y, String type, Dungeon dungeon, int attack, int health) {
        super(x, y, type, dungeon);
        this.movement = new ZombieMovement();
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

    public MovementBehaviour getMovement() {
        return movement;
    }

    public void move(Direction direction) {
        movement.move(direction, this);
    }
}
