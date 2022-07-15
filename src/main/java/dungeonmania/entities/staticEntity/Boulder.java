package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class Boulder extends Entity{
    private boolean hasCollision;

    public Boulder(int x, int y, String type, String key) {
        super(x, y, type);
        this.hasCollision = true;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    // moves the rock
    // also check if a floorswitch is activated
    public void interact() {}

}
