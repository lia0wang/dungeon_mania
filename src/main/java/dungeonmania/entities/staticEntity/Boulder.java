package dungeonmania.entities.staticEntity;

import dungeonmania.entities.BaseEntity;
import dungeonmania.util.Position;

public class Boulder extends BaseEntity{
    private boolean hasCollision;

    public Boulder(String id, String type, Position position) {
        super(id, type, position);
        this.hasCollision = true;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    // moves the rock
    // also check if a floorswitch is activated
    public void interact() {}

}
