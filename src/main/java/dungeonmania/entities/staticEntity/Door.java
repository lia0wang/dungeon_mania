package dungeonmania.entities.staticEntity;

import dungeonmania.entities.BaseEntity;
import dungeonmania.util.Position;

public class Door extends BaseEntity{
    private boolean hasCollision;
    private String key;

    public Door(String id, String type, Position position, String key) {
        super(id, type, position);
        this.hasCollision = true;
        this.key = key;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public void interact() {}

}