package dungeonmania.entities.staticEntity;

import dungeonmania.entities.BaseEntity;
import dungeonmania.util.Position;

public class Wall extends BaseEntity{
    private boolean hasCollision;

    public Wall(String id, String type, Position position) {
        super(id, type, position);
        this.hasCollision = false;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public void interact() {}

}