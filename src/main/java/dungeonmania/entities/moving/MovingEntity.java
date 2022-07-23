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
}
