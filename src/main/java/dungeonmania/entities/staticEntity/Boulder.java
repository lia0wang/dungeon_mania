package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class Boulder extends Entity{
    public Boulder(int x, int y, String type, String key) {
        super(x, y, type);
        this.setCollision(true);
    }

    // moves the rock
    // also check if a floorswitch is activated
    public void interact() {}

}
