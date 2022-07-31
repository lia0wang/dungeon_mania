package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.util.Direction;

public abstract class MovingEntity extends Entity{
    protected Dungeon dungeon;
    
    public MovingEntity(int x, int y, String type, Dungeon dungeon) {
        super(x, y, type);
        this.dungeon = dungeon;
    }
    public Dungeon getDungeon() {
        return dungeon;
    }
    
    public abstract void move(Direction direction);

    /**
     * Get entity attack
     *
     * @return attack
     */
    public abstract double getAttack();

    /**
     * Get entity health
     *
     * @return health
     */
    public abstract double getHealth();

    /**
     * Set entity health
     *
     * @return health
     */
    public abstract void setHealth(double health);
}
