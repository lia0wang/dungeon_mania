package dungeonmania.entities.staticEntity;

import dungeonmania.entities.BaseEntity;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends BaseEntity{
    private boolean hasCollision;

    public ZombieToastSpawner(String id, String type, Position position) {
        super(id, type, position);
        this.hasCollision = false;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public void interact() {}

}